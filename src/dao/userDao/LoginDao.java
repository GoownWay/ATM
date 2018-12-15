package dao.userDao;

import javax.swing.JOptionPane;

import bean.Admin;
import bean.Card;
import dao.Method;
import frame.userFrame.UserLoginInputPasswordFrame;
import frame.userFrame.noCardDeposit.NoCardDepositFrame;

public class LoginDao {//登录的验证操作
	Method method = new Method();//储存方法的类
	static Admin admin = null;//管理员类

	//查询账号是否存在，返回值0代表普通用户，1代表管理员，2代表超级用户，-1表示都不存在,-2代表该用户处于冻结，挂失，注销或者该管理员处于离职状态
	public int queryIsExist(String tex) {
		if(tex.length()==19)
		{//账号长度为19位
			Card card=null;//银行卡对象
			card = method.queryCard(tex);//根据账号查询银行卡信息
			String privilege;//银行卡权限
			if(card.getAccount()!=null)
			{//银行卡的主键不为空即存在该账号
				privilege = card.getPrivilege();//状态
				if(privilege.equals("正常"))
					return 0;
				else
				{
					if(privilege.equals("冻结"))
						//冻结的话弹出弹窗
						JOptionPane.showMessageDialog(null, "该卡处于冻结状态，无法登录！", "错误提示", JOptionPane.ERROR_MESSAGE);
					else if(privilege.equals("挂失"))
						JOptionPane.showMessageDialog(null, "该卡已挂失，无法登录！", "错误提示", JOptionPane.ERROR_MESSAGE);
					else 
						JOptionPane.showMessageDialog(null, "该卡已注销！", "错误提示", JOptionPane.ERROR_MESSAGE);	
					return -2;
				}
			}
			else
				JOptionPane.showMessageDialog(null, "账号不存在，请检查是否输入有误", "错误提示", JOptionPane.ERROR_MESSAGE);
		}
		else if(tex.length()==8)//账号长度为8，判断管理员账号中是否存在
		{
			admin = method.queryAdmin(tex);//查询管理员信息
			String privilege;//管理员权限：管理员，超级用户，离职
			if(admin.getAccount()!=null)
			{//管理员表的主键不为空，即存在该账号
				privilege = admin.getPrivilege();//权限
				if(privilege.equals("管理员"))
					return 1;
				else
					if(privilege.equals("超级用户"))
						return 2;
					else
					{
						JOptionPane.showMessageDialog(null, "该账号不可用！", "错误提示", JOptionPane.ERROR_MESSAGE);
						return -2;
					}
			}else
				JOptionPane.showMessageDialog(null, "账号不存在，请检查是否输入有误！", "错误提示", JOptionPane.ERROR_MESSAGE);
		}else
			JOptionPane.showMessageDialog(null, "请输入正确的账号位数！", "错误提示", JOptionPane.ERROR_MESSAGE);
		return -1;
	}

	//判断用户登录时密码是否正确
	public int isPasswordCorrect(char[] c) {
		//验证用户密码是否正确，c是用户输入的密码
		Card card = method.queryCard(NoCardDepositFrame.tex);//查询卡的信息
		String password = card.getPassword();
		String gotPassword = String.valueOf(c);//用户输入的密码
		int pwdNum = card.getPWDNum();//获取密码错误数，如果错误数为3，则提示让其先去解冻

		if(gotPassword.equals(password)){
			pwdNum = 0;//密码正确后将密码错误数置为0
			card.setPWDNum(pwdNum);
			method.updateCard(card);//更新卡的信息
			return 1;
		}
		else
		{
			pwdNum++;//如果输入不正确，则密码错误数加1
			card.setPWDNum(pwdNum);
			if(pwdNum==3)					
			{//连续三次输入错误后，提示让用户去解冻后再登录
				JOptionPane.showMessageDialog(null, "该卡已3次输入密码错误，请先到柜台解冻！", "错误提示", JOptionPane.ERROR_MESSAGE);
				card.setPrivilege("冻结");
				method.updateCard(card);//更新卡信息
				return 3;
			}
			else
			{
				method.updateCard(card);//更新卡信息
				JOptionPane.showMessageDialog(null, "密码不正确！", "错误提示", JOptionPane.ERROR_MESSAGE);
				UserLoginInputPasswordFrame f = new UserLoginInputPasswordFrame();
				f.show();
				f.listen();
				return 0;
			}
		}
	}

	//管理员和超级用户的密码验证
	public boolean isSuperPasswordCorrect(char[] c) {
		String password = admin.getPassword();//获取管理员密码
		String gotPassword = String.valueOf(c);
		if(gotPassword.equals(password))//密码与输入密码比对
			return true;
		else
			JOptionPane.showMessageDialog(null, "密码不正确！", "错误提示", JOptionPane.ERROR_MESSAGE);
		return false;
	}
}