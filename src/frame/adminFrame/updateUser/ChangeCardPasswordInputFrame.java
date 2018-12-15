package frame.adminFrame.updateUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import frameInterface.FrameInterface;
import model.InputPasswordFrame;

/**
 * @author hjx
 *改密码的输入新密码界面
 */
public class ChangeCardPasswordInputFrame implements FrameInterface, ActionListener {
	public InputPasswordFrame frame;
	String cardAccount;
	public ChangeCardPasswordInputFrame(String text) {
		cardAccount = text;
	}

	@Override
	public void init() {
		frame = new InputPasswordFrame();
		frame.show();
	}

	@Override
	public void show() {
		init();//加载组件
		frame.label.setText("<html><font size=7>请输入新密码</font><br><font size=5>Please enter a new password</font></html>");
		frame.label.setBounds(355, 180, 300, 70);//设置标签的大小和在窗口的位置;
	}

	//监听组件
	@Override
	public void listen() {
		frame.frame.cancelButton.addActionListener(this);
		frame.frame.confirmButton.addActionListener(this);
	}

	//对监听的组件作出反应
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html><font size=6>取消</font><br>&nbsp;Cancel</html>"))
		{
			SelectUpdateTypeFrame f = new SelectUpdateTypeFrame();
			f.show();
			f.listen();
			frame.frame.dispose();
		}
		if(e.getActionCommand().equals("<html><font size=6>确认</font><br>&nbsp;Comfirm</html>"))
		{
			ConfirmCardPasswordInputFrame f = new ConfirmCardPasswordInputFrame(cardAccount,frame.c);
			f.show();
			f.listen();
			frame.frame.dispose();
		}
	}
}