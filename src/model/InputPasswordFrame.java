package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import frameInterface.FrameInterface;

public class InputPasswordFrame implements FrameInterface,KeyListener {

	public MyFrame frame;
	public JPasswordField[] pass;//密码框数组
	JPanel pwPanel;
	public JLabel label;
	public int count = 0;//密码输入框跳转标志
	public char[] c = new char[6];//存放密码

	@Override
	public void init() {
		frame = new MyFrame("登录");
		pass = new MyPasswordField[6];// 密码框数组
		pwPanel = new JPanel();// 密码框panel
		label = new JLabel("<html><font size=7>请输入密码</font><br><font size=5>Please input a password</font></html>");
	}

	@Override
	public void show() {
		init();
		int i = 0;// 密码框计数器

		for (JPasswordField pw : pass) {
			pw = new MyPasswordField();// 初始化，不初始化会报空指针异常
			pw.setOpaque(false);// 设置密码框背景为不可见
			pw.setBorder(new MyBorder());// 加载MyBorder
			pw.setBounds(280 + i * 70, 300, 60, 60);// 设置密码框的大小和位置
			pw.addKeyListener(this);
			pwPanel.add(pw);
			pw.setFont(new Font("宋体", 1, 50));
			pw.setForeground(Color.WHITE);// 设置密码框的文字颜色
			pw.setHorizontalAlignment(JPasswordField.CENTER);// 密码框文字居中显示
			i++;
		}

		pwPanel.setLayout(null);// 将pwPanel的布局设置为空
		label.setForeground(Color.WHITE);

		label.setBounds(380, 180, 300, 70);// 设置标签的大小和在窗口的位置
		pwPanel.setOpaque(false);// 把pwPanel设置为透明，这样容器的第二层也就是背景才能显示出来

		pwPanel.add(label);

		frame.getContentPane().add(pwPanel);
		frame.setVisible(true);
	}

	@Override
	public void listen() {}

	@Override
	public void keyPressed(KeyEvent e) {
		// 按下键盘
		if (e.getKeyCode() == 8 && count>0) {
			//按下退格键
			c[count]= 0;
			count--;
			pass[count].requestFocus();
			pass[count].setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}// 松开键盘

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar()  > 47 && e.getKeyChar() < 58) {
			//按下了0-9
			c[count]=e.getKeyChar();
			pass[count] = (JPasswordField) e.getSource();// 获得焦点

			if (5>count ) {
				pass[count].transferFocus();//焦点转换
				count++;
			} 
		}
	}  
}