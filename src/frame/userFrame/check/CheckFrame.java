package frame.userFrame.check;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dao.userDao.CheckDao;
import frame.LoginFrame;
import frame.userFrame.LoginedFrame;
import frameInterface.FrameInterface;
import model.MyButton;
import model.MyFrame;

/**
 * @author ckx
 *查询业务选择界面
 */
public class CheckFrame implements ActionListener, FrameInterface {
	public MyButton balanceButton,DetailedButton;//cancelButton;
	public MyFrame frame;
	JPanel panel;
	JLabel label;
	@Override
	public void init() {
		frame = new MyFrame("查询");
		panel = new JPanel();
		label = new JLabel("<html><font size=7>请选择查询类别</font><br><font size=5>Please select category of query</font></html>");
		balanceButton = new MyButton("<html><font size=6>余额查询</font><br>&nbsp;Balance inquiry</html>");
		DetailedButton = new MyButton("<html><font size=6>明细查询</font><br>&nbsp;Detailed inquiry</html>");
	}

	@Override
	public void show() {
		init();//加载组件
		panel.setLayout(null);//将panel的布局设置为空
		panel.setOpaque(false);//将panel的背景设置为空，这样才能显示背景图片
		label.setForeground(Color.WHITE);//将label的字体颜色设置为白色

		label.setBounds(340, 120, 400, 100);//设置标签的大小和在窗口的位置
		balanceButton.setBounds(20,320,250,60);//设置按钮的大小和在窗口的位置
		DetailedButton.setBounds(700,320,250,60);//设置按钮的大小和在窗口的位置

		frame.cancelButton.setBounds(700, 550, 250, 60);
		frame.confirmButton.setVisible(false);
		//添加组件
		panel.add(label);
		panel.add(balanceButton);
		panel.add(DetailedButton);
		frame.getContentPane().add(panel);

		frame.setVisible(true);//窗口可视化
	}

	//监听组件
	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
		balanceButton.addActionListener(this);
		DetailedButton.addActionListener(this);
	}

	//对监听的组件作出反应
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html><font size=6>取消</font><br>&nbsp;Cancel</html>"))
		{
			LoginedFrame f = new LoginedFrame();
			f.show();
			f.listen();
			frame.dispose();
		}
		if(e.getActionCommand().equals("<html><font size=6>余额查询</font><br>&nbsp;Balance inquiry</html>"))
		{
			CheckDao check = new CheckDao();
			List<String> list = check.balanceQuery(LoginFrame.loginedAccount);
			if(list!=null){
				BalanceInquiryFrame f = new BalanceInquiryFrame();
				f.init();
				f.remainMoneyLabel.setText(f.remainMoneyLabel.getText()+" ￥ "+list.get(0));
				f.deseirableLabel.setText(f.deseirableLabel.getText()+" ￥ "+list.get(1));
				f.show();
				f.listen();
				frame.dispose();
			}
		}
		if(e.getActionCommand().equals("<html><font size=6>明细查询</font><br>&nbsp;Detailed inquiry</html>"))
		{
			SelectDateFrame f = new SelectDateFrame();
			f.show();
			f.listen();
			frame.dispose();
		}
	}
}