package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import frame.userFrame.noCardDeposit.NoCardDepositFrame;
import frameInterface.FrameInterface;
import model.MyButton;
import model.MyFrame;


/**
 * @author ckx
 *欢迎界面
 */
public class WelcomeFrame implements FrameInterface,ActionListener{
	MyFrame frame;
	JPanel panel;
	MyButton loginButton,wkButton;//登录和无卡操作按钮

	@Override
	public void init() {
		frame = new MyFrame("欢迎");//初始化自定义的窗体，并把标题设置为欢迎
		loginButton = new MyButton("<html><font size=6>登录</font><br>&nbsp;&nbsp;Login</html>");
		wkButton = new MyButton("<html><font size=6>无卡交易</font><br>&nbsp;No Card Recharge</html>");
		panel = new JPanel();
	}

	@Override
	public void show() {
		init();//定义组件
		panel.setOpaque(false);//将panel的背景设置为透明才能看到背景图片
		panel.setLayout(null);//panel的布局设置为空（绝对布局），这样能把组件放在任意位置
		//取消和确认按钮不可见
		frame.cancelButton.setVisible(false);
		frame.confirmButton.setVisible(false);

		ImageIcon bg = new ImageIcon("./src/image/welcomeBackground.png");//欢迎界面背景图片
		JLabel label0 = new JLabel(bg);//把背景图片放到label0上
		label0.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());//背景图片的位置和大小
		frame.getLayeredPane().remove(frame.label);//把原来的背景图片去除，添加欢迎页面的背景图
		frame.getLayeredPane().add(label0, new Integer(Integer.MIN_VALUE));//把带有欢迎界面的背景图片放到第二层

		panel.add(loginButton);//将按钮添加到panel
		panel.add(wkButton);//将按钮添加到panel

		loginButton.setBounds(80, 210, 260, 140);//设置按钮的大小和在窗口的位置
		wkButton.setBounds(80, 390, 260, 140);//设置按钮的大小和在窗口的位置

		frame.getContentPane().add(panel);//在窗体的第一层添加panel
		frame.setVisible(true);//把窗体设置为可视
	}

	//监听组件
	@Override
	public void listen() {
		loginButton.addActionListener(this);//监听登录按钮
		wkButton.addActionListener(this);//监听无卡操作按钮
	}

	//对监听的组件作出反应
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html><font size=6>登录</font><br>&nbsp;&nbsp;Login</html>"))
		{//点击了登录按钮之后
			LoginFrame f = new LoginFrame();//登录界面初始化
			f.show();//登录界面显示
			f.listen();//登录界面的按钮监听
			frame.dispose();//跳转到登录界面之后，欢迎界面消失
		}else
		{//点击了无卡操作按钮之后
			NoCardDepositFrame f = new NoCardDepositFrame();//无卡操作界面初始化
			f.show();//无卡操作界面显示
			f.listen();//无卡操作界面
			frame.dispose();//跳转到无卡操作界面之后，欢迎界面消失
		}
	}
}