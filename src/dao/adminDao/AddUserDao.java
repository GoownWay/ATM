package dao.adminDao;

import javax.swing.JOptionPane;
import bean.Card;
import bean.User;
import dao.Method;

/**
 * @author hjx
 *管理员开户的dao
 */
public class AddUserDao {
	//判断界面传过来的数据是否输入了，不能不输入
	public boolean isNull(Card card, User user) {
		if(!card.getName().equals("")&&!card.getName().equals("例：张三"))
		{			
			if(!user.getGender().equals(""))
			{
				if(!card.getId().equals("")&&!card.getId().equals("请输入用户身份证"))
				{
					if(!user.getAddress().equals("")&&!user.getAddress().equals("请输入用户地址"))
					{
						if(!user.getTel().equals("")&&!user.getTel().equals("1300000xxxx"))
						{
							if(!card.getBank().equals("")&&!card.getBank().equals("请输入所属银行"))
							{
								return true;
							}
							else
								JOptionPane.showMessageDialog(null, "所属银行为空", "错误提示", JOptionPane.ERROR_MESSAGE);							
						}else
							JOptionPane.showMessageDialog(null, "电话不能为空", "错误提示", JOptionPane.ERROR_MESSAGE);
					}else
						JOptionPane.showMessageDialog(null, "地址不能为空", "错误提示", JOptionPane.ERROR_MESSAGE);
				}else
					JOptionPane.showMessageDialog(null, "身份证号码不能为空", "错误提示", JOptionPane.ERROR_MESSAGE);
			}else
				JOptionPane.showMessageDialog(null, "性别不能为空", "错误提示", JOptionPane.ERROR_MESSAGE);
		}else
			JOptionPane.showMessageDialog(null, "姓名不能为空", "错误提示", JOptionPane.ERROR_MESSAGE);
		return false;
	}

	public boolean isTwoPasswordSame(char[] firstPassword, char[] secondPassword) {
		String gotfirstPassword = String.valueOf(firstPassword);//获取第一次输入的密码
		String gotsecondPassword = String.valueOf(secondPassword);//获取第二次输入的密码
		if(gotfirstPassword.equals(gotsecondPassword))//判断两次密码输入是否正确
			return true;
		else
			JOptionPane.showMessageDialog(null, "两次新密码输入不一致", "错误提示", JOptionPane.ERROR_MESSAGE);
		return false;
	}

	//更新数据库
	public boolean insertDataBase(User newUser, Card newCard) {
		Method method = new Method();
		boolean i=true;
		User x = method.queryUser(newUser.getId());//通过身份证号码查询用户信息
		if(x.getId()==null)//判断用户表里是否已经存在用户信息，如果存在则不用插入，不存在则新插入一个用户
			i= method.insertUser(newUser);
		newCard.setPWDNum(0);
		newCard.setRemainMoney(0.00);
		newCard.setDesirable(20000);
		newCard.setPrivilege("正常");
		newCard.setTransferdesirable(200000.00);
		boolean j = method.inserCard(newCard);
		if(i&&j)
			return true;
		return false;
	}
}