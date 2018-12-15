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

public class WithdrawDao {
	Method method =new Method();
	static Trade withdrawtrade = new Trade();;
	//获得交易凭条所需信息
	public List<String> gettickertape() {
		List<String> list = new ArrayList<String>();//用String类型的list装凭条所需的信息
		list.add(withdrawtrade.getId());
		//将银行卡的倒数第2位~倒数第5位变成*
		String accout = withdrawtrade.getAccount();
		accout = accout.substring(0,14)+"****"+accout.substring(18,19); 

		list.add(accout);
		//将名字的中间部分变成*
		String name = withdrawtrade.getName();
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

		String transAccount = withdrawtrade.getTransAccount();
		transAccount = transAccount.substring(0,14)+"****"+transAccount.substring(18,19);

		list.add(transAccount);
		list.add(withdrawtrade.getTradeSum().toString());
		list.add(withdrawtrade.getType());
		list.add(String.valueOf(withdrawtrade.getDate()).substring(0, 19));
		list.add(withdrawtrade.getRemark());

		//看手续费是否超高50，如果超过则收50,不超过则收相应的手续费
		if(withdrawtrade.getTradeSum()*TransferDao.free<50)
			list.add(String.valueOf(withdrawtrade.getTradeSum()*TransferDao.free));
		else
			list.add("50.00");

		TransferDao.free=0;//操作完成后手续费重置为0
		return list;	
	}

	//取款后更新卡和产生交易记录
	public boolean updateRemainMoney(String money) {
		if(!money.equals("")){
			if(0<Integer.parseInt(money)){
				if(Integer.parseInt(money)<=5000)
				{
					if(Integer.parseInt(money)%100==0){
						ATM atm = method.queryATM();//查询ATM机的信息
						Card card = method.queryCard(LoginFrame.loginedAccount);//查询卡的信息

						if(atm!=null){//判断card和atm查询是否有结果集，无的话返回null

							Long ATMRemainMoney=atm.getATMRemainMoney();//atm的余额
							Double cardRemainMoney = card.getRemainMoney();//card的余额
							Long cardDesirable = card.getDesirable();//card的当日可取余额
							String cardBank = card.getBank();//card的所属银行

							long sum = ATMRemainMoney - Long.parseLong(money);//取款金额不能超过atm余额
							if(sum>0)
							{
								if(cardDesirable>Long.parseLong(money))
								{
									if(!cardBank.equals("建设银行"))//如果非建设银行的则收取1%的手续费
										TransferDao.free=0.01;
									if(cardRemainMoney>(Double.parseDouble(money)*(1+TransferDao.free)))
									{//取款金额加上手续费是否大于卡的余额
										Long ATMTakeMoney=atm.getATMTakeMoney();//atm的累计取款金额数
										Long BeUseTimes=atm.getBeUsedTimes();//atm的使用次数
										String userName = card.getName();//card的用户名

										atm.setATMRemainMoney(sum);//更新ATM机的余额
										ATMTakeMoney+=Long.parseLong(money);//确认可以取款之后，更新atm的累计取款金额
										atm.setATMTakeMoney(ATMTakeMoney);;

										BeUseTimes+=1;//atm使用次数加1
										atm.setBeUsedTimes(BeUseTimes);
										//看手续费是否超高50，如果超过则收50,不超过则收相应的手续费
										if(Double.parseDouble(money)*TransferDao.free<50)
											cardRemainMoney=cardRemainMoney-Double.parseDouble(money)*(1+TransferDao.free);//存款后card的余额
										else
											cardRemainMoney=cardRemainMoney-Double.parseDouble(money)-50.00;//存款后card的余额

										card.setRemainMoney(cardRemainMoney);

										//存款后更新atm机的语句
										boolean i = method.updateATM(atm);

										//存款后更新card的语句
										boolean j = method.updateCard(card);

										if(i&&j)//判断是否更新成功,更新成功后产生凭条
										{
											Date getdate = new Date();
											SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
											String qdate = sdf.format(getdate);
											Timestamp date = Timestamp.valueOf(qdate);

											sdf.applyPattern("yyyyMMddHHmmss");
											String tradeId = sdf.format(getdate)+"0001";//交易流水号为时间+0001

											withdrawtrade.setId(tradeId);
											withdrawtrade.setAccount(LoginFrame.loginedAccount);
											withdrawtrade.setName(userName);
											withdrawtrade.setDate(date);
											withdrawtrade.setTradeSum(Double.parseDouble(money));
											withdrawtrade.setTransAccount(LoginFrame.loginedAccount);//存款的话账号和交易账号设置为同一个
											withdrawtrade.setType("取款");
											//看手续费是否超高50，如果超过则收50,不超过则收相应的手续费
											if(Double.parseDouble(money)*TransferDao.free<50)
												withdrawtrade.setServiceCharge(TransferDao.free*Double.parseDouble(money));
											else
												withdrawtrade.setServiceCharge(50.00);
											withdrawtrade.setRemark("");//取款没备注

											boolean k = method.updateTradeInfo(withdrawtrade);//交易记录插入到数据库中
											if(k)
												return true;
										}
									}else
										JOptionPane.showMessageDialog(null, "余额不足！", "错误提示", JOptionPane.ERROR_MESSAGE);
								}else
									JOptionPane.showMessageDialog(null, "取款金额超过今日可取余额，请重新输入！", "错误提示", JOptionPane.ERROR_MESSAGE);
							}else
								JOptionPane.showMessageDialog(null, "取款金额超过ATM机余额！", "错误提示", JOptionPane.ERROR_MESSAGE);
						}
					}else
						JOptionPane.showMessageDialog(null, "取款金额输入错误，请输入整百金额！", "错误提示", JOptionPane.ERROR_MESSAGE);
				}else
					JOptionPane.showMessageDialog(null, "单笔取款不能超过5000，请重新输入取款金额！", "错误提示", JOptionPane.ERROR_MESSAGE);
			}else
				JOptionPane.showMessageDialog(null, "取款金额为0或者是负数，请重新输入取款金额！", "错误提示", JOptionPane.ERROR_MESSAGE);
		}else 
			JOptionPane.showMessageDialog(null, "取款金额不能为空，请输入取款金额！", "错误提示", JOptionPane.ERROR_MESSAGE);
		return false;
	}
}