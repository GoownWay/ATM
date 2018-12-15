package frame.adminFrame.updateATMFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import frame.WelcomeFrame;
import frame.adminFrame.search.Search;
import frameInterface.FrameInterface;
import model.MyFrame;

/**
 * @author hjx
 *更新atm余额成功界面
 */
public class UpdateSuccessFrame implements FrameInterface, ActionListener{
	MyFrame frame;
	JPanel panel;
	JLabel label;

	@Override
	public void init() {
		frame = new MyFrame("操作成功！");
		panel = new JPanel();
		label = new JLabel("<html><font size=7>&nbsp 操作成功！</font><br><font size=5></br>&nbsp Successful operation!</font></html>");
	}

	@Override
	public void show() {
		init();
		panel.setLayout(null);//将panel的布局设置为空
		panel.setOpaque(false);//把panel设置为透明，这样容器的第二层也就是背景才能显示出来

		label.setForeground(Color.WHITE);
		label.setBounds(380, 250, 300, 70);//设置标签的大小和在窗口的位置

		panel.add(label);

		frame.getContentPane().add(panel);
		frame.setVisible(true);
		frame.cancelButton.setText("<html><font size=6>返回</font><br>&nbsp;Cancel</html>");
		frame.confirmButton.setText("<html><font size=6>退出</font><br>&nbsp;Exit</html>");
	}

	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "<html><font size=6>返回</font><br>&nbsp;Cancel</html>") {
			Search f = new Search();
			f.show();
			f.listen();
			frame.dispose();
		}else if(e.getActionCommand() == "<html><font size=6>退出</font><br>&nbsp;Exit</html>") {
			WelcomeFrame f = new WelcomeFrame();
			f.show();
			f.listen();
			frame.dispose();
		}	
	}
}