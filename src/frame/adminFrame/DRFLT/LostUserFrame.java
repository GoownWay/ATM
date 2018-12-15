package frame.adminFrame.DRFLT;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import bean.Card;
import dao.adminDao.*;
import frame.adminFrame.AdminLoginedFrame;
import frameInterface.FrameInterface;
import model.MyFrame;
import model.MyTextField;

/**
 * @author hjx
 * 挂失用户
 */
public class LostUserFrame  implements FrameInterface, ActionListener{
	MyFrame frame;
	JPanel panel;
	JLabel label;
	public MyTextField userAccount;

	@Override
	public void init() {
		frame = new MyFrame("挂失用户");
		panel = new JPanel();
		label = new JLabel("<html><font size=7>请输入待挂失的用户账号:</font><br><font size=5>Please enter the account to be reported lost:</font></html>");
		label.setFont(new java.awt.Font("宋体", 0, 35));
		label.setForeground(Color.white);
		userAccount = new MyTextField();// 输入待挂失的用户账号
	}

	@Override
	public void show() {
		init();
		panel.setOpaque(false);// 将panel设置为透明，背景图片才能显示出来
		panel.setLayout(null);// 将panel的布局设置为空，这样能自定义组件位置

		userAccount.setText("请输入19位用户账号");
		userAccount.setForeground(Color.lightGray);// 将文本框的字体设置为提示的颜色

		panel.add(userAccount);
		panel.add(label);

		//面板获得焦点，不至于让文本框一开始就获得焦点而使得提示文字消失
		panel.setFocusable(true);

		userAccount.setBounds(310,280,350, 50);//设置文本框的位置和大小
		userAccount.setFont(new Font("宋体", 1, 30));//设置文本框字体的大小

		label.setBounds(285, 160, 450, 100);// 设置标签的大小和在窗口的位置

		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<html><font size=6>确认</font><br>&nbsp;Comfirm</html>") {
			LostUserDao lost = new LostUserDao();
			Card card = lost.queryIsExist(userAccount.getText());//判断账号存在
			if(card != null) {
				UserInfoFrame f = new UserInfoFrame();//存在即跳转到信息界面
				f.show();
				f.listen();
				f.setAccount(card, 1);//传入账号，0=冻结，1=挂失，2\3=解冻解挂，4=注销
				frame.dispose();
			}
		}else {
			AdminLoginedFrame f = new AdminLoginedFrame();
			f.show();
			f.listen();
			frame.dispose();
		}
	}

	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
		userAccount.textListener(userAccount, "请输入19位用户账号");
	}
}