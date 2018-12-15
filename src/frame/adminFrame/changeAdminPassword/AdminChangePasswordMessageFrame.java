package frame.adminFrame.changeAdminPassword;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import frame.LoginFrame;
import frame.WelcomeFrame;
import frame.adminFrame.AdminLoginedFrame;
import frameInterface.FrameInterface;
import model.MessageFrame;

/**
 * @author hjx
 *管理员改密码成功界面
 */
public class AdminChangePasswordMessageFrame implements FrameInterface,ActionListener{
	public MessageFrame frame;

	@Override
	public void init() {
		frame = new MessageFrame();
	}

	@Override
	public void show() {
		init();
		frame.show();
		frame.frame.confirmButton.setText("<html><font size=6>退出</font><br>&nbsp;&nbsp;&nbsp;Exit</html>");
	}

	@Override
	public void listen() {
		frame.frame.cancelButton.addActionListener(this);
		frame.frame.confirmButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//按了返回就返回到用户登录后的界面
		if(e.getActionCommand().equals("<html><font size=6>返回</font><br>&nbsp;Cancel</html>"))
		{
			AdminLoginedFrame d = new AdminLoginedFrame();
			d.show();
			d.listen();
			frame.frame.dispose();
		}else
		{
			WelcomeFrame f = new WelcomeFrame();
			f.show();
			f.listen();
			LoginFrame.loginedAccount = null;
			frame.frame.dispose();
		}
	}
}