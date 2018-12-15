package frame.adminFrame.updateATMFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dao.adminDao.UpdateATMAndQueryUserTransferDao;
import frame.adminFrame.search.Search;
import frameInterface.FrameInterface;
import model.MyFrame;
import model.MyTextField;

/**
 * @author hjx
 *更新atm余额界面
 */
public class UpdateATM implements FrameInterface, ActionListener {
	MyFrame frame;
	JPanel panel;
	JLabel label;

	public MyTextField updateMoney;

	@Override
	public void init() {
		frame = new MyFrame("输入更新金额");
		panel = new JPanel();
		label = new JLabel("<html><font size=7>请输入更新的现金数:</font><br><font size=5><p width = \"200px\">Please input the cash amount</p></font></html>");
		label.setForeground(Color.white);
		updateMoney = new MyTextField();// 输入更新的现金数
		frame.cancelButton.setText("<html><font size=6>返回</font><br>&nbsp; Back</html>");
		frame.confirmButton.setText("<html><font size=6>确认</font><br>&nbsp; Back</html>");
	}

	@Override
	public void show() {
		init();
		panel.setOpaque(false);// 将panel设置为透明，背景图片才能显示出来
		panel.setLayout(null);// 将panel的布局设置为空，这样能自定义组件位置

		updateMoney.setText("请输入更新的现金数:");
		updateMoney.setForeground(Color.lightGray);// 将文本框的字体设置为提示的颜色

		//面板获得焦点，不至于让文本框一开始就获得焦点而使得提示文字消失
		panel.setFocusable(true);

		panel.add(updateMoney);
		panel.add(label);

		updateMoney.setBounds(310,280,350, 50);//设置文本框的位置和大小
		updateMoney.setFont(new Font("宋体", 1, 30));//设置文本框字体的大小

		label.setBounds(300, 160, 450, 100);// 设置标签的大小和在窗口的位置

		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<html><font size=6>返回</font><br>&nbsp; Back</html>") {
			Search f = new Search();
			f.init();
			f.show();
			f.listen();
			frame.dispose();
		}else if(e.getActionCommand()=="<html><font size=6>确认</font><br>&nbsp; Back</html>") {
			UpdateATMAndQueryUserTransferDao updateDao = new UpdateATMAndQueryUserTransferDao();
			boolean i = updateDao.updateATM(updateMoney.getText());
			if(i) {
				UpdateSuccessFrame f = new UpdateSuccessFrame();
				f.show();
				f.listen();
				frame.dispose();
			}			
		}
	}

	@Override
	public void listen() {
		updateMoney.textListener(updateMoney, "请输入更新的现金数:");
		frame.confirmButton.addActionListener(this);
		frame.cancelButton.addActionListener(this);
	}
}