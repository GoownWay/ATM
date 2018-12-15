package frame.superAdminFrame.updateAdmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import bean.Admin;
import dao.Method;
import frame.superAdminFrame.SuperLoginedFrame;
import frameInterface.FrameInterface;
import model.MyFrame;
import model.MyTextField;

/**
 * @author fzj
 *超级用户功能之修改管理员信息界面
 */
public class InputUpdateAccount implements FrameInterface, ActionListener {
	MyFrame frame;
	JPanel panel;
	JLabel label;
	public MyTextField adminAccount;
	public static Admin admin = new Admin();

	@Override
	public void init() {
		frame = new MyFrame("修改管理员");
		panel = new JPanel();
		label = new JLabel("账号：");
		adminAccount = new MyTextField();// 输入待注销的管理员账号
	}

	@Override
	public void show() {
		init();
		panel.setOpaque(false);// 将panel设置为透明，背景图片才能显示出来
		panel.setLayout(null);// 将panel的布局设置为空，这样能自定义组件位置

		adminAccount.setText("请输入待修改的管理员账号");

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
		else if(e.getActionCommand().equals("<html><font size=6>确认</font><br>&nbsp;Comfirm</html>")){
			Method method = new Method();
			admin = method.queryAdmin(adminAccount.getText());
			if(admin.getAccount()!= null){
				UpdateAdminFrame ua = new UpdateAdminFrame();
				ua.show();
				ua.listen();
				frame.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "账号不存在！", "错误提示", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
		adminAccount.textListener(adminAccount, "请输入待修改的管理员账号");
	}
}