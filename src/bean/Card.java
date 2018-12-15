package bean;

import java.sql.Timestamp;

/**
 * @author fzj
 *银行卡类
 */
public class Card {
	private String account;//银行卡账号
	private String id;//身份证号码
	private String bank;//所属银行
	private String name;//储户姓名
	private String password;//银行卡密码
	private Timestamp date;//注册时间
	private Double remainMoney;//余额
	private int PWDNum;//密码错误数
	private long desirable;//当日剩余可取余额
	private String privilege;//状态
	private Double Transferdesirable;//当日剩余可转账余额
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
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
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public Double getRemainMoney() {
		return remainMoney;
	}
	public void setRemainMoney(Double remainMoney) {
		this.remainMoney = remainMoney;
	}
	public int getPWDNum() {
		return PWDNum;
	}
	public void setPWDNum(int pWDNum) {
		PWDNum = pWDNum;
	}
	public long getDesirable() {
		return desirable;
	}
	public void setDesirable(long desirable) {
		this.desirable = desirable;
	}
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	public Double getTransferdesirable() {
		return Transferdesirable;
	}
	public void setTransferdesirable(Double transferdesirable) {
		Transferdesirable = transferdesirable;
	}	
}