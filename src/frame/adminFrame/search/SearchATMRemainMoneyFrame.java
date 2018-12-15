package frame.adminFrame.search;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.MyButton;
import frame.WelcomeFrame;
import frame.adminFrame.updateATMFrame.UpdateATM;
import frameInterface.FrameInterface;
import model.MyFrame;

/**
 * @author hjx
 * 显示ATM余额界面
 */
public class SearchATMRemainMoneyFrame implements FrameInterface,ActionListener {
	MyFrame frame;
	JPanel panel;
	public JLabel label;
	MyButton updateATM;

	@Override
	public void init() {
		frame = new MyFrame("显示ATM余额界面");
		panel = new JPanel();
		label = new JLabel("ATM现存余额：￥ ");
		updateATM = new MyButton("<html><font size=6>更新ATM余额</font><br>&nbsp; UpdateATM</html>");
		frame.cancelButton.setText("<html><font size=6>返回</font><br>&nbsp; return</html>");
		frame.confirmButton.setText("<html><font size=6>退出</font><br>&nbsp; exit</html>");
	}

	@Override
	public void show() {
		panel.setLayout(null);//将panel的布局设置为空
		label.setForeground(Color.WHITE);

		label.setBounds(300, 180, 500, 100);//设置标签的大小和在窗口的位置
		updateATM.setBounds(700, 350,250,60);//设置更新按钮的大小和在窗口的位置
		panel.setOpaque(false);//把panel设置为透明，这样容器的第二层也就是背景才能显示出来

		panel.add(label);
		panel.add(updateATM);

		frame.getContentPane().add(panel);
		frame.setVisible(true);	
	}

	@Override
	public void listen() {
		frame.confirmButton.addActionListener(this);
		frame.cancelButton.addActionListener(this);
		updateATM.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "<html><font size=6>更新ATM余额</font><br>&nbsp; UpdateATM</html>": {
			UpdateATM f = new UpdateATM();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>返回</font><br>&nbsp; return</html>": {
			Search f = new Search();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		case "<html><font size=6>退出</font><br>&nbsp; exit</html>": {
			WelcomeFrame f = new WelcomeFrame();
			f.show();
			f.listen();
			frame.dispose();
			break;
		}
		}
	}
}