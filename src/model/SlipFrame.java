package model;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import frameInterface.FrameInterface;
//凭条打印界面
public class SlipFrame implements FrameInterface{

	public MyFrame frame;
	JPanel panel;
	JLabel label;//凭条标题
	public JLabel label1,label2,label3,label4,label5,label6,label7,label8,label9;//凭条信息

	@Override
	public void init() {

		frame = new MyFrame("储户交易记录信息");
		panel = new JPanel();
		label = new JLabel("中国建设银行用户交易凭条");
		label1 = new JLabel("流水号：");
		label2 = new JLabel("账户：");
		label3 = new JLabel("交易用户姓名：");
		label4 = new JLabel("交易账号：");
		label5 = new JLabel("交易金额：");
		label6 = new JLabel("交易类型：");
		label7 = new JLabel("交易时间：");
		label8 = new JLabel("备注：");
		label9 = new JLabel("手续费：");
		//设置label的字体大小和格式
		label.setFont(new Font("宋体", 1, 24));
		label1.setFont(new Font("宋体", 1, 20));
		label2.setFont(new Font("宋体", 1, 20));
		label3.setFont(new Font("宋体", 1, 20));
		label4.setFont(new Font("宋体", 1, 20));
		label5.setFont(new Font("宋体", 1, 20));
		label6.setFont(new Font("宋体", 1, 20));
		label7.setFont(new Font("宋体", 1, 20));
		label8.setFont(new Font("宋体", 1, 20));
		label9.setFont(new Font("宋体", 1, 20));

		frame.cancelButton.setText("<html><font size=6>返回</font><br>&nbsp; Back</html>");
		frame.confirmButton.setText("<html><font size=6>退卡</font><br>&nbsp; Exit</html>");
	}

	@Override
	public void show() {
		panel.setLayout(null);//将panel的布局设置为空
		//设置标签的大小和在窗口的位置
		label.setBounds(300, 110, 350, 60);
		label1.setBounds(320, 160, 350, 50);
		label2.setBounds(320, 200, 350, 50);
		label3.setBounds(320, 250, 350, 50);
		label4.setBounds(320, 300, 350, 50);
		label5.setBounds(320, 350, 350, 50);
		label6.setBounds(320, 400, 200, 50);
		label7.setBounds(320, 450, 450, 50);
		label8.setBounds(320, 500, 450, 50);
		label9.setBounds(580, 350, 350, 50);
		//设置label的字体颜色
		label.setForeground(Color.WHITE);
		label1.setForeground(Color.WHITE);
		label2.setForeground(Color.WHITE);
		label3.setForeground(Color.WHITE);
		label4.setForeground(Color.WHITE);
		label5.setForeground(Color.WHITE);
		label6.setForeground(Color.WHITE);
		label7.setForeground(Color.WHITE);
		label8.setForeground(Color.WHITE);
		label9.setForeground(Color.WHITE);

		panel.setOpaque(false);//把panel设置为透明，这样容器的第二层也就是背景才能显示出来
		//添加组件到panel上
		panel.add(label);
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);
		panel.add(label6);
		panel.add(label7);
		panel.add(label8);
		panel.add(label9);

		frame.getContentPane().add(panel);
		frame.setVisible(true);	
	}

	@Override
	public void listen() {}
}