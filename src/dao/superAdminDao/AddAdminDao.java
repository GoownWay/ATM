package dao.superAdminDao;

import dao.Method;

import javax.swing.JOptionPane;

import bean.Admin;
//添加管理员
public class AddAdminDao {
	Method method = new Method();// 存储方法的类
	Admin admin = new Admin();// 管理员类

	public boolean addAdminInfo(Admin a) {// a是界面填写的管理员信息
		//判断是否未输入管理员的相关信息
		if (!a.getName().equals("") && !a.getName().equals("请输入管理员姓名")) {
			if(!a.getPassword().equals("")&&!a.getPassword().equals("请输入密码"))
			{
				if(!a.getTel().equals("")&&!a.getTel().equals("请输入管理员电话"))
				{
					if(!a.getId().equals("")&&!a.getId().equals("请输入管理员身份证"))
					{
						if(!a.getAddress().equals("")&&!a.getAddress().equals("请输入管理员地址"))
						{
							admin.setName(a.getName());
							admin.setPassword(a.getPassword());
							admin.setGeneder(a.getGeneder());
							admin.setAddress(a.getAddress());
							admin.setTel(a.getTel());
							admin.setId(a.getId());
							admin.setPrivilege(a.getPrivilege());
							boolean i = method.addAdmin(admin);//添加管理员
							return i;
						}else
							JOptionPane.showMessageDialog(null, "地址不能为空！", "错误提示", JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(null, "身份证号码不能为空！", "错误提示", JOptionPane.ERROR_MESSAGE);
				}else
					JOptionPane.showMessageDialog(null, "电话不能为空！", "错误提示", JOptionPane.ERROR_MESSAGE);
			}else
				JOptionPane.showMessageDialog(null, "密码不能为空！", "错误提示", JOptionPane.ERROR_MESSAGE);
		}else
			JOptionPane.showMessageDialog(null, "姓名不能为空！", "错误提示", JOptionPane.ERROR_MESSAGE);
		return false;
	}
}