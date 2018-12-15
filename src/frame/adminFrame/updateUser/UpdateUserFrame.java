package frame.adminFrame.updateUser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import bean.User;
import dao.Method;
import frame.adminFrame.DRFLT.DRFLTSuccessFrame;
import frameInterface.FrameInterface;
import model.MyFrame;

/**
 * @author hjx
 *修改用户信息界面
 */
public class UpdateUserFrame  implements FrameInterface,ActionListener{
	User user = new User();
	Method method = new Method();
	MyFrame frame;
	JPanel panel;

	String id;//身份证号
	String name;//姓名
	String gender;//性别
	String address;//地址
	String tel;//电话
	public JTextField Name,Address,Tel;
	public JLabel labName,labGender,labAddress,labTel;
	public JRadioButton b1, b2;//性别按钮
	public ButtonGroup group1;
	public int flag = 0; 

	@Override
	public void init() {
		frame = new MyFrame("用户信息确认");
		panel = new JPanel();
		labName = new JLabel();
		labGender = new JLabel();
		labAddress = new JLabel();
		labTel = new JLabel();
		Name = new JTextField();
		Address = new JTextField();
		Tel = new JTextField();
		b1 = new JRadioButton("男");
		b2 = new JRadioButton("女");
		group1 = new ButtonGroup();
		group1.add(b1); group1.add(b2);
	}

	@Override
	public void show() {
		init();
		b1.setOpaque(false);
		b2.setOpaque(false);

		labName.setText("姓    名：");
		labGender.setText("性    别：");
		labAddress.setText("地    址：");
		labTel.setText("电    话：");
		labName.setBounds(240, 160, 150, 40);
		labGender.setBounds(240, 240, 150, 40);
		labAddress.setBounds(240, 320, 150, 40);
		labTel.setBounds(240, 400, 150, 40);

		Name.setBounds(400, 160, 200, 35);
		b1.setBounds(400, 240, 60, 35);
		b2.setBounds(470, 240, 60, 35);
		Address.setBounds(400, 320, 400, 35);
		Tel.setBounds(400, 400, 200, 35);

		this.changeLabelCSS(labAddress);//Label、TextFeild添加样式
		this.changeLabelCSS(labGender);
		this.changeLabelCSS(labName);
		this.changeLabelCSS(labAddress);
		this.changeLabelCSS(labTel);
		this.changeTextCSS(Name);
		this.changeTextCSS(Address);
		this.changeTextCSS(Tel);
		b1.setForeground(Color.white);
		b1.setFont(new Font("宋体", 0, 30));
		b2.setForeground(Color.white);
		b2.setFont(new Font("宋体", 0, 30));

		panel.setOpaque(false);// 将panel设置为透明，背景图片才能显示出来
		panel.setLayout(null);// 将panel的布局设置为空，这样能自定义组件位置

		panel.add(Address);
		panel.add(Name);
		panel.add(Tel);
		panel.add(b1);
		panel.add(b2);
		panel.add(labGender);
		panel.add(labName);
		panel.add(labAddress);
		panel.add(labTel);

		frame.cancelButton.setText("<html><font size=6>返回</font><br>&nbsp;Cancel</html>");
		frame.confirmButton.setText("<html><font size=6>确认</font><br>&nbsp;Comfirm</html>");

		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<html><font size=6>返回</font><br>&nbsp;Cancel</html>") {
			UpdateUserInputFrame f = new UpdateUserInputFrame();
			f.show();
			f.listen();
			frame.dispose();
		}else if(e.getActionCommand()=="<html><font size=6>确认</font><br>&nbsp;Comfirm</html>") {
			//根据flag跳转到特定的Dao进行操作
			user.setAddress(Address.getText());
			user.setGender(b1.isSelected()?"男":"女");
			user.setName(Name.getText());
			user.setTel(Tel.getText());
			Method method = new Method();
			boolean i = method.updateUser(user);
			if(i) {
				DRFLTSuccessFrame f = new DRFLTSuccessFrame();
				f.show();
				f.listen();
				frame.dispose();
			}else JOptionPane.showMessageDialog(null, "更新失败", "错误提示", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
	}

	public void changeLabelCSS(JLabel j) {
		j.setForeground(Color.white);
		j.setFont(new Font("宋体", 0, 30));
	}
	public void changeTextCSS(JTextField j) {
		j.setFont(new Font("宋体", 0, 30));
	}

	public void setInfo(User user) {
		this.user = user;
		Name.setText(user.getName());
		Address.setText(user.getAddress());
		Tel.setText(user.getTel());
		b1.setSelected(user.getGender().equals("男")?true:false);
		b2.setSelected(user.getGender().equals("女")?true:false);
	}
}