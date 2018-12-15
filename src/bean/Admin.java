package bean;

import java.sql.Timestamp;

/**
 * @author fzj
 *管理员类
 */
public class Admin {
	private String account;//账户
	private String name;//姓名
	private String password;//密码
	private String gender;//性别
	private String address;//地址
	private String id;//身份证号码
	private String tel;//电话
	private Timestamp rigisterDate;//注册时间
	private String privilege;//状态

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGeneder() {
		return gender;
	}
	public void setGeneder(String geneder) {
		this.gender = geneder;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Timestamp getRigisterDate() {
		return rigisterDate;
	}
	public void setRigisterDate(Timestamp rigisterDate) {
		this.rigisterDate = rigisterDate;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
}