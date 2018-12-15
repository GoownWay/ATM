package frame.adminFrame.changeAdminPassword;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import dao.adminDao.AdminChangePasswordDao;
import frame.adminFrame.AdminLoginedFrame;
import frameInterface.FrameInterface;
import model.MyFrame;

/**
 * @author hjx
 *管理员修改密码界面
 */
public class ModifyPW implements FrameInterface,ActionListener{
	MyFrame frame;
	JPanel panel;
	JLabel label1,label2;
	JPasswordField jpf1,jpf2;
	
	@Override
	public void init() {
		frame = new MyFrame("修改密码界面");
		panel = new JPanel();
		label1 = new JLabel("<html><font size=6>请输入6位新密码</font><br>&nbsp;</html>");
		label2 = new JLabel("<html><font size=6>请确认密码</font><br>&nbsp;</html>");
		jpf1 = new JPasswordField();
		jpf2 = new JPasswordField();
	}

	@Override
	public void show() {
		init();//加载组件
		panel.setLayout(null);//将panel的布局设置为空

		label1.setForeground(Color.WHITE);//将jpf的字体颜色设置为白色
		label2.setForeground(Color.WHITE);
		
		label1.setBounds(330, 170, 200, 50);//设置标签的大小和在窗口的位置
		jpf1.setBounds(330, 220, 300, 50);//设置标签的大小和在窗口的位置
		label2.setBounds(330, 300, 200, 50);//设置标签的大小和在窗口的位置
		jpf2.setBounds(330, 350, 300, 50);
		
		jpf1.setFont(new Font("宋体", 1, 30));
		jpf2.setFont(new Font("宋体", 1, 30));
		
		panel.setOpaque(false);//将panel的背景设置为透明，这样才能看到背景图片

		//添加组件
		panel.add(label1);
		panel.add(label2);
		panel.add(jpf1);
		panel.add(jpf2);
		frame.getContentPane().add(panel);
		//窗口可视化
		frame.setVisible(true);
	}

	@Override
	public void listen() {
		frame.confirmButton.addActionListener(this);
		frame.cancelButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<html><font size=6>取消</font><br>&nbsp;Cancel</html>")
		{
			AdminLoginedFrame f = new AdminLoginedFrame();
			f.show();
			f.listen();
			frame.dispose();
		}
		else//确认按钮
		{
			AdminChangePasswordDao dp = new AdminChangePasswordDao();
			boolean i = dp.isTwoPasswordSame(jpf1.getPassword(),jpf2.getPassword());
			if(i){
				AdminChangePasswordMessageFrame f = new AdminChangePasswordMessageFrame();
				f.show();
				f.listen();
				frame.dispose();
			}
		}
	}
}