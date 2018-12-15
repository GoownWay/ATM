package dao.adminDao;

import javax.swing.JOptionPane;

import bean.Card;
import dao.Method;

public class DeleteUserDao {
	Method method = new Method();
	Card card= new Card();//银行卡对象

	public boolean queryInfoToDelete(Card card){
		int a = this.judgeDelete(card);
		if(a == 0) {
			card.setPrivilege("注销");//将卡的状态设置为注销
			card.setRemainMoney(0.00);//将用户的余额清空
			boolean i = method.updateCard(card);//更新卡的信息
			return i;
		}else return false;
	}

	//根据卡的状态，来决定是否能注销
	public int judgeDelete(Card card) {
		String privilege;//银行卡权限
		privilege = card.getPrivilege();
		if(privilege.equals("正常") || privilege.equals("冻结") || privilege.equals("挂失")) return 0;
		else if(privilege.equals("注销")) {
			JOptionPane.showMessageDialog(null, "该卡已被注销！", "错误提示", JOptionPane.ERROR_MESSAGE);
			return 2;
		}
		return -1;
	}

	//判断账户是否存在
	public Card queryIsExist(String tex) {
		if(tex.length()==19){
			//账号长度为19位
			card = method.queryCard(tex);
			if(card.getAccount()!=null){
				//银行卡的主键不为空即存在该账号
				return card;
			}
			else {
				JOptionPane.showMessageDialog(null, "账号不存在，请检查是否输入有误", "错误提示", JOptionPane.ERROR_MESSAGE);
				return null;
			}
		}else
			JOptionPane.showMessageDialog(null, "账号位数为19位！请正确输入！", "错误提示", JOptionPane.ERROR_MESSAGE);
		return null;
	}
}