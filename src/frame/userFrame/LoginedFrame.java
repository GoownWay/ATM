package frame.userFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import frame.LoginFrame;
import frame.WelcomeFrame;
import frame.userFrame.cardDeposit.CardDepositFrame;
import frame.userFrame.changePassword.ChangePasswordFrame;
import frame.userFrame.check.CheckFrame;
import frame.userFrame.transfer.TransferFrame;
import frame.userFrame.withdraw.WithdrawFrame;
import frameInterface.FrameInterface;
import model.MyButton;
import model.MyFrame;

/**
 * @author ckx
 *用户登录后的主界面
 */
public class LoginedFrame implements FrameInterface,ActionListener{

	MyButton withdrawButton,depositButton,transferButton,modifyButton,queryButton,exitButton;
	MyFrame frame;
	JPanel panel;
	JLabel label;

	@Override
	public void init() {
		frame = new MyFrame("用户界面");
		panel = new JPanel();
		label = new JLabel("<html><font size=7>请选择业务</font><br><font size=5>Please select business</font></html>");
		depositButton = new MyButton("<html><font size=6>存款</font><br>&nbsp;Deposit</html>");
		withdrawButton = new MyButton("<html><font size=6>取款</font><br>Withdraw</html>");
		transferButton = new MyButton("<html><font size=6>转账</font><br>&nbsp;Transfer</html>");
		modifyButton = new MyButton("<html><font size=6>修改密码</font><br>Change Password</html>");
		queryButton = new MyButton("<html><font size=6>查询</font><br>&nbsp;Check</html>");
		exitButton = new MyButton("<html><font size=6>退卡</font><br>&nbsp;&nbsp;&nbsp;Exit</html>");		
	}

	@Override
	public void show() {
		init();//加载组件
		panel.setLayout(null);//将panel的布局设置为空

		label.setForeground(Color.WHITE);//将label的字体颜色设置为白色

		frame.cancelButton.setVisible(false);
		frame.confirmButton.setVisible(false);

		label.setBounds(390, 150, 400, 100);//设置标签的大小和在窗口的位置
		depositButton.setBounds(700, 350, 250, 60);//设置按钮的大小和在窗口的位置
		withdrawButton.setBounds(700, 450, 250, 60);//设置按钮的大小和在窗口的位置
		transferButton.setBounds(700, 550, 250, 60);//设置按钮的大小和在窗口的位置
		modifyButton.setBounds(20, 350, 250, 60);//设置按钮的大小和在窗口的位置
		queryButton.setBounds(20, 450, 250, 60);//设置按钮的大小和在窗口的位置
		exitButton.setBounds(20, 550, 250, 60);//设置按钮的大小和在窗口的位置
		panel.setOpaque(false);//将panel的背景设置为透明，这样才能看到背景图片

		//添加组件
		panel.add(label);
		panel.add(depositButton);
		panel.add(withdrawButton);
		panel.add(transferButton);
		panel.add(modifyButton);
		panel.add(queryButton);
		panel.add(exitButton);
		frame.getContentPane().add(panel);
		//窗口可视化
		frame.setVisible(true);
	}

	@Override
	public void listen() {
		//监听按钮
		depositButton.addActionListener(this);
		withdrawButton.addActionListener(this);
		transferButton.addActionListener(this);
		modifyButton.addActionListener(this);
		queryButton.addActionListener(this);
		exitButton.addActionListener(this);
	}

	//对监听的组件做出反应
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){

		case "<html><font size=6>存款</font><br>&nbsp;Deposit</html>":
		{
			CardDepositFrame f = new CardDepositFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>取款</font><br>Withdraw</html>":
		{
			WithdrawFrame f = new WithdrawFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>转账</font><br>&nbsp;Transfer</html>":
		{
			TransferFrame f = new TransferFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>修改密码</font><br>Change Password</html>":
		{
			ChangePasswordFrame f = new ChangePasswordFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>查询</font><br>&nbsp;Check</html>":
		{
			CheckFrame f = new CheckFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>退卡</font><br>&nbsp;&nbsp;&nbsp;Exit</html>":
		{
			WelcomeFrame f = new WelcomeFrame();
			LoginFrame.loginedAccount = null;
			f.show();
			f.listen();
			frame.dispose();
			break;
		}}
	}
}