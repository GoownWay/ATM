package frame.adminFrame.search;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dao.adminDao.UpdateATMAndQueryUserTransferDao;
import frame.WelcomeFrame;
import frame.adminFrame.AdminLoginedFrame;
import frameInterface.FrameInterface;
import model.MyButton;
import model.MyFrame;

/**
 * @author hjx
 * 管理员查询界面
 */
public class Search implements FrameInterface,ActionListener {
	MyButton searchUserTransaction,searchATM;
	MyFrame frame;
	JPanel panel;
	JLabel label;

	@Override
	public void init() {
		frame = new MyFrame("管理员查询界面");
		panel = new JPanel();
		label = new JLabel("<html><font size=7>请选择业务</font><br><font size=5>Please select business</font></html>");

		searchUserTransaction = new MyButton("<html><font size=6>查询用户交易</font><br>Query user transactions</html>");
		searchATM = new MyButton("<html><font size=6>查询ATM余额</font><br>&nbsp;Query balance</html>");

		frame.cancelButton.setText("<html><font size=6>返回</font><br>&nbsp; Back</html>");
		frame.confirmButton.setText("<html><font size=6>退出</font><br>&nbsp; Back</html>");
	}

	@Override
	public void show() {
		init();//加载组件
		panel.setLayout(null);//将panel的布局设置为空

		label.setForeground(Color.WHITE);//将label的字体颜色设置为白色

		label.setBounds(390, 150, 400, 100);//设置标签的大小和在窗口的位置
		//第一列
		searchUserTransaction.setBounds(20, 350, 250, 60);
		searchATM.setBounds(700, 350, 250, 60);

		panel.setOpaque(false);//将panel的背景设置为透明，这样才能看到背景图片

		//添加组件
		panel.add(label);
		panel.add(searchUserTransaction);
		panel.add(searchATM);

		frame.getContentPane().add(panel);
		//窗口可视化
		frame.setVisible(true);
	}

	@Override
	public void listen() {
		searchUserTransaction.addActionListener(this);
		searchATM.addActionListener(this);
		frame.confirmButton.addActionListener(this);
		frame.cancelButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "<html><font size=6>查询用户交易</font><br>Query user transactions</html>":{
			QueryUserInfoInputFrame f = new QueryUserInfoInputFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>查询ATM余额</font><br>&nbsp;Query balance</html>":{
			UpdateATMAndQueryUserTransferDao atmUpdate = new UpdateATMAndQueryUserTransferDao();
			Long i = atmUpdate.queryATMRemainMoney();
			SearchATMRemainMoneyFrame f = new SearchATMRemainMoneyFrame();
			f.init();
			f.show();
			f.label.setText(f.label.getText()+i);
			f.label.setFont(new Font("宋体", 1, 30));//设置文本框字体的大小

			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>返回</font><br>&nbsp; Back</html>": {
			AdminLoginedFrame f = new AdminLoginedFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>退出</font><br>&nbsp; Back</html>": {
			WelcomeFrame f = new WelcomeFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		}
	}
}