package dao.adminDao;

import javax.swing.JOptionPane;
import bean.Card;
import dao.Method;
//管理员修改用户密码
public class ChangeCardPasswordDao {

	public boolean isTwoPasswordSame(String cardAccount, char[] firstPassword, char[] secondPassword) {
		String gotfirstPassword = String.valueOf(firstPassword);//获取第一次输入的密码
		String gotsecondPassword = String.valueOf(secondPassword);//获取第二次输入的密码
		if(gotfirstPassword.equals(gotsecondPassword))
		{//判断两次密码输入是否正确
			Method method = new Method();
			Card card = method.queryCard(cardAccount);//根据账号查询卡的信息
			card.setPassword(gotfirstPassword);//设置新密码
			boolean i = method.updateCard(card);//更新到数据库
			if(i)
				return true;
		}
		else
			JOptionPane.showMessageDialog(null, "两次新密码输入不一致", "错误提示", JOptionPane.ERROR_MESSAGE);
		return false;
	}
}