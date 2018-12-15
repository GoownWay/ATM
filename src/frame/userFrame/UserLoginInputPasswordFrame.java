package frame.userFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.userDao.LoginDao;
import frame.LoginFrame;
import frame.WelcomeFrame;
import frame.userFrame.noCardDeposit.NoCardDepositFrame;
import frameInterface.FrameInterface;
import model.InputPasswordFrame;

/**
 * @author ckx
 *用户登录时密码输入界面
 */
public class UserLoginInputPasswordFrame implements FrameInterface, ActionListener{

	InputPasswordFrame frame;

	@Override
	public void init() {
		frame = new InputPasswordFrame();
		frame.show();//显示密码输入窗口
	}

	@Override
	public void show() {
		init();
	}

	@Override
	public void listen() {
		//按钮添加监听
		frame.frame.confirmButton.addActionListener(this);
		frame.frame.cancelButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<html><font size=6>取消</font><br>&nbsp;Cancel</html>")
		{//按下了取消按钮
			LoginFrame login = new LoginFrame();
			login.show();
			login.listen();
			login.text.setText(NoCardDepositFrame.tex);
			login.text.setForeground(Color.BLACK);
			frame.frame.dispose();
		}
		else{//按下确认按钮
			LoginDao login = new LoginDao();
			//用户密码验证
			int i = login.isPasswordCorrect(frame.c);
			if(i==1)
			{//密码正确后操作
				LoginFrame.loginedAccount = NoCardDepositFrame.tex;
				LoginedFrame logined = new LoginedFrame();
				logined.show();
				logined.listen();
			}
			else
				if(i==3)
				{//三次密码错误后，冻结卡跳转到欢迎界面
					WelcomeFrame f = new WelcomeFrame();
					f.show();
					f.listen();
				}
			frame.frame.dispose();
		}
	}
}