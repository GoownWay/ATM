package dao.adminDao;

import javax.swing.JOptionPane;
import bean.Admin;
import dao.Method;
import frame.LoginFrame;

public class AdminChangePasswordDao {
	//检验两次新密码是否一样
	public boolean isTwoPasswordSame(char[] firstPassword, char[] secondPassword) {
		String gotfirstPassword = String.valueOf(firstPassword);//获取第一次输入的密码
		String gotsecondPassword = String.valueOf(secondPassword);//获取第二次输入的密码
		if(!gotfirstPassword.equals("")&&!gotsecondPassword.equals(""))
		{
			if(gotfirstPassword.equals(gotsecondPassword))
			{//判断两次密码输入是否正确
				Method method = new Method();
				Admin admin = method.queryAdmin(LoginFrame.loginedAccount);//根据账号查询管理员的信息
				admin.setPassword(gotfirstPassword);//设置新密码
				boolean i = method.updateAdmin(admin);//更新到数据库
				if(i)
					return true;
			}
			else
				JOptionPane.showMessageDialog(null, "两次新密码输入不一致", "错误提示", JOptionPane.ERROR_MESSAGE);
		}else
			JOptionPane.showMessageDialog(null, "新密码或确认密码输入为空", "错误提示", JOptionPane.ERROR_MESSAGE);
		return false;
	}
}