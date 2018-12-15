package dao.adminDao;

import javax.swing.JOptionPane;

import bean.ATM;
import bean.Card;
import dao.Method;

public class UpdateATMAndQueryUserTransferDao {
	Method method = new Method();
	ATM atm = method.queryATM();//查询atm信息
	//更新atm信息
	public boolean updateATM(String updateMoney) {
		if(!updateMoney.equals("") && (!updateMoney.equals("请输入更新的现金数:")))
		{
			if(Long.parseLong(updateMoney) <= 100000 && Long.parseLong(updateMoney) >= 1000 && Long.parseLong(updateMoney) %100==0)
			{
				atm.setATMRemainMoney(Long.parseLong(updateMoney)+atm.getATMRemainMoney());
				method.updateATM(atm);//更新atm信息
				return true;
			}else
				JOptionPane.showMessageDialog(null, "请输入1000—1000000之间的整百数！", "错误提示", JOptionPane.ERROR_MESSAGE);
		}else
			JOptionPane.showMessageDialog(null, "更新金额不能为空，请输入存入金额！", "错误提示", JOptionPane.ERROR_MESSAGE);
		return false;
	}

	//查询atm余额
	public Long queryATMRemainMoney() {
		return atm.getATMRemainMoney();
	}

	//查询账号是否存在
	public boolean queryIsExist(String tex) {
		if(tex.length()==19)
		{//账号长度为19位
			Card card=null;//银行卡对象
			card = method.queryCard(tex);//根据账号查询银行卡信息
			if(card.getAccount()!=null)//银行卡的主键不为空即存在该账号
				return true;
			else
				JOptionPane.showMessageDialog(null, "账号不存在，请检查是否输入有误", "错误提示", JOptionPane.ERROR_MESSAGE);
		}else
			JOptionPane.showMessageDialog(null, "请输入19位账号！", "错误提示", JOptionPane.ERROR_MESSAGE);
		return false;
	}
}