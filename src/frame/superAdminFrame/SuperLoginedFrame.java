package frame.superAdminFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import frame.WelcomeFrame;
import frame.superAdminFrame.addAdmin.AddAdminFrame;
import frame.superAdminFrame.deleteAdmin.DeleteAdminFrame;
import frame.superAdminFrame.updateAdmin.InputUpdateAccount;
import frameInterface.FrameInterface;
import model.MyButton;
import model.MyFrame;

/**
 * @author ckx
 *超级用户登录成功后的界面
 */
public class SuperLoginedFrame implements ActionListener, FrameInterface {

	MyButton delete, add, modify;
	MyFrame frame;
	JPanel panel;
	JLabel label;

	@Override
	public void init() {
		frame = new MyFrame("超级用户操作界面");
		panel = new JPanel();
		label = new JLabel("<html><font size=7>请选择业务</font><br><font size=5>Please select business</font></html>");
		add = new MyButton("<html><font size=6>增加管理员</font><br>&nbsp;&nbsp;Add Admin</html>");
		delete = new MyButton("<html><font size=6>注销管理员</font><br>&nbsp;&nbsp;Delete Admin</html>");
		modify = new MyButton("<html><font size=6>修改管理员</font><br>&nbsp;&nbsp;Modify Admin</html>");
	}

	@Override
	public void show() {
		init();// 加载组件
		panel.setLayout(null);// 将panel的布局设置为空
		panel.setOpaque(false);// 将panel的背景设置为透明，这样才能看到背景图片
		frame.confirmButton.setVisible(false);

		label.setForeground(Color.WHITE);// 将label的字体颜色设置为白色

		label.setBounds(390, 100, 400, 100);// 设置标签的大小和在窗口的位置
		add.setBounds(20, 250, 250, 60);
		delete.setBounds(700, 250, 250, 60);
		modify.setBounds(700, 450, 250, 60);
		frame.cancelButton.setBounds(20, 450, 250, 60);

		panel.add(label);
		panel.add(add);
		panel.add(delete);
		panel.add(modify);
		frame.cancelButton.setText("<html><font size=6>退出</font><br>&nbsp;Exit</html>");
		frame.getContentPane().add(panel);
		// 窗口可视化
		frame.setVisible(true);
	}

	@Override
	public void listen() {
		//按钮监听
		add.addActionListener(this);
		delete.addActionListener(this);
		modify.addActionListener(this);
		frame.cancelButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//按钮点击之后的操作
		if(e.getActionCommand().equals("<html><font size=6>退出</font><br>&nbsp;Exit</html>")){
			WelcomeFrame welcome = new WelcomeFrame();
			welcome.show();
			welcome.listen();
			frame.dispose();
		}
		if(e.getActionCommand().equals("<html><font size=6>增加管理员</font><br>&nbsp;&nbsp;Add Admin</html>")){
			AddAdminFrame addAdmin = new AddAdminFrame();
			addAdmin.show();
			addAdmin.listen();
			frame.dispose();
		}
		if(e.getActionCommand().equals("<html><font size=6>注销管理员</font><br>&nbsp;&nbsp;Delete Admin</html>")){
			DeleteAdminFrame deleteAdmin = new DeleteAdminFrame();
			deleteAdmin.show();
			deleteAdmin.listen();
			frame.dispose();
		}
		if(e.getActionCommand().equals("<html><font size=6>修改管理员</font><br>&nbsp;&nbsp;Modify Admin</html>")){
			InputUpdateAccount updateAdmin = new InputUpdateAccount();
			updateAdmin.show();
			updateAdmin.listen();
			frame.dispose();
		}
	}
}