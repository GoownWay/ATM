package dao.adminDao;

import javax.swing.JOptionPane;

import bean.User;
import dao.Method;

public class UpdateUserDao {
	Method method = new Method();

	//判断账号是否存在
	public User queryIsExist(String tex) {
		if(tex.length()==18){
			//身份证长度为18位
			User user = method.queryUser(tex);//查询卡信息
			if(user.getId()!=null){
				//银行卡的主键不为空即存在该账号
				return user;
			}
			else {
				JOptionPane.showMessageDialog(null, "账号不存在，请检查是否输入有误", "错误提示", JOptionPane.ERROR_MESSAGE);
				return null;
			}

		}else
			JOptionPane.showMessageDialog(null, "账号位数为18位！请输入正确！", "错误提示", JOptionPane.ERROR_MESSAGE);
		return null;
	}
}