package frame.adminFrame.updateUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import frameInterface.FrameInterface;
import model.MessageFrame;

/**
 * @author hjx
 *改密码成功界面
 */
public class ChangeCardPasswordMessageFrame implements FrameInterface,ActionListener{
	public MessageFrame frame;

	@Override
	public void init() {
		frame = new MessageFrame();
	}

	@Override
	public void show() {
		init();
		frame.show();
		frame.frame.confirmButton.setVisible(false);
		frame.frame.cancelButton.setBounds(700,550,250,60);
	}

	@Override
	public void listen() {//按钮添加监听
		frame.frame.cancelButton.addActionListener(this);
		frame.frame.confirmButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//按了返回就返回到用户登录后的界面
		if(e.getActionCommand().equals("<html><font size=6>返回</font><br>&nbsp;Cancel</html>"))
		{
			SelectUpdateTypeFrame d = new SelectUpdateTypeFrame();
			d.show();
			d.listen();
			frame.frame.dispose();
		}
	}
}