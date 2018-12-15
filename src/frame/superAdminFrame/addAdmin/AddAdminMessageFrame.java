package frame.superAdminFrame.addAdmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.Method;
import frame.WelcomeFrame;
import frameInterface.FrameInterface;
import model.MyFrame;

/**
 * @author fzj
 * 成功添加管理员界面
 */
public class AddAdminMessageFrame implements FrameInterface,ActionListener{
	public MyFrame frame;
	JPanel panel;
	JLabel label1,label2;
	long account;

	@Override
	public void init() {
		frame = new MyFrame("消息");
		account = Long.parseLong(Method.bornAdminAccount())-1;//获取到账号
		panel = new JPanel();
		label1 = new JLabel("<html><font size=7> 操作成功！</font><br><font size=5>Successful operation!</font></html>");
		label2 = new JLabel("新账户："+String.valueOf(account));
		frame.cancelButton.setText("<html><font size=6>返回</font><br>&nbsp;Back</html>");
		frame.confirmButton.setText("<html><font size=6>退出</font><br>&nbsp; Exit</html>");
	}

	@Override
	public void show() {
		init();

		panel.setLayout(null);//将panel的布局设置为空
		label1.setForeground(Color.WHITE);
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("宋体", 1, 30));

		label1.setBounds(390, 150, 300, 70);//设置标签的大小和在窗口的位置
		label2.setBounds(360, 250, 300, 70);
		panel.setOpaque(false);//把panel设置为透明，这样容器的第二层也就是背景才能显示出来

		panel.add(label1);
		panel.add(label2);

		frame.getContentPane().add(panel);
		frame.setVisible(true);	
	}

	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html><font size=6>返回</font><br>&nbsp;Back</html>"))
		{
			AddAdminFrame a = new AddAdminFrame();
			a.show();
			a.listen();
			frame.dispose();
		}
		else
		{
			WelcomeFrame welcome = new WelcomeFrame();
			welcome.show();
			welcome.listen();
			frame.dispose();
		}
	}
}