package frame.superAdminFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.userDao.LoginDao;
import frame.LoginFrame;
import frame.userFrame.noCardDeposit.NoCardDepositFrame;
import frameInterface.FrameInterface;
import model.InputPasswordFrame;

/**
 * @author ckx
 *超级用户密码输入框
 */
public class SuperLoginInputPwdFrame implements ActionListener, FrameInterface {

	InputPasswordFrame frame;

	@Override
	public void init() {
		frame = new InputPasswordFrame();
		frame.show();//密码输入窗口显示
	}

	@Override
	public void show() {
		init();
	}

	@Override
	public void listen() {
		//监听按钮
		frame.frame.confirmButton.addActionListener(this);
		frame.frame.cancelButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<html><font size=6>取消</font><br>&nbsp;Cancel</html>")
		{//用户按了取消按钮
			LoginFrame login = new LoginFrame();
			login.show();
			login.listen();
			login.text.setText(NoCardDepositFrame.tex);//账号不变
			login.text.setForeground(Color.BLACK);
			frame.frame.dispose();//当前窗口关闭
		}
		else{//用户按了确认按钮
			LoginDao login = new LoginDao();
			//验证管理员或者超级用户的密码是否正确
			boolean i = login.isSuperPasswordCorrect(frame.c);
			if(i)
			{
				SuperLoginedFrame logined = new SuperLoginedFrame();
				logined.show();
				logined.listen();
				frame.frame.dispose();
			}
		}
	}
}