package model;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frameInterface.FrameInterface;
//操作成功界面
public class MessageFrame implements FrameInterface{

	public MyFrame frame;
	JPanel panel;
	JLabel label;

	@Override
	public void init() {
		frame = new MyFrame("消息");
		panel = new JPanel();
		frame.cancelButton.setText("<html><font size=6>返回</font><br>&nbsp;Cancel</html>");
		label = new JLabel("<html><font size=7> 操作成功！</font><br><font size=5>Successful operation!</font></html>");
		frame.confirmButton.setText("<html><font size=6>打印凭条</font><br>&nbsp; Printing Slip</html>");
	}

	@Override
	public void show() {
		init();

		panel.setLayout(null);//将panel的布局设置为空
		label.setForeground(Color.WHITE);


		label.setBounds(390, 200, 300, 70);//设置标签的大小和在窗口的位置
		panel.setOpaque(false);//把panel设置为透明，这样容器的第二层也就是背景才能显示出来

		panel.add(label);

		frame.getContentPane().add(panel);
		frame.setVisible(true);	
	}

	@Override
	public void listen() {}
}