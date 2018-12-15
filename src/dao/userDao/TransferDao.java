package dao.userDao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import bean.ATM;
import bean.Card;
import bean.Trade;
import dao.Method;
import frame.LoginFrame;
import frame.userFrame.noCardDeposit.NoCardDepositFrame;

public class TransferDao {
	Method method =new Method();
	static double free=0;//手续费百分比
	static Trade trade;//交易记录
	//用户转账时通过账号查询姓名，所属银行和账号状态，text传入的是账号,money是金额
	public List<String> queryInfoByAccount(String text, String money) {
		if(text.matches("^[0-9]+$")&&text.length()==19){
			if(money!="" && (!money.equals("请输入转入金额:"))){
				if(0<Double.parseDouble(money)){
					if(Double.parseDouble(money)<=50000){
						if(!text.equals(LoginFrame.loginedAccount)){
							//查询登录的卡的信息
							Card card = method.queryCard(LoginFrame.loginedAccount);
							//查询转入账号的信息
							Card goalCard = method.queryCard(text);

							List<String> list = new ArrayList<String>(); //list集合，第0个代表用户名，第1个代表所属银行

							if(goalCard.getAccount()!=null)//判断是否查到信息，没查到则返回null，表示用户不存在
							{
								String cardPrivilege = goalCard.getPrivilege();//获取卡状态
								if(cardPrivilege.equals("挂失"))
									JOptionPane.showMessageDialog(null, "该账号已挂失", "错误提示", JOptionPane.ERROR_MESSAGE);
								else
									if(cardPrivilege.equals("注销"))
										JOptionPane.showMessageDialog(null, "账号已注销", "错误提示", JOptionPane.ERROR_MESSAGE);
									else{
										Double transferDesirable = card.getTransferdesirable();//卡的当日可转账余额
										if(transferDesirable>0 && transferDesirable>Double.parseDouble(money))
										{
											if(!card.getBank().equals("建设银行")||!goalCard.getBank().equals("建设银行"))//如果非建设银行的则收取1%的手续费
												free=0.01;//登录的卡或者转入的卡其中一个为非建设银行账户，则收取相应的手续费
											Double remain;
											//看手续费是否超高50，如果超过则收50,不超过则收相应的手续费
											if(Double.parseDouble(money)*free<50)
												remain = card.getRemainMoney()-Double.parseDouble(money)*(1+free);
											else
												remain = card.getRemainMoney()-Double.parseDouble(money)-50;
											if(remain>=0){//余额是否够扣除转账金额和手续费
												String userName = goalCard.getName();	//用户名
												//将名字中间部分用*代替
												String first = userName.substring(0, 1);
												String last;
												if(userName.length()>2)
													last = userName.substring(userName.length()-1,userName.length());
												else
													last = "";
												String rName = first;
												for(int i=1;i<userName.length();i++){
													rName = rName + " * ";
												}
												rName = rName+last; 
												String cardBank = goalCard.getBank();//所属银行
												list.add(rName);
												list.add(cardBank);
												return list;//返回list集合
											}else 
												JOptionPane.showMessageDialog(null, "余额不足，请重新输入转账金额！", "错误提示", JOptionPane.ERROR_MESSAGE);
										}else
											JOptionPane.showMessageDialog(null, "超过今日可转账金额，请重新输入转账金额！", "错误提示", JOptionPane.ERROR_MESSAGE);
									}
							}else
								JOptionPane.showMessageDialog(null, "账号不存在，请检查账号是否输入有误", "错误提示", JOptionPane.ERROR_MESSAGE);
						}else
							JOptionPane.showMessageDialog(null, "您输入的是自己账号，本行不进行卡内转账，请重新输入", "错误提示", JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(null, "单笔转账不能超过50000，请重新输入转账金额！", "错误提示", JOptionPane.ERROR_MESSAGE);
				}else
					JOptionPane.showMessageDialog(null, "转入金额为0或者是负数，请重新输入转入金额！", "错误提示", JOptionPane.ERROR_MESSAGE);
			}else 
				JOptionPane.showMessageDialog(null, "转入金额不能为空，请输入转入金额！", "错误提示", JOptionPane.ERROR_MESSAGE);
		}else
			JOptionPane.showMessageDialog(null, "账号输入有误,请输入19位的纯数字账号！", "错误提示", JOptionPane.ERROR_MESSAGE);
		return null;
	}

	//点击确认后，传入了账号和存入金额，然后更新ATM机信息，用户信息和生成一条交易信息
	public boolean updateInfoByAccount(String tex, String mon) {

		ATM atm = method.queryATM();//查询ATM机的信息
		Card card = method.queryCard(LoginFrame.loginedAccount);//查询卡的信息
		Card goalCard = method.queryCard(tex);//查询卡的信息

		if(goalCard!=null&&atm!=null){//判断card和atm查询是否有结果集，无的话返回null

			Double ATMTransferMoney = atm.getATMTransferMoney();//atm累计转账金额数
			Long BeUseTimes=atm.getBeUsedTimes();//atm的使用次数
			Double cardRemainMoney = card.getRemainMoney();//card的余额
			String GoaluserName = goalCard.getName();//card的用户名
			String userName = card.getName();//交易账号姓名
			Double cardTransferDesirable = card.getTransferdesirable();//卡的当日可转账数

			ATMTransferMoney+=Double.parseDouble(mon);//确认可以存款之后，更新atm的累计存款金额
			atm.setATMTransferMoney(ATMTransferMoney);

			BeUseTimes+=1;//atm使用次数加1
			atm.setBeUsedTimes(BeUseTimes);
			//看手续费是否超高50，如果超过则收50,不超过则收相应的手续费
			if(Double.parseDouble(mon)*free<50)
				cardRemainMoney=cardRemainMoney-Double.parseDouble(mon)*(1+free);//存款后card的余额				
			else
				cardRemainMoney=cardRemainMoney-Double.parseDouble(mon)-50;//存款后card的余额				

			card.setRemainMoney(cardRemainMoney);
			goalCard.setRemainMoney(goalCard.getRemainMoney()+Double.parseDouble(mon));

			cardTransferDesirable = cardTransferDesirable-Double.parseDouble(mon);
			card.setTransferdesirable(cardTransferDesirable);
			//存款后更新atm机的语句
			boolean i = method.updateATM(atm);

			//存款后更新card的语句
			boolean j = method.updateCard(card);
			boolean l = method.updateCard(goalCard);

			if(i&&j&&l)//判断是否更新成功,更新成功后产生凭条
			{
				trade = new Trade();
				Date getdate = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String qdate = sdf.format(getdate);
				Timestamp date = Timestamp.valueOf(qdate);
				//本账号交易记录
				sdf.applyPattern("yyyyMMddHHmmss");
				String tradeId = sdf.format(getdate)+"0001";//交易流水号为时间+0001
				trade.setId(String.valueOf(tradeId));
				trade.setAccount(LoginFrame.loginedAccount);//<------------------------缺自己账号------------>
				trade.setName(GoaluserName);
				trade.setDate(date);
				trade.setTradeSum(Double.parseDouble(mon));
				trade.setTransAccount(tex);//存款的话账号和交易账号设置为同一个
				trade.setType("转出");
				//看手续费是否超高50，如果超过则收50,不超过则收相应的手续费
				if(Double.parseDouble(mon)*free<50)
					trade.setServiceCharge(free*Double.parseDouble(mon));
				else
					trade.setServiceCharge(50.00);
				trade.setRemark(NoCardDepositFrame.rem);

				//目标账号交易记录
				Trade tradeGoal = new Trade();
				String tradeGoalId = sdf.format(getdate)+"0002";//转账目标账号的交易流水号为时间+0002
				tradeGoal.setId(String.valueOf(tradeGoalId));
				tradeGoal.setAccount(tex);
				tradeGoal.setName(userName);
				tradeGoal.setDate(date);
				tradeGoal.setTradeSum(Double.parseDouble(mon));
				tradeGoal.setTransAccount(LoginFrame.loginedAccount);
				tradeGoal.setType("转入");
				tradeGoal.setServiceCharge(0.00);
				tradeGoal.setRemark(NoCardDepositFrame.rem);

				boolean k = method.updateTradeInfo(trade);//插入交易到数据库
				boolean y = method.updateTradeInfo(tradeGoal);//插入交易到数据库
				if(k&&y)
					return true;
				else
					return false;
			}
		}
		return true;
	}

	//获得交易凭条所需信息
	public List<String> gettickertape() {
		List<String> list = new ArrayList<String>();//用String类型的list装凭条所需的信息
		list.add(trade.getId());
		//将银行卡的倒数第2位~倒数第5位变成*
		String accout = trade.getAccount();
		accout = accout.substring(0,14)+"****"+accout.substring(18,19); 

		list.add(accout);
		//将名字的中间部分变成*
		String name = trade.getName();
		String first = name.substring(0, 1);
		String last;
		if(name.length()>2)
			last = name.substring(name.length()-1,name.length());
		else
			last = "";
		String rName = first;
		for(int i=1;i<name.length();i++){
			rName = rName + " * ";
		}
		rName = rName+last; 

		list.add(rName);

		String taccout = trade.getTransAccount();
		taccout = taccout.substring(0,14)+"****"+taccout.substring(18,19); 

		list.add(taccout);
		list.add(trade.getTradeSum().toString());
		list.add(trade.getType());
		list.add(String.valueOf(trade.getDate()).substring(0, 19));
		list.add(trade.getRemark());
		if(trade.getTradeSum()*free<50)
			list.add(String.valueOf(trade.getTradeSum()*free));
		else
			list.add("50.00");
		free=0;
		return list;	
	}
}