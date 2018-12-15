package frame.adminFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.MyButton;
import model.MyFrame;
import frame.LoginFrame;
import frame.WelcomeFrame;
import frame.adminFrame.DRFLT.*;
import frame.adminFrame.addUser.AddUserFrame;
import frame.adminFrame.changeAdminPassword.ModifyPW;
import frame.adminFrame.search.Search;
import frame.adminFrame.updateUser.SelectUpdateTypeFrame;
import frameInterface.FrameInterface;

/**
 * @author ckx
 *管理员登录后的界面
 */
public class AdminLoginedFrame implements FrameInterface, ActionListener {

	MyButton delete, add, freeze, unfreeze, loss, unloss, search, modifyUser, modifyPW;//updateATM,
	public MyFrame frame;
	JPanel panel;
	JLabel label;

	@Override
	public void init() {
		frame = new MyFrame("管理员界面");
		panel = new JPanel();
		label = new JLabel("<html><font size=7>请选择业务</font><br><font size=5>Please select business</font></html>");
		add = new MyButton("<html><font size=6>开户</font><br>&nbsp;add</html>");
		delete = new MyButton("<html><font size=6>销户</font><br>&nbsp;delete</html>");
		freeze = new MyButton("<html><font size=6>冻结</font><br>&nbsp;freeze</html>");
		unfreeze = new MyButton("<html><font size=6>解冻</font><br>&nbsp;unfreeze</html>");
		loss = new MyButton("<html><font size=6>挂失</font><br>&nbsp;loss</html>");
		unloss = new MyButton("<html><font size=6>解挂</font><br>&nbsp;unloss</html>");
		search = new MyButton("<html><font size=6>查询</font><br>&nbsp;search</html>");
		modifyUser = new MyButton("<html><font size=6>修改用户信息</font><br>&nbsp;modifyUser</html>");
		modifyPW = new MyButton("<html><font size=6>修改密码</font><br>&nbsp;modifyPW</html>");
		frame.confirmButton.setText("<html><font size=6>退出</font><br>&nbsp; Back</html>");
	}

	@Override
	public void show() {
		init();// 加载组件
		panel.setLayout(null);// 将panel的布局设置为空

		label.setForeground(Color.WHITE);// 将label的字体颜色设置为白色

		label.setBounds(390, 100, 400, 100);// 设置标签的大小和在窗口的位置
		// 第一列
		add.setBounds(20, 250, 250, 60);
		delete.setBounds(20, 330, 250, 60);
		freeze.setBounds(20, 410, 250, 60);
		// 第二列
		modifyUser.setBounds(350, 250, 250, 60);
		unfreeze.setBounds(350, 410, 250, 60);
		modifyPW.setBounds(350, 330, 250, 60);
		// 第三列
		loss.setBounds(700, 250, 250, 60);
		unloss.setBounds(700, 330, 250, 60);
		search.setBounds(700, 410, 250, 60);

		frame.cancelButton.setVisible(false);
		panel.setOpaque(false);// 将panel的背景设置为透明，这样才能看到背景图片

		// 添加组件
		panel.add(label);
		panel.add(add);
		panel.add(delete);
		panel.add(freeze);
		panel.add(unfreeze);
		panel.add(loss);
		panel.add(unloss);
		panel.add(search);
		panel.add(modifyUser);
		panel.add(modifyPW);
		frame.getContentPane().add(panel);
		// 窗口可视化
		frame.setVisible(true);
	}

	@Override
	public void listen() {
		add.addActionListener(this);
		delete.addActionListener(this);
		freeze.addActionListener(this);
		unfreeze.addActionListener(this);
		loss.addActionListener(this);
		unloss.addActionListener(this);
		search.addActionListener(this);
		modifyPW.addActionListener(this);
		modifyUser.addActionListener(this);
		frame.confirmButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "<html><font size=6>开户</font><br>&nbsp;add</html>": {
			AddUserFrame f = new AddUserFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>销户</font><br>&nbsp;delete</html>": {
			DeleteUserFrame f = new DeleteUserFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>冻结</font><br>&nbsp;freeze</html>": {
			FreezeUserFrame f = new FreezeUserFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>解冻</font><br>&nbsp;unfreeze</html>": {
			ThawUserFrame f = new ThawUserFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>挂失</font><br>&nbsp;loss</html>": {
			LostUserFrame f = new LostUserFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>解挂</font><br>&nbsp;unloss</html>": {
			ReleaseUserFrame f = new ReleaseUserFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>查询</font><br>&nbsp;search</html>": {
			Search f = new Search();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>修改用户信息</font><br>&nbsp;modifyUser</html>": {
			SelectUpdateTypeFrame f = new SelectUpdateTypeFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>修改密码</font><br>&nbsp;modifyPW</html>": {
			ModifyPW f = new ModifyPW();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>退出</font><br>&nbsp; Back</html>": {
			WelcomeFrame f = new WelcomeFrame();
			f.show();
			f.listen();
			LoginFrame.loginedAccount = null;
			frame.dispose();
			break;
		}
		}
	}
}