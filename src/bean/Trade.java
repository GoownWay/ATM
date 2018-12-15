package bean;

import java.sql.Timestamp;

/**
 * @author fzj
 *交易记录类
 */
public class Trade {
	private String id;//交易流水号
	private String account;//用户账号
	private String name;//用户姓名
	private Timestamp date;//交易时间
	private Double tradeSum;//交易金额
	private String transAccount;//交易账户
	private String type;//交易类型
	private Double ServiceCharge;
	private String remark;//备注

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date2) {
		this.date = date2;
	}
	public Double getTradeSum() {
		return tradeSum;
	}
	public void setTradeSum(Double tradeSum) {
		this.tradeSum = tradeSum;
	}
	public String getTransAccount() {
		return transAccount;
	}
	public void setTransAccount(String transAccount) {
		this.transAccount = transAccount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Double getServiceCharge() {
		return ServiceCharge;
	}
	public void setServiceCharge(Double serviceCharge) {
		ServiceCharge = serviceCharge;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}