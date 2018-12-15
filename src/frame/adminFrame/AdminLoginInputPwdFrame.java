package frame.adminFrame;

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
 *管理员登录密码输入界面
 */
public class AdminLoginInputPwdFrame implements FrameInterface,ActionListener{
	InputPasswordFrame frame;

	@Override
	public void init() {
		frame = new InputPasswordFrame();
		frame.show();
	}

	@Override
	public void show() {
		init();
	}

	@Override
	public void listen() {
		frame.frame.confirmButton.addActionListener(this);
		frame.frame.cancelButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<html><font size=6>取消</font><br>&nbsp;Cancel</html>")
		{//按下取消按钮
			LoginFrame login = new LoginFrame();
			login.show();
			login.listen();
			login.text.setText(NoCardDepositFrame.tex);
			login.text.setForeground(Color.BLACK);
			frame.frame.dispose();
		}
		else{//按下确认按钮
			LoginDao login = new LoginDao();
			//验证管理员密码是否正确
			boolean i = login.isSuperPasswordCorrect(frame.c);
			if(i)
			{
				LoginFrame.loginedAccount = NoCardDepositFrame.tex;
				AdminLoginedFrame logined = new AdminLoginedFrame();
				logined.show();
				logined.listen();
				frame.frame.dispose();
			}
		}
	}
}