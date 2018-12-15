package frame.userFrame.check;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.LoginFrame;
import frame.WelcomeFrame;
import frameInterface.FrameInterface;
import model.MyFrame;

/**
 * @author ckx
 *查询余额显示界面
 */
public class BalanceInquiryFrame implements ActionListener, FrameInterface {
	public MyFrame frame;
	public JLabel remainMoneyLabel,deseirableLabel;
	JPanel panel;
	@Override
	public void init() {
		frame = new MyFrame("余额");
		panel = new JPanel();
		remainMoneyLabel = new JLabel("余额：");
		deseirableLabel = new JLabel("可取余额：");
	}

	@Override
	public void show() {
		panel.setLayout(null);//将panel的布局设置为空
		panel.setOpaque(false);//将panel的背景设置为空，这样才能显示背景图片

		remainMoneyLabel.setFont(new Font("宋体", 1, 30));
		deseirableLabel.setFont(new Font("宋体", 1, 30));
		remainMoneyLabel.setForeground(Color.WHITE);//将label的字体颜色设置为白色
		deseirableLabel.setForeground(Color.WHITE);//将label的字体颜色设置为白色

		remainMoneyLabel.setBounds(315, 170, 400, 100);//设置标签的大小和在窗口的位置
		deseirableLabel.setBounds(315, 270, 400, 100);//设置标签的大小和在窗口的位置

		frame.cancelButton.setText("<html><font size=6>返回</font><br>&nbsp;Return</html>");
		frame.confirmButton.setText("<html><font size=6>退卡</font><br>&nbsp;&nbsp;&nbsp;Exit</html>");
		frame.cancelButton.setBounds(700, 550, 250, 60);
		frame.confirmButton.setBounds(20, 550, 250, 60);

		//添加组件
		panel.add(remainMoneyLabel);
		panel.add(deseirableLabel);
		frame.getContentPane().add(panel);

		frame.setVisible(true);//窗口可视化
	}

	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html><font size=6>返回</font><br>&nbsp;Return</html>"))
		{
			CheckFrame f = new CheckFrame();
			f.show();
			f.listen();
			frame.dispose();
		}
		else
		{//退卡按钮
			WelcomeFrame f = new WelcomeFrame();
			LoginFrame.loginedAccount = null;
			f.show();
			f.listen();
			frame.dispose();
		}
	}
}