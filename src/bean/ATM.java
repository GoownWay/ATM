package bean;

/**
 * @author fzj
 *atm类
 */
public class ATM {
	private String ATMID;//机号
	private long ATMRemainMoney;//余额
	private long ATMTakeMoney;//用户取走金额
	private long ATMDepositMoney;//储户存入金额
	private Double ATMTransferMoney;//储户转账金额
	private long BeUsedTimes;//ATM机的使用次数
	public String getATMID() {
		return ATMID;
	}
	public void setATMID(String aTMID) {
		ATMID = aTMID;
	}
	public long getATMRemainMoney() {
		return ATMRemainMoney;
	}
	public void setATMRemainMoney(long aTMRemainMoney) {
		ATMRemainMoney = aTMRemainMoney;
	}
	public long getATMTakeMoney() {
		return ATMTakeMoney;
	}
	public void setATMTakeMoney(long aTMTakeMoney) {
		ATMTakeMoney = aTMTakeMoney;
	}
	public long getATMDepositMoney() {
		return ATMDepositMoney;
	}
	public void setATMDepositMoney(long aTMDepositMoney) {
		ATMDepositMoney = aTMDepositMoney;
	}
	public Double getATMTransferMoney() {
		return ATMTransferMoney;
	}
	public void setATMTransferMoney(Double aTMTransferMoney) {
		ATMTransferMoney = aTMTransferMoney;
	}
	public long getBeUsedTimes() {
		return BeUsedTimes;
	}
	public void setBeUsedTimes(long beUsedTimes) {
		BeUsedTimes = beUsedTimes;
	}
}