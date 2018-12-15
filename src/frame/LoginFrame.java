package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.userDao.LoginDao;
import frame.adminFrame.AdminLoginInputPwdFrame;
import frame.superAdminFrame.SuperLoginInputPwdFrame;
import frame.userFrame.UserLoginInputPasswordFrame;
import frame.userFrame.noCardDeposit.NoCardDepositFrame;
import frameInterface.FrameInterface;
import model.MyFrame;
import model.MyTextField;

/**
 * @author ckx
 *登录界面
 */
public class LoginFrame implements FrameInterface,ActionListener{
	MyFrame frame;
	public MyTextField text;//账号输入框
	JPanel panel;
	JLabel label;
	public static String loginedAccount;//登录成功的账号

	@Override
	public void init() {
		frame = new MyFrame("登录");
		text = new MyTextField();
		panel = new JPanel();
		label = new JLabel("请输入您的账号");
	}

	@Override
	public void show() {
		init();	//加载组件
		panel.setLayout(null);//将panel的布局设置为空
		//设置组件大小和位置
		label.setBounds(375, 180, 300, 70);
		text.setBounds(310, 300, 350, 50);
		//设置组件字体
		label.setFont(new Font("宋体", 1, 30));
		text.setFont(new Font("宋体", 1, 30));
		panel.setOpaque(false);//把panel设置为透明，这样容器的第二层也就是背景才能显示出来

		//添加组件
		panel.add(text);
		panel.add(label);

		text.setFocusable(false);//设置鼠标不在文本框上

		text.setText("请输入账号:");//文本框提示内容
		text.setForeground(Color.lightGray);//把字体设置为透明色
		label.setForeground(Color.WHITE);//把字体设置为透明色

		frame.getContentPane().add(panel);
		frame.setVisible(true);	//窗口可视化
	}

	//对监听的组件作出反应
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html><font size=6>取消</font><br>&nbsp;Cancel</html>"))
		{//按了取消按钮
			WelcomeFrame f = new WelcomeFrame();
			f.show();
			f.listen();
			frame.dispose();
		}
		else
		{//按了确认按钮
			NoCardDepositFrame.tex = text.getText();
			LoginDao logindao = new LoginDao();
			int i = logindao.queryIsExist(NoCardDepositFrame.tex);//根据账号查询是否允许登录，即什么用户登录
			//0代表普通用户，1代表管理员，2代表超级管理员，-1代表用户不存在，-2代表当前用户处于非正常状态
			if(i!=-1)
			{
				if(i==0)
				{//跳转到用户密码输入界面，并监听其组件，同时当前窗口消失
					UserLoginInputPasswordFrame input = new UserLoginInputPasswordFrame();
					input.show();
					input.listen();
					frame.dispose();
				}else if(i==1)
				{//跳转到管理员密码输入界面，并监听其组件，同时当前窗口消失
					AdminLoginInputPwdFrame input = new AdminLoginInputPwdFrame();
					input.show();
					input.listen();
					frame.dispose();
				}else if(i==2)
				{//跳转到超级用户密码输入界面，并监听其组件，同时当前窗口消失
					SuperLoginInputPwdFrame input = new SuperLoginInputPwdFrame(); 
					input.show();
					input.listen();
					frame.dispose();
				}
				else
				{//跳转到欢迎界面，并监听其组件，同时当前窗口消失
					WelcomeFrame f = new WelcomeFrame();
					f.show();
					f.listen();
					frame.dispose();
				}
			}
		}
	}

	@Override
	public void listen() {
		frame.confirmButton.addActionListener(this);//监听确认按钮
		frame.cancelButton.addActionListener(this);//监听取消按钮
		//监听文本框
		text.textListener(text, "请输入账号:");
	}
}