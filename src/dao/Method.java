package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.ATM;
import bean.Admin;
import bean.Card;
import bean.Trade;
import bean.User;

public class Method {

	//查询ATM机的信息
	public ATM queryATM(){
		String sql = "select * from atm_info";//查询atm信息的sql语句
		DbBean db = new DbBean();//JDBC类
		db.openConnection();//连接数据库
		ResultSet rs = db.executeQuery(sql);//查询atm机信息返回结果集
		ATM atm = new ATM();
		try {
			while(rs.next()){
				String ATMID = rs.getString("ATMID");//atm的机号
				Long ATMRemainMoney = rs.getLong("ATMRemainMoney");//atm余额
				Long ATMTakeMoney = rs.getLong("ATMTakeMoney");//atm取走金额
				Long ATMDepositMoney=rs.getLong("ATMDepositMoney");//atm的累计存款金额数
				Double ATMTransferMoney = rs.getDouble("ATMTransferMoney");//atm累计转账金额
				Long BeUseTimes=rs.getLong("BeUseTimes");//atm的使用次数
				atm.setATMID(ATMID);
				atm.setATMRemainMoney(ATMRemainMoney);
				atm.setATMTakeMoney(ATMTakeMoney);
				atm.setATMDepositMoney(ATMDepositMoney);
				atm.setATMTransferMoney(ATMTransferMoney);
				atm.setBeUsedTimes(BeUseTimes);
			}
			db.closeConnection();//关闭数据库连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atm;
	}

	//更新atm信息
	public boolean updateATM(ATM atm){
		String ATMID = atm.getATMID();//机号
		long ATMRemainMoney = atm.getATMRemainMoney();//atm余额
		long ATMTakeMoney = atm.getATMTakeMoney();//用户取走金额
		long ATMDepositMoney = atm.getATMDepositMoney();//储户存入金额
		Double ATMTransferMoney = atm.getATMTransferMoney();//储户转账金额
		long BeUsedTimes = atm.getBeUsedTimes();//ATM机的使用次数)

		String sql = "update atm_info set ATMID='"+ATMID+"',ATMRemainMoney='"+ATMRemainMoney+"',"
				+ "ATMTakeMoney='"+ATMTakeMoney+"',ATMDepositMoney='"+ATMDepositMoney+"',"
				+ "ATMTransferMoney='"+ATMTransferMoney+"',BeUseTimes='"+BeUsedTimes+"'";

		DbBean db = new DbBean();
		db.openConnection();//连接数据库
		int i = db.executeUpdate(sql);//存款后，插入一条交易记录
		db.closeConnection();//关闭数据库连接
		if(i==1)
			return true;
		else
			return false;
	}

	//查询卡信息
	public Card queryCard(String tex){
		String sql = "select * from card_info where cardAccount ='"+tex+"'";//根据账号查询card信息
		DbBean db = new DbBean();
		Card card = new Card();
		db.openConnection();//连接数据库
		ResultSet rs = db.executeQuery(sql);//查询card信息返回结果集
		try {
			while(rs.next()){
				String account = rs.getString("cardAccount");//账号
				String id = rs.getString("userID");//身份证号
				String name = rs.getString("userName");//用户名
				String bank = rs.getString("cardBank");//所属银行
				String password = rs.getString("cardPWD");//银行卡密码
				Timestamp date = rs.getTimestamp("cardRegisterTime");//注册时间
				Double remainMoney = rs.getDouble("cardRemainMoney");//余额
				int PWDNum = rs.getInt("cardPWDNum");//密码错误数
				long desirable = rs.getLong("cardDesirable");//当日剩余可取余额
				String privilege = rs.getString("cardPrivilege");//状态
				Double transferdesirable = rs.getDouble("cardTransferDesirable");//当日剩余可转账余额
				card.setAccount(account);
				card.setId(id);
				card.setName(name);
				card.setBank(bank);
				card.setPassword(password);
				card.setDate(date);
				card.setRemainMoney(remainMoney);
				card.setPWDNum(PWDNum);
				card.setDesirable(desirable);
				card.setPrivilege(privilege);
				card.setTransferdesirable(transferdesirable);
			}
			db.closeConnection();//关闭数据库连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return card;
	}

	//更新卡信息
	public boolean updateCard(Card card){
		String account = card.getAccount();//账号
		String id = card.getId();//身份证号
		String name = card.getName();//姓名
		String bank = card.getBank();//所属银行
		String password = card.getPassword();//银行卡密码

		String date = card.getDate().toString().substring(0,19);//注册时间

		Double remainMoney = card.getRemainMoney();//余额
		int PWDNum = card.getPWDNum();//密码错误数
		long desirable = card.getDesirable();//当日剩余可取余额
		String privilege = card.getPrivilege();//状态
		Double transferDesirable = card.getTransferdesirable();//当日剩余可转账数

		String sql = "update card_info set cardAccount='"+account+"',userID='"+id+"',userName='"+name+"',"
				+ "cardBank='"+bank+"',cardPWD='"+password+"',cardRegisterTime='"+date+"',cardRemainMoney='"+remainMoney+"',"
				+ "cardPWDNum='"+PWDNum+"',cardDesirable='"+desirable+"',cardPrivilege='"+privilege+"',"
				+ "cardTransferDesirable='"+transferDesirable+ "' where cardAccount='"+account+"'";

		DbBean db = new DbBean();
		db.openConnection();//连接数据库
		int i = db.executeUpdate(sql);//存款后，插入一条交易记录
		db.closeConnection();//关闭数据库连接
		if(i==1)
			return true;
		else
			return false;
	}

	//生成交易信息方法
	public boolean updateTradeInfo(Trade trade){
		String id = trade.getId();//交易记录流水号
		String userAccount = trade.getAccount();//账户
		String userName = trade.getName();//交易账号的姓名
		Timestamp tradeTime = trade.getDate();//交易时间
		Double tradeSum = trade.getTradeSum();//交易金额
		String transAccount = trade.getTransAccount();//交易账号
		String type = trade.getType();//交易类型
		Double ServiceCharge = trade.getServiceCharge();//手续费
		String remark = trade.getRemark();//交易备注

		String sql = "insert into trade_info(ID,userAccount,userName,tradeTime,tradeSum,transAccount,type,ServiceCharge,remark) "
				+ "values('"+id+"','"+userAccount+"','"+userName+"','"+tradeTime+"','"+tradeSum+"','"+transAccount+"','"+type+"','"+ServiceCharge+"','"+remark+"')";
		DbBean db = new DbBean();
		db.openConnection();//打开数据库连接
		int i = db.executeUpdate(sql);//存款后，插入一条交易记录
		db.closeConnection();//关闭数据库连接
		if(i==1)
			return true;
		else
			return false;
	}

	//查询交易明细
	public List<Trade> queryTrade(String loginedAccount, Timestamp startDate, Timestamp endDate) {
		List<Trade> list = new ArrayList<Trade>();//trade类型的List集合
		String sql = "select * from trade_info where userAccount='"+loginedAccount+"' and tradeTime>="
				+ "'"+startDate+"' and tradeTime<='"+endDate+"' order by tradeTime desc;";
		DbBean db = new DbBean();
		db.openConnection();//连接数据库
		ResultSet rs = db.executeQuery(sql);//按时间和账号查询交易记录返回结果集
		try {
			while(rs.next())
			{
				String id = rs.getString("ID");//交易流水号
				String account = rs.getString("userAccount");//用户账号
				String name = rs.getString("userName");//交易账号姓名
				Timestamp date = rs.getTimestamp("tradeTime");//交易时间
				Double tradeSum = rs.getDouble("tradeSum");//交易金额
				String transAccount = rs.getString("transAccount");//交易账户
				String type = rs.getString("type");//交易类型
				Double serviceCharge = rs.getDouble("ServiceCharge");//手续费
				String remark = rs.getString("remark");//备注

				Trade td = new Trade();
				td.setId(id);
				td.setAccount(account);
				td.setName(name);
				td.setDate(date);
				td.setTradeSum(tradeSum);
				td.setTransAccount(transAccount);
				td.setType(type);
				td.setServiceCharge(serviceCharge);
				td.setRemark(remark);

				list.add(td);//添加到list集合中
			}
			db.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	//自动生成新管理员账号
	public static String bornAdminAccount(){
		String newAccount = null;
		String sql = "select adminAccount from admin_info order by adminAccount desc limit 1";
		DbBean db = new DbBean();
		db.openConnection();//连接数据库
		ResultSet rs = db.executeQuery(sql);//查询管理员账号信息
		try {
			while(rs.next()){
				newAccount = String.valueOf(Long.parseLong(rs.getString("adminAccount"))+1);
			}
			db.closeConnection();//关闭数据库连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newAccount;//返回新账号
	}

	//添加管理员
	public boolean addAdmin(Admin admin){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String account = Method.bornAdminAccount();//生成管理员账户
		String name = admin.getName();//姓名
		String password = admin.getPassword();//密码
		String gender = admin.getGeneder();//性别
		String address = admin.getAddress();//地址
		String id = admin.getId();//身份证号码
		String tel = admin.getTel();//电话
		String rigisterDate = dateFormat.format(now).substring(0,19);//注册时间
		String privilege = admin.getPrivilege();//状态

		String sql = "insert into admin_info values('"+account+"','"+name+"','"+password+"','"+gender+"','"+address+"','"+tel+"','"+id+"','"+rigisterDate+"','"+privilege+"')";

		DbBean db = new DbBean();
		db.openConnection();//连接数据库
		int i = db.executeUpdate(sql);//插入一条管理员记录
		db.closeConnection();//关闭数据库
		if(i==1)
			return true;
		else
			return false;
	}

	//查询管理员信息
	public Admin queryAdmin(String tex) {
		String sql = "select * from admin_info where adminAccount ='"+tex+"'";//根据账号查询管理员信息
		DbBean db = new DbBean();
		Admin admin = new Admin();
		db.openConnection();//连接数据库
		ResultSet rs = db.executeQuery(sql);//查询管理员信息
		try {
			while(rs.next()){
				String account = rs.getString("adminAccount");//管理员账户
				String name = rs.getString("adminName");//姓名
				String password = rs.getString("adminPWD");//密码
				String gender = rs.getString("adminGender");//性别
				String address = rs.getString("adminAddress");//地址
				String id = rs.getString("adminID");//身份证号码
				String tel = rs.getString("adminTel");//电话
				Timestamp rigisterDate = rs.getTimestamp("adminRegisterTime");//注册时间
				String privilege = rs.getString("adminPrivilege");//状态
				admin.setAccount(account);
				admin.setName(name);
				admin.setPassword(password);
				admin.setGeneder(gender);
				admin.setAddress(address);
				admin.setId(id);
				admin.setTel(tel);
				admin.setRigisterDate(rigisterDate);
				admin.setPrivilege(privilege);
			}
			db.closeConnection();//关闭数据库连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

	//更新管理员信息
	public boolean updateAdmin(Admin admin){
		String account = admin.getAccount();//账户
		String name = admin.getName();//姓名
		String password = admin.getPassword();//密码
		String gender = admin.getGeneder();//性别
		String address = admin.getAddress();//地址
		String id = admin.getId();//身份证号码
		String tel = admin.getTel();//电话
		String privilege = admin.getPrivilege();//状态

		String sql = "update admin_info set adminName = '"+name+"',adminPWD = '"+password+"',adminGender = '"+gender+"',"
				+"adminAddress = '"+address+"',adminTel = '"+tel+"',adminID = '"+id+"',adminPrivilege = '"+privilege+"'"
				+ " where adminAccount = '"+account+"'";

		DbBean db = new DbBean();
		db.openConnection();//连接数据库
		int i = db.executeUpdate(sql);//更新管理员信息，将其状态改为“离职”
		db.closeConnection();//关闭数据库
		if(i==1)
			return true;
		else
			return false;
	}

	//自动产生user账号
	public String createUserAccount() {
		String newAccount = null;
		String sql = "select cardAccount from card_info order by cardAccount desc limit 1";
		DbBean db = new DbBean();
		db.openConnection();//连接数据库
		ResultSet rs = db.executeQuery(sql);//查询管理员账号信息
		try {
			while(rs.next()){
				newAccount = String.valueOf(Long.parseLong(rs.getString("cardAccount"))+1);
			}
			db.closeConnection();//关闭数据库连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newAccount;
	}

	//添加新用户
	public boolean insertUser(User newUser) {
		String id = newUser.getId();//身份证号
		String name = newUser.getName();//姓名
		String gender = newUser.getGender();//性别
		String address = newUser.getAddress();//地址
		String tel = newUser.getTel();//电话

		String sql = "insert into user_info values('"+id+"','"+name+"','"+gender+"',"
				+ "'"+address+"','"+tel+"')";

		DbBean db = new DbBean();
		db.openConnection();//连接数据库
		int i = db.executeUpdate(sql);//添加新用户
		db.closeConnection();
		if(i==1)
			return true;
		else
			return false;

	}

	//添加新的卡
	public boolean inserCard(Card newCard) {
		String account = newCard.getAccount();//账号
		String id = newCard.getId();//身份证号
		String name = newCard.getName();//姓名
		String bank = newCard.getBank();//所属银行
		String password = newCard.getPassword();//银行卡密码
		String date = newCard.getDate().toString().substring(0,19);//注册时间
		Double remainMoney = newCard.getRemainMoney();//余额
		int PWDNum = newCard.getPWDNum();//密码错误数
		long desirable = newCard.getDesirable();//当日剩余可取余额
		String privilege = newCard.getPrivilege();//状态
		Double transferDesirable = newCard.getTransferdesirable();//当日可转账余额

		String sql = "insert into card_info values('"+account+"','"+id+"','"+name+"',"
				+ "'"+bank+"','"+password+"','"+date+"','"+remainMoney+"',"
				+ "'"+PWDNum+"','"+desirable+"','"+privilege+"',"
				+ "'"+transferDesirable+"')";

		DbBean db = new DbBean();
		db.openConnection();//连接数据库
		int i = db.executeUpdate(sql);//添加新卡
		db.closeConnection();//关闭数据库连接
		if(i==1)
			return true;
		else
			return false;
	}

	//通过身份证号查询用户预留信息
	public User queryUser(String id) {
		String sql = "select * from user_info where userID = '"+id+"'";
		DbBean db = new DbBean();
		db.openConnection();//连接数据库
		ResultSet i = db.executeQuery(sql);//查询用户信息返回结果集
		User user = new User();
		try {
			if(i.next())
			{
				user.setId(id);//身份证号
				user.setName(i.getString("userName"));//姓名
				user.setGender(i.getString("userGender"));//性别
				user.setAddress(i.getString("userAddress"));//地址
				user.setTel(i.getString("userTel"));//电话
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();//关闭数据库连接
		return user;
	}

	//更新用户预留信息
	public boolean updateUser(User user){
		String id = user.getId();//身份证号
		String name = user.getName();//姓名
		String gender = user.getGender();//性别
		String address = user.getAddress();//地址
		String tel = user.getTel();//电话

		String sql = "update user_info set userID='"+id+"',userName='"+name+"',"
				+ "userGender='"+gender+"',userAddress='"+address+"',userTel='"+tel+"' where userID='"+id+"'";

		DbBean db = new DbBean();
		db.openConnection();//连接数据库
		int i = db.executeUpdate(sql);
		db.closeConnection();//关闭数据库连接
		if(i==1)
			return true;
		else
			return false;
	}
}