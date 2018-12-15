package frame.adminFrame.updateUser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import bean.User;
import dao.adminDao.UpdateUserDao;
import frameInterface.FrameInterface;
import model.MyFrame;
import model.MyTextField;

/**
 * @author hjx
 *更新用户信息功能之输入身份证号界面
 */
public class UpdateUserInputFrame implements FrameInterface, ActionListener {
	MyFrame frame;
	JPanel panel;
	JLabel label;
	public MyTextField userAccount;
	public int flag = 1;

	@Override
	public void init() {
		frame = new MyFrame("输入待修改用户ID");
		panel = new JPanel();
		label = new JLabel("<html><font size=7>请输入待修改用户的身份证号码：</font><br>&nbsp<font size=5>Please enter the ID of the user to be modified:</font></html>");
		label.setFont(new java.awt.Font("宋体", 0, 35));
		label.setForeground(Color.white);
		userAccount = new MyTextField();//请输入待修改用户的ID：
	}

	@Override
	public void show() {
		init();
		panel.setOpaque(false);// 将panel设置为透明，背景图片才能显示出来
		panel.setLayout(null);// 将panel的布局设置为空，这样能自定义组件位置

		frame.cancelButton.setText("<html><font size=6>取消</font><br>&nbsp;Cancel</html>");
		frame.confirmButton.setText("<html><font size=6>确认</font><br>&nbsp;Comfirm</html>");

		userAccount.setText("请输入18位用户ID");
		userAccount.setForeground(Color.lightGray);// 将文本框的字体设置为提示的颜色

		panel.add(userAccount);
		panel.add(label);

		//面板获得焦点，不至于让文本框一开始就获得焦点而使得提示文字消失
		panel.setFocusable(true);

		userAccount.setBounds(310,280,350, 50);//设置文本框的位置和大小
		userAccount.setFont(new Font("宋体", 1, 30));//设置文本框字体的大小

		label.setBounds(255, 160, 540, 100);// 设置标签的大小和在窗口的位置

		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<html><font size=6>确认</font><br>&nbsp;Comfirm</html>") {
			UpdateUserDao update = new UpdateUserDao();
			User user = update.queryIsExist(userAccount.getText());//判断账号存在
			if(user != null) {
				UpdateUserFrame f = new UpdateUserFrame();//存在即跳转到信息界面
				f.show();
				f.listen();
				f.setInfo(user);
				frame.dispose();
			}
		}else {//取消按钮
			SelectUpdateTypeFrame f = new SelectUpdateTypeFrame();
			f.show();
			f.listen();
			frame.dispose();
		}
	}

	@Override
	public void listen() {
		userAccount.textListener(userAccount, "请输入18位用户ID");
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
	}
}