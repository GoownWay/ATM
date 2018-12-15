package frame.superAdminFrame.addAdmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import bean.Admin;
import dao.superAdminDao.AddAdminDao;
import frame.superAdminFrame.SuperLoginedFrame;
import frameInterface.FrameInterface;
import model.MyFrame;
import model.MyTextField;

/**
 * @author fzj
 * 添加管理员
 */
public class AddAdminFrame implements FrameInterface, ActionListener {
	MyFrame frame;
	JPanel panel;
	public JLabel LabName, LabPWD, LabGender, LabAddress, LabTel, LabID;
	public MyTextField adminName, adminPWD, adminAddress,adminTel, adminID;
	public JRadioButton b1, b2;// 性别按钮
	public ButtonGroup group1, group2;
	public int flag = 0;
	String sexStr, privilegeStr;// 性别、权限

	@Override
	public void init() {
		frame = new MyFrame("添加管理员");
		panel = new JPanel();
		LabName = new JLabel("姓名：");
		LabPWD = new JLabel("密码：");
		LabGender = new JLabel("性别：");
		LabAddress = new JLabel("地址：");
		LabTel = new JLabel("电话：");
		LabID = new JLabel("身份证：");

		adminName = new MyTextField();// 姓名
		adminPWD = new MyTextField();// 密码
		adminAddress = new MyTextField();// 地址
		adminTel = new MyTextField();// 电话
		adminID = new MyTextField();// 身份证号

		b1 = new JRadioButton("男", true);
		b2 = new JRadioButton("女", false);
		group1 = new ButtonGroup();
		group2 = new ButtonGroup();
		group1.add(b1);
		group1.add(b2);
	}

	@Override
	public void show() {
		init();
		panel.setOpaque(false);// 将panel设置为透明，背景图片才能显示出来
		panel.setLayout(null);// 将panel的布局设置为空，这样能自定义组件位置

		adminName.setText("请输入管理员姓名");
		adminPWD.setText("请输入密码");
		adminAddress.setText("请输入管理员地址");
		adminTel.setText("请输入管理员电话");
		adminID.setText("请输入管理员身份证");

		adminName.setForeground(Color.lightGray);
		adminPWD.setForeground(Color.lightGray);
		adminAddress.setForeground(Color.lightGray);
		adminTel.setForeground(Color.lightGray);
		adminID.setForeground(Color.lightGray);

		panel.add(adminName);
		panel.add(adminPWD);
		panel.add(adminAddress);
		panel.add(adminTel);
		panel.add(adminID);
		panel.add(LabName);
		panel.add(LabPWD);
		panel.add(LabGender);
		panel.add(LabAddress);
		panel.add(LabTel);
		panel.add(LabID);
		panel.add(b1);
		panel.add(b2);

		adminName.setBounds(380, 130, 300, 40);
		b1.setBounds(380, 200, 50, 40);
		b2.setBounds(450, 200, 50, 40);
		adminPWD.setBounds(380, 270, 300, 40);
		adminTel.setBounds(380, 340, 300, 40);
		adminID.setBounds(380, 410, 300, 40);
		adminAddress.setBounds(380, 480, 300, 40);

		adminName.setFont(new Font("宋体", 1, 20));
		adminPWD.setFont(new Font("宋体", 1, 20));
		adminAddress.setFont(new Font("宋体", 1, 20));
		adminTel.setFont(new Font("宋体", 1, 20));
		adminID.setFont(new Font("宋体", 1, 20));

		LabName.setBounds(300, 130, 120, 40);// 名字
		LabGender.setBounds(300, 200, 120, 40);// 性别
		LabPWD.setBounds(300, 270, 120, 40);// 密码
		LabTel.setBounds(300, 340, 350, 40);// 电话
		LabID.setBounds(300, 410, 350, 40);// 身份证
		LabAddress.setBounds(300, 480, 350, 40);// 地址

		this.changeFont(LabName);
		this.changeFont(LabPWD);
		this.changeFont(LabGender);
		this.changeFont(LabAddress);
		this.changeFont(LabTel);
		this.changeFont(LabID);
		this.changeCss(b1);
		this.changeCss(b2);

		// 取得性别的值
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (b1.isSelected()) {
					sexStr = b1.getText();
				}
			}
		});

		// 面板获得焦点，不至于让文本框一开始就获得焦点而使得提示文字消失
		panel.setFocusable(true);

		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html><font size=6>取消</font><br>&nbsp;Cancel</html>")){
			SuperLoginedFrame superLogined = new SuperLoginedFrame();
			superLogined.show();
			superLogined.listen();
			frame.dispose();
		}
		else{
			AddAdminDao aad = new AddAdminDao();
			Admin a = new Admin();
			a.setName(adminName.getText());
			a.setGeneder(b1.isSelected()?"男":"女");
			a.setPassword(adminPWD.getText());
			a.setId(adminID.getText());
			a.setTel(adminTel.getText());
			a.setAddress(adminAddress.getText());
			a.setPrivilege("管理员");

			if(aad.addAdminInfo(a)){
				AddAdminMessageFrame am = new AddAdminMessageFrame();
				am.show();
				am.listen();
				frame.dispose();
			}

		}
	}

	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
		adminName.textListener(adminName,"请输入管理员姓名");
		adminPWD.textListener(adminPWD,"请输入密码");
		adminAddress.textListener(adminAddress,"请输入管理员地址");
		adminTel.textListener(adminTel,"请输入管理员电话");
		adminID.textListener(adminID,"请输入管理员身份证");
	}

	public void changeFont(JLabel j) {
		j.setFont(new Font("宋体", 1, 20));
		j.setForeground(Color.white);
	}
	public void changeCss(JRadioButton j) {
		j.setFont(new Font("宋体", 1, 20));
		j.setForeground(Color.white);
		j.setOpaque(false);
	}
}