package model;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frameInterface.FrameInterface;
//转账、无卡存款输入账号后信息显示界面
public class ShowFrame implements FrameInterface{
	MyFrame frame;
	JPanel panel;
	public JLabel accountLabel,nameLabel,bankLabel,moneyLabel,remarkLabel;

	@Override
	public void init() {
		frame = new MyFrame("信息显示");
		panel = new JPanel();
		accountLabel = new JLabel("账号：");
		nameLabel = new JLabel("姓名：");
		bankLabel = new JLabel("所属银行：");
		moneyLabel = new JLabel("金额：");
		remarkLabel = new JLabel("备注：");
	}
	@Override
	public void show() {	
		//设置字体大小
		accountLabel.setFont(new Font("宋体", 1, 24));
		nameLabel.setFont(new Font("宋体", 1, 24));
		moneyLabel.setFont(new Font("宋体", 1, 24));
		bankLabel.setFont(new Font("宋体", 1, 24));
		remarkLabel.setFont(new Font("宋体", 1, 24));
		//设置label的字体颜色
		accountLabel.setForeground(Color.white);
		nameLabel.setForeground(Color.white);
		moneyLabel.setForeground(Color.white);
		bankLabel.setForeground(Color.white);
		remarkLabel.setForeground(Color.white);

		panel.setOpaque(false);//将panel设置为透明
		panel.setLayout(null);//将panel的布局设置为空
		//组件定位和大小
		nameLabel.setBounds(300,150,200,50);
		accountLabel.setBounds(300,200,400,50);
		bankLabel.setBounds(300,250,400,50);
		moneyLabel.setBounds(300,300,350,50);
		remarkLabel.setBounds(300,350,350,50);
		//将组件添加到panel上
		panel.add(accountLabel);
		panel.add(nameLabel);
		panel.add(moneyLabel);
		panel.add(bankLabel);
		panel.add(remarkLabel);

		frame.getContentPane().add(panel);
		frame.setVisible(true);//窗口可视化
	}

	@Override
	public void listen() {}
}