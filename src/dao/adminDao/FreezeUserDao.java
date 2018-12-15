package dao.adminDao;

import javax.swing.JOptionPane;

import bean.Card;
import dao.Method;

public class FreezeUserDao {
	Method method = new Method();
	Card card= new Card();//银行卡对象

	//冻结卡
	public boolean queryInfoToFreeze(Card card){
		int a = this.judgeFreeze(card);//银行卡权限判断
		if(a == 0) {
			card.setPrivilege("冻结");
			card.setPWDNum(0);
			boolean i = method.updateCard(card);//更新数据库中的卡信息
			return i;
		}else return false;
	}

	//银行卡权限判断
	public int judgeFreeze(Card card) {
		String privilege;//银行卡权限
		privilege = card.getPrivilege();
		if(privilege.equals("正常")) return 0;
		else if(privilege.equals("冻结")) {
			JOptionPane.showMessageDialog(null, "该卡已处于冻结状态！", "错误提示", JOptionPane.ERROR_MESSAGE);
			return 1;
		}else if(privilege.equals("注销")) {
			JOptionPane.showMessageDialog(null, "该卡已被注销！", "错误提示", JOptionPane.ERROR_MESSAGE);
			return 2;
		}else if(privilege.equals("挂失")) {
			JOptionPane.showMessageDialog(null, "该卡已处于挂失状态！", "错误提示", JOptionPane.ERROR_MESSAGE);
			return 3;
		}
		return -1;
	}

	//判断账号是否存在
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
			JOptionPane.showMessageDialog(null, "账号位数为19位！请输入正确！", "错误提示", JOptionPane.ERROR_MESSAGE);
		return null;
	}
}