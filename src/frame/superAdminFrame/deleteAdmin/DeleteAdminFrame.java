package frame.superAdminFrame.deleteAdmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import dao.superAdminDao.DeleteAdminDao;
import frame.superAdminFrame.SuperLoginedFrame;
import frameInterface.FrameInterface;
import model.MyFrame;
import model.MyTextField;

/**
 * @author fzj
 * 注销管理员
 */
public class DeleteAdminFrame implements FrameInterface, ActionListener {
	MyFrame frame;
	JPanel panel;
	JLabel label;
	public MyTextField adminAccount;

	@Override
	public void init() {
		frame = new MyFrame("注销管理员");
		panel = new JPanel();
		label = new JLabel("账号：");
		adminAccount = new MyTextField();// 输入待注销的管理员账号
	}

	@Override
	public void show() {
		init();
		panel.setOpaque(false);// 将panel设置为透明，背景图片才能显示出来
		panel.setLayout(null);// 将panel的布局设置为空，这样能自定义组件位置

		adminAccount.setText("请输入待注销的管理员账号");

		adminAccount.setForeground(Color.lightGray);// 将文本框的字体设置为提示的颜色
		label.setForeground(Color.WHITE);

		panel.add(adminAccount);
		panel.add(label);

		label.setBounds(300, 200, 120, 50);
		label.setFont(new Font("宋体", 1, 20));
		adminAccount.setBounds(370, 200, 330, 50);// 设置文本框的位置和大小
		adminAccount.setFont(new Font("宋体", 1, 20));// 设置文本框字体的大小

		// 面板获得焦点，不至于让文本框一开始就获得焦点而使得提示文字消失
		panel.setFocusable(true);

		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("<html><font size=6>取消</font><br>&nbsp;Cancel</html>")) {
			SuperLoginedFrame superLogined = new SuperLoginedFrame();
			superLogined.show();
			superLogined.listen();
			frame.dispose();
		}
		else{
			DeleteAdminDao da = new DeleteAdminDao();
			if(da.updateAdminInfo(adminAccount.getText())){
				DeleteAdminMessageFrame daf = new DeleteAdminMessageFrame();
				daf.show();
				daf.listen();
				frame.dispose();
			}
			else{
				JOptionPane.showMessageDialog(null, "操作失败！", "错误提示", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
		adminAccount.textListener(adminAccount, "请输入待注销的管理员账号");
	}
}