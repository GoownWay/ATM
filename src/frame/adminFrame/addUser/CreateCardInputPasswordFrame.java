package frame.adminFrame.addUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bean.Card;
import bean.User;
import frameInterface.FrameInterface;
import model.InputPasswordFrame;

/**
 * @author hjx
 *开卡输入新密码界面
 */
public class CreateCardInputPasswordFrame implements FrameInterface, ActionListener {
	public InputPasswordFrame frame;
	Card card;
	User user;
	public CreateCardInputPasswordFrame(User newUser, Card newCard) {
		card = newCard;
		user = newUser;
	}

	@Override
	public void init() {
		frame = new InputPasswordFrame();
		frame.show();
	}

	@Override
	public void show() {
		init();//加载组件
		frame.label.setText("<html><font size=7>请输入6位密码</font><br><font size=5>Please enter a new password</font></html>");
		frame.label.setBounds(355, 180, 300, 70);//设置标签的大小和在窗口的位置;
		frame.frame.cancelButton.setVisible(false);
	}

	//监听组件
	@Override
	public void listen() {
		//frame.frame.cancelButton.addActionListener(this);
		frame.frame.confirmButton.addActionListener(this);
	}

	//对监听的组件作出反应
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html><font size=6>确认</font><br>&nbsp;Comfirm</html>"))
		{	
			CreateCardConfirmPasswordFrame f = new CreateCardConfirmPasswordFrame(user,card,frame.c);
			f.show();
			f.listen();
			frame.frame.dispose();
		}
	}
}