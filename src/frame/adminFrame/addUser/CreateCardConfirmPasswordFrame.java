package frame.adminFrame.addUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import bean.Card;
import bean.User;
import dao.adminDao.AddUserDao;
import frameInterface.FrameInterface;
import model.InputPasswordFrame;

/**
 * @author hjx
 *开卡新密码确认界面
 */
public class CreateCardConfirmPasswordFrame implements FrameInterface, ActionListener{
	public InputPasswordFrame frame;
	Card newCard;
	User newUser;
	char[] firstPassword;

	public CreateCardConfirmPasswordFrame(User user, Card card, char[] c) {
		newCard = card;
		newUser = user;
		firstPassword=c;
	}

	@Override
	public void init() {
		frame = new InputPasswordFrame();
		frame.show();
	}

	@Override
	public void show() {
		init();//加载组件
		frame.label.setText("<html><font size=7>请再次输入6位密码</font><br><font size=5>&nbsp;&nbsp;&nbsp;Please enter a new password</font></html>");
		frame.label.setBounds(335, 180, 350, 70);//设置标签的大小和在窗口的位置;
		frame.frame.cancelButton.setVisible(false);
	}

	@Override
	public void listen() {
		frame.frame.confirmButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html><font size=6>确认</font><br>&nbsp;Comfirm</html>"))
		{
			AddUserDao cp = new AddUserDao();
			//调用方法，判断两次新密码输入是否相同，不相同则重新输入，相同就更新到数据库
			boolean i = cp.isTwoPasswordSame(firstPassword,frame.c);
			if(i)
			{	
				newCard.setPassword(String.valueOf(firstPassword));
				boolean j = cp.insertDataBase(newUser,newCard);
				if(j)
				{	
					AddUserSuccessFrame f = new AddUserSuccessFrame();
					f.show();
					f.listen();
					this.frame.frame.dispose();
				}
			}
			else
			{
				CreateCardInputPasswordFrame f = new CreateCardInputPasswordFrame(newUser,newCard);
				f.show();
				f.listen();
				frame.frame.dispose();
			}
		}
	}
}