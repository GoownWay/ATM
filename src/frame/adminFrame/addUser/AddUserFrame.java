package frame.adminFrame.addUser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import bean.Card;
import bean.User;
import dao.adminDao.AddUserDao;
import frame.adminFrame.AdminLoginedFrame;
import model.MyFrame;
import model.MyTextField;
import frameInterface.FrameInterface;

/**
 * @author hjx
 *开卡界面
 */
public class AddUserFrame implements FrameInterface,ActionListener{
	public MyFrame frame;
	JPanel panel;
	public MyTextField userName,userAddress,userTel,userID,cardBank;
	public JLabel LabName,LabGender,LabAddress,LabTel,LabID,LabCardBank;
	public JRadioButton b1, b2;//性别按钮
	public ButtonGroup group1;
	String sexStr = "";//性别、权限

	@Override
	public void init() {
		frame = new MyFrame("添加用户");
		panel = new JPanel();
		b1 = new JRadioButton("男/Man", false);
		b2 = new JRadioButton("女/Woman", false);
		group1 = new ButtonGroup();
		group1.add(b1); group1.add(b2);

		userName = new MyTextField();//姓名
		cardBank = new MyTextField();//密码
		userAddress = new MyTextField();//地址
		userTel = new MyTextField();//电话
		userID = new MyTextField();//身份证号

		LabName = new JLabel("姓名/Name：");
		LabCardBank = new JLabel("所属银行/Bank：");
		LabGender = new JLabel("性别/Gender：");
		LabAddress = new JLabel("地址/Adress：");
		LabTel = new JLabel("电话/Tel：");
		LabID = new JLabel("身份证号码/ID：");
	}
	@Override
	public void show() {
		init();
		panel.setOpaque(false);//将panel设置为透明，背景图片才能显示出来
		panel.setLayout(null);//将panel的布局设置为空，这样能自定义组件位置
		b1.setOpaque(false);
		b2.setOpaque(false);
		b1.setForeground(Color.WHITE);
		b2.setForeground(Color.WHITE);
		b1.setFont(new Font("宋体", 1, 20));
		b2.setFont(new Font("宋体", 1, 20));
		frame.cancelButton.setText("<html><font size=6>取消</font><br>&nbsp;Cancel</html>");
		frame.confirmButton.setText("<html><font size=6>确认</font><br>&nbsp;Comfirm</html>");

		userName.setText("例：张三");
		cardBank.setText("请输入所属银行");
		userAddress.setText("请输入用户地址");
		userTel.setText("1300000xxxx");
		userID.setText("请输入用户身份证");

		LabName.setForeground(Color.WHITE);
		LabCardBank.setForeground(Color.WHITE);
		LabAddress.setForeground(Color.WHITE);
		LabTel.setForeground(Color.WHITE);
		LabID.setForeground(Color.WHITE);
		LabGender.setForeground(Color.WHITE);

		userName.setForeground(Color.lightGray);
		cardBank.setForeground(Color.lightGray);
		userAddress.setForeground(Color.lightGray);
		userTel.setForeground(Color.lightGray);
		userID.setForeground(Color.lightGray);

		panel.add(userName);
		panel.add(cardBank);
		panel.add(b1);
		panel.add(b2);
		panel.add(userAddress);
		panel.add(userTel);
		panel.add(userID);

		panel.add(LabName);
		panel.add(LabCardBank);
		panel.add(LabGender);
		panel.add(LabAddress);
		panel.add(LabTel);
		panel.add(LabID);

		userName.setBounds(250,180,120,40);
		b1.setBounds(600,180,90,40);
		b2.setBounds(700,180,130,40);
		userID.setBounds(300,250,350,40);
		userAddress.setBounds(300,320,500,40);
		userTel.setBounds(250,390,200,40);
		cardBank.setBounds(300,460,160,40);

		LabName.setBounds(130,180,240, 40);//名字
		LabCardBank.setBounds(130,460,240, 40);//密码
		LabGender.setBounds(460,180, 240, 40);//性别
		LabAddress.setBounds(130,320,350, 40);//地址
		LabTel.setBounds(130,390,350, 40);//电话
		LabID.setBounds(130,250,350, 40);//身份证

		userName.setFont(new Font("宋体", 1, 20));
		cardBank.setFont(new Font("宋体", 1, 20));
		userAddress.setFont(new Font("宋体", 1, 20));
		userTel.setFont(new Font("宋体", 1, 20));
		userID.setFont(new Font("宋体", 1, 20));

		LabName.setFont(new Font("宋体", 1, 20));
		LabCardBank.setFont(new Font("宋体", 1, 20));
		LabAddress.setFont(new Font("宋体", 1, 20));
		LabTel.setFont(new Font("宋体", 1, 20));
		LabID.setFont(new Font("宋体", 1, 20));
		LabGender.setFont(new Font("宋体", 1, 20));

		frame.getContentPane().add(panel);
		frame.setVisible(true);

		//取得性别的值
		b1.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) { 				
				if (b1.isSelected()) {					
					sexStr = "男";				
				}			
			}		
		});

		b2.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) { 				
				if (b2.isSelected()) {					
					sexStr = "女";				
				}			
			}		
		});
		//面板获得焦点，不至于让文本框一开始就获得焦点而使得提示文字消失
		panel.setFocusable(true);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "<html><font size=6>确认</font><br>&nbsp;Comfirm</html>") {
			Card card = new Card();
			card.setId(userID.getText());
			card.setName(userName.getText());
			card.setBank(cardBank.getText());
			User user = new User();
			user.setId(userID.getText());
			user.setName(userName.getText());
			user.setGender(sexStr);
			user.setAddress(userAddress.getText());
			user.setTel(userTel.getText());
			AddUserDao add = new AddUserDao();
			boolean i = add.isNull(card,user);
			if(i)
			{				
				AddUserMessageFrame f = new AddUserMessageFrame(card,user);
				f.show();
				f.listen();
				frame.dispose();
			}
		}else if(e.getActionCommand() == "<html><font size=6>取消</font><br>&nbsp;Cancel</html>") {
			AdminLoginedFrame f = new AdminLoginedFrame();
			f.show();
			f.listen();
			frame.dispose();
		}
	}
	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
		userName.textListener(userName, "例：张三");
		cardBank.textListener(cardBank, "请输入所属银行");
		userAddress.textListener(userAddress, "请输入用户地址");
		userTel.textListener(userTel, "1300000xxxx");
		userID.textListener(userID, "请输入用户身份证");
	}
}