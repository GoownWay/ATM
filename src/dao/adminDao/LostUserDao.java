package dao.adminDao;

import javax.swing.JOptionPane;

import bean.Card;
import dao.Method;

public class LostUserDao {
	Method method =new Method();
	Card card= new Card();//银行卡对象
	
	//挂失卡
	public boolean queryInfoToLost(Card card){
		int a = this.judgeLost(card);
		if(a == 0) {
			card.setPrivilege("挂失");
			boolean i = method.updateCard(card);//更新数据库中卡的信息
			return i;
		}else return false;
	}
	//检查卡是否存在
	public Card queryIsExist(String tex) {
		if(tex.length()==19){
			//账号长度为19位
			card = method.queryCard(tex);//查询卡信息
			if(card.getAccount()!=null){
				//银行卡的主键不为空即存在该账号
				return card;
			}
			else {
				JOptionPane.showMessageDialog(null, "账号不存在，请检查是否输入有误", "错误提示", JOptionPane.ERROR_MESSAGE);
				return null;
			}
		}else
			JOptionPane.showMessageDialog(null, "账号位数为19位！请输入正确！", "错误提示", JOptionPane.ERROR_MESSAGE);
		return null;
	}
	
	//银行卡权限判断
	public int judgeLost(Card card) {
		String privilege;//银行卡权限
		privilege = card.getPrivilege();
		if(privilege.equals("正常") || privilege.equals("冻结")) return 0;
		else if(privilege.equals("挂失")) {
			JOptionPane.showMessageDialog(null, "该卡已处于挂失状态！", "错误提示", JOptionPane.ERROR_MESSAGE);
			return 1;
		}else if(privilege.equals("注销")) {
			JOptionPane.showMessageDialog(null, "该卡已被注销！", "错误提示", JOptionPane.ERROR_MESSAGE);
			return 2;
		}
		return -1;
	}
}