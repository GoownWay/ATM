package frame.userFrame.withdraw;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.userDao.WithdrawDao;
import frame.userFrame.LoginedFrame;
import frameInterface.FrameInterface;
import model.MyButton;
import model.MyFrame;
import model.MyTextField;

/**
 * @author ckx
 *取款金额输入界面
 */
public class WithdrawFrame implements ActionListener, FrameInterface {
	MyTextField money;
	MyButton one,two,five,ten;
	MyFrame frame;
	JLabel label;
	JPanel panel;
	@Override
	public void init() {
		frame = new MyFrame("取款");
		panel = new JPanel();
		money = new MyTextField();//取款输入框
		one = new MyButton("<html><font size=6>100</font></html>");
		two = new MyButton("<html><font size=6>200</font></html>");
		five = new MyButton("<html><font size=6>500</font></html>");
		ten = new MyButton("<html><font size=6>1000</font></html>");
		label = new JLabel("<html><font size=7>请输入取款金额:</font><br><font size=4>&nbsp;&nbsp;Please enter the withdrawal amount:</font></html>");
	}

	@Override
	public void show() {
		init();//加载组件
		panel.setLayout(null);//将panel的布局设置为空
		label.setForeground(Color.WHITE);//label的字体颜色设置为白色
		money.setFont(new Font("宋体", 1, 30));//设置文本框字体的大小

		label.setBounds(345, 100, 400, 100);//设置标签的大小和在窗口的位置
		money.setBounds(310,230,350, 50);//设置文本框的位置和大小
		one.setBounds(20, 350, 250, 60);//设置按钮的大小和在窗口的位置
		two.setBounds(20, 450, 250, 60);//设置按钮的大小和在窗口的位置
		five.setBounds(700, 350, 250, 60);//设置按钮的大小和在窗口的位置
		ten.setBounds(700, 450, 250, 60);//设置按钮的大小和在窗口的位置

		panel.setOpaque(false);//将panel的背景设置为透明，这样才能看到背景图片
		//添加组件
		panel.add(label);
		panel.add(money);
		panel.add(one);
		panel.add(two);
		panel.add(five);
		panel.add(ten);
		frame.getContentPane().add(panel);
		//窗口可视化
		frame.setVisible(true);
	}
	//监听组件
	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
		one.addActionListener(this);
		two.addActionListener(this);
		five.addActionListener(this);
		ten.addActionListener(this);
	}
	//对组件监听作出反应
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "<html><font size=6>取消</font><br>&nbsp;Cancel</html>":
		{
			LoginedFrame f = new LoginedFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>确认</font><br>&nbsp;Comfirm</html>":
		{
			WithdrawDao wd = new WithdrawDao();
			boolean i = wd.updateRemainMoney(money.getText().trim());
			if(i)
			{
				WithdrawMessageFrame m = new WithdrawMessageFrame();
				m.show();
				m.listen();
				frame.dispose();
			}
			break;
		}
		case "<html><font size=6>100</font></html>":{money.setText("100");break;}
		case "<html><font size=6>200</font></html>":{money.setText("200");break;}
		case "<html><font size=6>500</font></html>":{money.setText("500");break;}
		case "<html><font size=6>1000</font></html>":{money.setText("1000");break;}
		}
	}
}