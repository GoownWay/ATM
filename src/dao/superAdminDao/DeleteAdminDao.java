package dao.superAdminDao;

import dao.DbBean;
import dao.Method;
//注销管理员
public class DeleteAdminDao {
	Method method = new Method();//存储方法的类
	final String privilege = "离职";

	public boolean updateAdminInfo(String account){//account为要注销的管理员帐号
		String sql = "update admin_info set adminPrivilege = '"+privilege+"' where adminAccount = '"+account+"'";

		DbBean db = new DbBean();
		db.openConnection();//打开数据库连接
		int i = db.executeUpdate(sql);//更新管理员信息，将其状态改为“离职”
		db.closeConnection();//关闭数据库连接
		if(i==1)
			return true;
		else
			return false;
	}
}