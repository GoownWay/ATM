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
//无卡存款和有卡存款
public class DepositDao {
	Method method =new Method();
	static Trade trade;
	//无卡操作时通过账号查询姓名，所属银行和账号状态，text传入的是账号，money是金额
	public List<String> queryInfoByAccount(String text, String money) {
		//首先对输入的信息进行判断，判断输入是否正确
		if(text.matches("^[0-9]+$")&&text.length()==19){
			if(!money.equals("") && (!money.equals("请输入存入金额:"))){
				if(0<Integer.parseInt(money)){
					if(Integer.parseInt(money)<=10000){
						if(Integer.parseInt(money)%100==0){
							Card card = method.queryCard(text);//根据账号查询卡的信息
							List<String> list = new ArrayList<String>(); //list集合，第0个代表用户名，第1个代表所属银行
							if(LoginFrame.loginedAccount!=null){//如果是登录后的存款，则不用执行接下来的操作
								list.add("已登录");
								return list;
							}
							if(card.getAccount()!=null)//判断是否查到信息，没查到则返回null，表示用户不存在
							{
								String cardPrivilege = card.getPrivilege();//获取卡状态
								if(cardPrivilege.equals("挂失"))
									JOptionPane.showMessageDialog(null, "该账号已挂失", "错误提示", JOptionPane.ERROR_MESSAGE);
								else
									if(cardPrivilege.equals("注销"))
										JOptionPane.showMessageDialog(null, "账号已注销", "错误提示", JOptionPane.ERROR_MESSAGE);
									else{
										String userName = card.getName();	//用户名
										//对用户名的中间部分进行隐藏，只显示第一个后最后一个字
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
										String cardBank = card.getBank();//所属银行
										list.add(rName);
										list.add(cardBank);
										return list;//返回list集合
									}
							}else
								JOptionPane.showMessageDialog(null, "账号不存在，请检查账号是否输入有误", "错误提示", JOptionPane.ERROR_MESSAGE);
						}else
							JOptionPane.showMessageDialog(null, "存入金额输入错误，请输入整百金额！", "错误提示", JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(null, "单笔存款不能超过10000，请重新输入存入金额！", "错误提示", JOptionPane.ERROR_MESSAGE);
				}else
					JOptionPane.showMessageDialog(null, "存入金额为0或者是负数，请重新输入存入金额！", "错误提示", JOptionPane.ERROR_MESSAGE);
			}else 
				JOptionPane.showMessageDialog(null, "存入金额不能为空，请输入存入金额！", "错误提示", JOptionPane.ERROR_MESSAGE);
		}else
			JOptionPane.showMessageDialog(null, "账号输入有误,请输入19位的纯数字账号！", "错误提示", JOptionPane.ERROR_MESSAGE);
		return null;
	}

	//点击确认后，传入了账号和存入金额，然后更新ATM机信息，用户信息和生成一条交易信息
	public boolean updateInfoByAccount(String tex, String mon) {

		ATM atm = method.queryATM();//查询ATM机的信息
		Card card = method.queryCard(tex);//查询卡的信息

		if(card!=null&&atm!=null){//判断card和atm查询是否有结果集，无的话返回null

			Long ATMRemainMoney=atm.getATMRemainMoney();//atm的余额
			Long ATMDepositMoney=atm.getATMDepositMoney();//atm的累计存款金额数
			Long BeUseTimes=atm.getBeUsedTimes();//atm的使用次数

			String cardBank = card.getBank();//card的所属银行
			Double cardRemainMoney = card.getRemainMoney();//card的余额
			String userName = card.getName();//card的用户名

			if(!cardBank.equals("建设银行"))//如果非建设银行的则收取1%的手续费
				TransferDao.free=0.01;

			long sum = ATMRemainMoney + Long.parseLong(mon);//存入金额加上atm余额不能超过atm机的装载量
			if(sum>100000)
			{
				JOptionPane.showMessageDialog(null, "存入金额超过ATM机装载量！", "错误提示", JOptionPane.ERROR_MESSAGE);
				return false;//大于装载量则不能存
			}
			else
			{
				atm.setATMRemainMoney(sum);//更新ATM机的余额
				ATMDepositMoney+=Long.parseLong(mon);//确认可以存款之后，更新atm的累计存款金额
				atm.setATMDepositMoney(ATMDepositMoney);

				BeUseTimes+=1;//atm使用次数加1
				atm.setBeUsedTimes(BeUseTimes);
				//看手续费是否超高50，如果超过则收50,不超过则收相应的手续费
				if(Double.parseDouble(mon)*TransferDao.free<50)
					cardRemainMoney=cardRemainMoney+Double.parseDouble(mon)*(1-TransferDao.free);//存款后card的余额
				else					
					cardRemainMoney=cardRemainMoney+Double.parseDouble(mon)-50;//存款后card的余额

				card.setRemainMoney(cardRemainMoney);

				//存款后更新atm机的语句
				boolean i = method.updateATM(atm);

				//存款后更新card的语句
				boolean j = method.updateCard(card);

				if(i&&j)//判断是否更新成功,更新成功后产生凭条
				{
					trade = new Trade();
					Date getdate = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String qdate = sdf.format(getdate);
					Timestamp date = Timestamp.valueOf(qdate);

					sdf.applyPattern("yyyyMMddHHmmss");
					String tradeId = sdf.format(getdate)+"0001";//交易流水号为时间+0001

					trade.setId(String.valueOf(tradeId));
					trade.setAccount(tex);
					trade.setName(userName);
					trade.setDate(date);
					trade.setTradeSum(Double.parseDouble(mon));
					trade.setTransAccount(tex);//存款的话账号和交易账号设置为同一个
					trade.setType("存款");
					//看手续费是否超高50，如果超过则收50,不超过则收相应的手续费
					if(Double.parseDouble(mon)*TransferDao.free<50)
						trade.setServiceCharge(TransferDao.free*Double.parseDouble(mon));
					else
						trade.setServiceCharge(50.00);
					trade.setRemark(NoCardDepositFrame.rem);

					boolean k = method.updateTradeInfo(trade);//将交易记录插入到数据库
					if(k)
						return true;
					else
						return false;
				}
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

		String transAccount = trade.getTransAccount();
		transAccount = transAccount.substring(0,14)+"****"+transAccount.substring(18,19);

		list.add(transAccount);
		list.add(trade.getTradeSum().toString());
		list.add(trade.getType());
		list.add(String.valueOf(trade.getDate()).substring(0, 19));
		list.add(trade.getRemark());
		//看手续费是否超高50，如果超过则收50,不超过则收相应的手续费
		if(trade.getTradeSum()*TransferDao.free<50)
			list.add(String.valueOf(trade.getTradeSum()*TransferDao.free));
		else
			list.add("50.00");
		TransferDao.free=0;//操作完成后手续费重置为0
		return list;	
	}
}