package frame.superAdminFrame.updateAdmin;

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

import bean.Admin;
import dao.superAdminDao.UpdateAdminDao;
import frame.superAdminFrame.SuperLoginedFrame;
import frameInterface.FrameInterface;
import model.MyFrame;

/**
 * @author fzj
 * 修改管理员信息
 */
public class UpdateAdminFrame implements FrameInterface,ActionListener{

	MyFrame frame;
	JPanel panel;
	public JLabel LabName, LabPWD, LabGender, LabAddress, LabTel, LabID,LabPrivilege;
	public JTextField adminName, adminPWD, adminGender, adminAddress;
	public JTextField adminTel, adminID,adminPrivilege;
	public JRadioButton b1, b2;// 性别按钮
	public ButtonGroup group1, group2;
	public int flag = 0;
	String sexStr, privilegeStr;// 性别、权限

	@Override
	public void init() {
		frame = new MyFrame("修改管理员");
		panel = new JPanel();
		LabName = new JLabel("姓名：");
		LabPWD = new JLabel("密码：");
		LabGender = new JLabel("性别：");
		LabAddress = new JLabel("地址：");
		LabTel = new JLabel("电话：");
		LabID = new JLabel("身份证：");
		LabPrivilege = new JLabel("权限：");
		
		adminName = new JTextField(10);// 姓名
		adminPWD = new JTextField(10);// 密码
		adminGender = new JTextField(10);// 性别
		adminAddress = new JTextField(20);// 地址
		adminTel = new JTextField(10);// 电话
		adminID = new JTextField(20);// 身份证号
		adminPrivilege = new JTextField(10);//权限

		b1 = new JRadioButton("男", InputUpdateAccount.admin.getGeneder().equals("男")?true:false);
		b2 = new JRadioButton("女", InputUpdateAccount.admin.getGeneder().equals("女")?true:false);
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

		adminName.setText(InputUpdateAccount.admin.getName());
		adminPWD.setText(InputUpdateAccount.admin.getPassword());
		adminGender.setText(InputUpdateAccount.admin.getGeneder());
		adminAddress.setText(InputUpdateAccount.admin.getAddress());
		adminTel.setText(InputUpdateAccount.admin.getTel());
		adminID.setText(InputUpdateAccount.admin.getId());
		adminPrivilege.setText(InputUpdateAccount.admin.getPrivilege());
		
		adminName.setForeground(Color.lightGray);
		adminPWD.setForeground(Color.lightGray);
		adminGender.setForeground(Color.lightGray);
		adminAddress.setForeground(Color.lightGray);
		adminTel.setForeground(Color.lightGray);
		adminID.setForeground(Color.lightGray);
		adminPrivilege.setForeground(Color.lightGray);

		// 监听文本框 //监听接口写好后修改此处！！！！！！！
		adminName.addMouseListener(null);
		adminPWD.addMouseListener(null);
		adminGender.addMouseListener(null);
		adminAddress.addMouseListener(null);
		adminTel.addMouseListener(null);
		adminID.addMouseListener(null);
		adminPrivilege.addMouseListener(null);

		panel.add(adminName);
		panel.add(adminPWD);
		panel.add(adminAddress);
		panel.add(adminTel);
		panel.add(adminID);
		panel.add(adminPrivilege);
		panel.add(LabName);
		panel.add(LabPWD);
		panel.add(LabGender);
		panel.add(LabAddress);
		panel.add(LabTel);
		panel.add(LabID);
		panel.add(LabPrivilege);
		panel.add(b1);
		panel.add(b2);

		adminName.setBounds(380, 120, 300, 40);
		b1.setBounds(380, 180, 50, 40);
		b2.setBounds(450, 180, 50, 40);
		adminPWD.setBounds(380, 240, 300, 40);
		adminTel.setBounds(380, 300, 300, 40);
		adminID.setBounds(380, 360, 300, 40);
		adminAddress.setBounds(380, 420, 300, 40);
		adminPrivilege.setBounds(380, 480, 300, 40);
		adminName.setFont(new Font("宋体", 1, 20));
		adminPWD.setFont(new Font("宋体", 1, 20));
		adminGender.setFont(new Font("宋体", 1, 20));
		adminAddress.setFont(new Font("宋体", 1, 20));
		adminTel.setFont(new Font("宋体", 1, 20));
		adminID.setFont(new Font("宋体", 1, 20));
		adminPrivilege.setFont(new Font("宋体", 1, 20));

		LabName.setBounds(300, 120, 120, 40);// 名字
		LabGender.setBounds(300, 180, 120, 40);// 性别
		LabPWD.setBounds(300, 240, 120, 40);// 密码
		LabTel.setBounds(300, 300, 350, 40);// 电话
		LabID.setBounds(300, 360, 350, 40);// 身份证
		LabAddress.setBounds(300, 420, 350, 40);// 地址
		LabPrivilege.setBounds(300, 480, 350, 40);// 权限
		
		this.changeFont(LabName);
		this.changeFont(LabPWD);
		this.changeFont(LabGender);
		this.changeFont(LabAddress);
		this.changeFont(LabTel);
		this.changeFont(LabID);
		this.changeFont(LabPrivilege);
		this.changeCss(b1);
		this.changeCss(b2);

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
			UpdateAdminDao ua = new UpdateAdminDao();
			Admin a = new Admin();
			a.setAccount(InputUpdateAccount.admin.getAccount());
			a.setName(adminName.getText());
			a.setGeneder(b1.isSelected()?"男":"女");
			a.setPassword(adminPWD.getText());
			a.setId(adminID.getText());
			a.setTel(adminTel.getText());
			a.setAddress(adminAddress.getText());
			a.setPrivilege(adminPrivilege.getText());
			if(ua.updateAdminInfo(a)){
				UpdateAdminMessageFrame um = new UpdateAdminMessageFrame();
				um.show();
				um.listen();
				frame.dispose();
			}else{
				JOptionPane.showMessageDialog(null, "操作失败！", "错误提示", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
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