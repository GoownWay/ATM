package frame.superAdminFrame.deleteAdmin;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.WelcomeFrame;
import frameInterface.FrameInterface;
import model.MyFrame;

/**
 * @author fzj
 * 删除管理员成功界面
 */
public class DeleteAdminMessageFrame implements FrameInterface,ActionListener{
	public MyFrame frame;
	JPanel panel;
	JLabel label1;

	@Override
	public void init() {
		frame = new MyFrame("消息");
		panel = new JPanel();
		label1 = new JLabel("<html><font size=7> 操作成功！</font><br><font size=5>Successful operation!</font></html>");
		frame.cancelButton.setText("<html><font size=6>返回</font><br>&nbsp;Back</html>");
		frame.confirmButton.setText("<html><font size=6>退出</font><br>&nbsp; Exit</html>");
	}

	@Override
	public void show() {
		init();

		panel.setLayout(null);//将panel的布局设置为空
		label1.setForeground(Color.WHITE);

		label1.setBounds(390, 150, 300, 70);//设置标签的大小和在窗口的位置
		panel.setOpaque(false);//把panel设置为透明，这样容器的第二层也就是背景才能显示出来

		panel.add(label1);

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
			DeleteAdminFrame a = new DeleteAdminFrame();
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