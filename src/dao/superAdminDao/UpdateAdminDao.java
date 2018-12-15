package dao.superAdminDao;

import bean.Admin;
import dao.Method;
//更新管理员信息
public class UpdateAdminDao {
	Method method = new Method();//存储方法的类
	static Admin admin = new Admin();//管理员类
	
	public boolean updateAdminInfo(Admin a){//a是界面修改后的管理员信息
		admin.setAccount(a.getAccount());
		admin.setName(a.getName());
		admin.setPassword(a.getPassword());
		admin.setGeneder(a.getGeneder());
		admin.setAddress(a.getAddress());
		admin.setTel(a.getTel());
		admin.setId(a.getId());
		admin.setPrivilege(a.getPrivilege());
		if(admin == null){
			return false;
		}
		boolean i = method.updateAdmin(admin);//更新管理员信息
		return i;
	}
}