package frame.adminFrame.updateUser;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import frame.WelcomeFrame;
import frame.adminFrame.AdminLoginedFrame;
import frameInterface.FrameInterface;
import model.MyButton;
import model.MyFrame;

/**
 * @author hjx
 * 用户更新功能选择
 */
public class SelectUpdateTypeFrame implements FrameInterface,ActionListener {
	MyButton updateUserInfo,updateCardPw;
	MyFrame frame;
	JPanel panel;
	JLabel label;

	@Override
	public void init() {
		frame = new MyFrame("修改用户信息");
		panel = new JPanel();
		label = new JLabel("<html><font size=7>请选择业务</font><br><font size=5>Please select business</font></html>");

		updateCardPw = new MyButton("<html><font size=6>修改卡密码</font><br>Modify card password</html>");
		updateUserInfo = new MyButton("<html><font size=6>修改用户信息</font><br>Modify user information</html>");

		frame.cancelButton.setText("<html><font size=6>返回</font><br>&nbsp; Back</html>");
		frame.confirmButton.setText("<html><font size=6>退出</font><br>&nbsp; Back</html>");
	}

	@Override
	public void show() {
		init();//加载组件
		panel.setLayout(null);//将panel的布局设置为空

		label.setForeground(Color.WHITE);//将label的字体颜色设置为白色

		label.setBounds(390, 150, 400, 100);//设置标签的大小和在窗口的位置
		//第一列
		updateCardPw.setBounds(20, 350, 250, 60);
		updateUserInfo.setBounds(700, 350, 250, 60);

		panel.setOpaque(false);//将panel的背景设置为透明，这样才能看到背景图片

		//添加组件
		panel.add(label);
		panel.add(updateCardPw);
		panel.add(updateUserInfo);

		frame.getContentPane().add(panel);
		//窗口可视化
		frame.setVisible(true);
	}

	@Override
	public void listen() {
		updateCardPw.addActionListener(this);
		updateUserInfo.addActionListener(this);
		frame.confirmButton.addActionListener(this);
		frame.cancelButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "<html><font size=6>修改卡密码</font><br>Modify card password</html>":{
			UpdateCardPwInputAccountFrame f = new UpdateCardPwInputAccountFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>修改用户信息</font><br>Modify user information</html>":{
			UpdateUserInputFrame f = new UpdateUserInputFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>返回</font><br>&nbsp; Back</html>": {
			AdminLoginedFrame f = new AdminLoginedFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>退出</font><br>&nbsp; Back</html>": {
			WelcomeFrame f = new WelcomeFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		}
	}
}