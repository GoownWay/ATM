package dao.adminDao;

import javax.swing.JOptionPane;

import bean.Card;
import dao.Method;

public class ThawReleaseUserDao {
	Method method =new Method();
	Card card=null;//银行卡对象
	//更新卡状态
	public boolean queryInfoToThawRelease(Card card){
		int a = this.judgeThawRelease(card);//卡的权限判断
		if(a == 0) {
			card.setPrivilege("正常");
			boolean i = method.updateCard(card);//更新卡状态
			return i;
		}else return false;
	}

	//对查找到的银行卡进行权限判断
	public int judgeThawRelease(Card card) {
		String privilege;//银行卡权限
		privilege = card.getPrivilege();
		if(privilege.equals("冻结") || privilege.equals("挂失")) return 0;
		else if(privilege.equals("注销")) {
			JOptionPane.showMessageDialog(null, "该卡已被注销！", "错误提示", JOptionPane.ERROR_MESSAGE);
			return 2;
		}else if(privilege.equals("正常")) {
			JOptionPane.showMessageDialog(null, "该卡处于正常状态！", "错误提示", JOptionPane.ERROR_MESSAGE);
			return 3;
		}
		return -1;
	}

	//判断卡号是否存在，如果存在则返回查到的card对象
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
}