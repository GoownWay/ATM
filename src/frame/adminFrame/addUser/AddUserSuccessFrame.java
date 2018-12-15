package frame.adminFrame.addUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import frame.LoginFrame;
import frame.WelcomeFrame;
import frame.adminFrame.AdminLoginedFrame;
import frameInterface.FrameInterface;
import model.MessageFrame;

/**
 * @author hjx
 *添加用户成功界面
 */
public class AddUserSuccessFrame  implements FrameInterface,ActionListener{
	public MessageFrame frame;
	@Override
	public void init() {
		frame = new MessageFrame();
	}

	@Override
	public void show() {
		init();
		frame.show();
		frame.frame.cancelButton.setText("<html><font size=6>返回</font><br>&nbsp;Cancel</html>");
		frame.frame.confirmButton.setText("<html><font size=6>退出</font><br>&nbsp;Exit</html>");
	}

	@Override
	public void listen() {
		frame.frame.cancelButton.addActionListener(this);
		frame.frame.confirmButton.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "<html><font size=6>返回</font><br>&nbsp;Cancel</html>") {
			AdminLoginedFrame f = new AdminLoginedFrame();
			f.show();
			f.listen();
			frame.frame.dispose();
		}
		else
		{
			WelcomeFrame f = new WelcomeFrame();
			f.show();
			f.listen();
			LoginFrame.loginedAccount = null;
			frame.frame.dispose();
		}
	}
}