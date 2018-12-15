package dao.userDao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import bean.Card;
import bean.Trade;
import dao.Method;
//查询余额和交易明细
public class CheckDao {
	Method method = new Method();
	//用户查询余额
	public List<String> balanceQuery(String loginedAccount) {
		Card card = method.queryCard(loginedAccount);//根据登录的账号查询卡的信息
		List<String> list = new ArrayList<String>();
		String money = String.valueOf(card.getRemainMoney());//余额
		String desirable = String.valueOf(card.getDesirable());//当日可取余额
		if(card.getRemainMoney()>card.getDesirable())
		{//当余额大于可取余额时余额和可取余额是不同的
			list.add(money);
			list.add(desirable);
		}
		else
		{//当余额小于可取余额时余额就是可取余额
			list.add(money);
			list.add(money);
		}
		return list;
	}

	//用户查询交易明细
	public String[][] detailedInquiry(String loginedAccount, String start, String end) {
		if(start.compareTo(end)>0)
		{
			JOptionPane.showMessageDialog(null, "开始时间比结束时间晚，请重新选择！", "错误提示", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		start = start + " 00:00:00";//开始那天的0时0分0秒
		end = end + " 23:59:59";//结束那天的23时59分59秒

		Timestamp startDate = Timestamp.valueOf(start);
		Timestamp endDate = Timestamp.valueOf(end);
		//查询在这个时间段的交易明细
		List<Trade> tradeList = method.queryTrade(loginedAccount,startDate,endDate);

		String[][] sub = new String[tradeList.size()][8];
		for(int i=0;i< tradeList.size();i++)
		{//将一行一行交易记录放在二维数组的中
			sub[i][0] = i+1+"";
			sub[i][1] = tradeList.get(i).getId();
			sub[i][2] = tradeList.get(i).getName();
			sub[i][3] = String.valueOf(tradeList.get(i).getDate()).substring(0,19);
			sub[i][4] = String.valueOf(tradeList.get(i).getTradeSum());
			sub[i][5] = tradeList.get(i).getTransAccount();
			sub[i][6] = tradeList.get(i).getType();
			sub[i][7] = String.valueOf(tradeList.get(i).getServiceCharge());
		}
		return sub;
	}
}