package frame.userFrame.changePassword;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.userDao.ChangePasswordDao;
import frameInterface.FrameInterface;
import model.InputPasswordFrame;

/**
 * @author ckx
 *用户改密之确认新密码界面
 */
public class ConfirmPasswordFrame implements FrameInterface, ActionListener{
	public InputPasswordFrame frame;
	char[] firstPassword;
	public ConfirmPasswordFrame(char[] c) {
		firstPassword=c;
	}

	@Override
	public void init() {
		frame = new InputPasswordFrame();
		frame.show();
	}

	@Override
	public void show() {
		init();//加载组件
		frame.label.setText("<html><font size=7>请再次输入新密码</font><br><font size=5>&nbsp;&nbsp;&nbsp;Please enter a new password</font></html>");
		frame.label.setBounds(335, 180, 350, 70);//设置标签的大小和在窗口的位置;
	}

	@Override
	public void listen() {
		frame.frame.cancelButton.addActionListener(this);
		frame.frame.confirmButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html><font size=6>取消</font><br>&nbsp;Cancel</html>"))
		{//取消的话就返回第一次输入界面
			ChangePasswordFrame f = new ChangePasswordFrame();
			f.show();
			f.listen();
			this.frame.frame.dispose();
		}
		if(e.getActionCommand().equals("<html><font size=6>确认</font><br>&nbsp;Comfirm</html>"))
		{
			ChangePasswordDao cp = new ChangePasswordDao();
			//调用方法，判断两次新密码输入是否相同，不相同则重新输入，相同就更新到数据库
			boolean i = cp.isTwoPasswordSame(firstPassword,frame.c);
			if(i)
			{				
				ChangePasswordMessageFrame f = new ChangePasswordMessageFrame();
				f.show();
				f.listen();
				this.frame.frame.dispose();
			}
			else
			{
				ChangePasswordFrame f = new ChangePasswordFrame();
				f.show();
				f.listen();
				frame.frame.dispose();
			}
		}
	}
}