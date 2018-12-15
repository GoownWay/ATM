package frame.adminFrame.addUser;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import bean.Card;
import bean.User;
import dao.Method;
import frameInterface.FrameInterface;
import model.MyFrame;

/**
 * @author hjx
 *添加用户信息确认界面
 */
public class AddUserMessageFrame implements FrameInterface, ActionListener {
	MyFrame frame;
	JPanel panel;
	public JLabel LabName,LabcardBank,LabGender,LabAddress,LabTel,LabID,LabAccount,LabRigisterTime;//,LabPrivilege
	Date date;
	SimpleDateFormat dateFormat;
	Card newCard;
	User newUser;
	String account=null;
	public AddUserMessageFrame(Card card, User user) {
		newCard = card;
		newUser = user;
	}
	@Override
	public void init() {
		//new日期对象
		date = new Date();
		//转换提日期输出格式
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		frame = new MyFrame("添加用户_信息确认");
		panel = new JPanel();
		LabAccount = new JLabel();
		LabName = new JLabel();
		LabcardBank = new JLabel();
		LabGender = new JLabel();
		LabAddress = new JLabel();
		LabTel = new JLabel();
		LabID = new JLabel();
		LabRigisterTime = new JLabel();
	}

	@Override
	public void show() {
		init();
		panel.setOpaque(false);//将panel设置为透明，背景图片才能显示出来
		panel.setLayout(null);//将panel的布局设置为空，这样能自定义组件位置		
		newCard.setDate(Timestamp.valueOf(dateFormat.format(date)));
		Method method = new Method();
		account = method.createUserAccount();
		newCard.setAccount(account);
		LabAccount.setText("账号/Account："+account);
		LabName.setText("姓名/Name："+newCard.getName());
		LabcardBank.setText("所属银行："+newCard.getBank());
		LabGender.setText("性别/Gender："+newUser.getGender());
		LabAddress.setText("地址/Adress："+newUser.getAddress());
		LabTel.setText("电话/Tel："+newUser.getTel());
		LabID.setText("身份证号码/ID："+newUser.getId());
		LabRigisterTime.setText("注册时间/Registered："+dateFormat.format(date));
		frame.cancelButton.setText("<html><font size=6>取消</font><br>&nbsp;Cancel</html>");
		frame.confirmButton.setText("<html><font size=6>确认</font><br>&nbsp;Comfirm</html>");

		//标签添加字体样式
		this.changeFont(LabAccount);
		this.changeFont(LabName);
		this.changeFont(LabcardBank);
		this.changeFont(LabGender);
		this.changeFont(LabAddress);
		this.changeFont(LabTel);
		this.changeFont(LabID);
		this.changeFont(LabRigisterTime);

		LabName.setBounds(130,130,240, 40);//名字
		LabGender.setBounds(460,130, 240, 40);//性别 
		LabAccount.setBounds(130,200,400, 40);//设置标签框的位置和大小
		LabID.setBounds(130,270,400, 40);//身份证
		LabTel.setBounds(130,340,300, 40);//电话
		LabAddress.setBounds(130,410,700, 40);//地址
		LabcardBank.setBounds(130,480,300, 40);//所属银行
		LabRigisterTime.setBounds(400,480,600, 40);//注册时间

		panel.add(LabAccount);//添加标签到面板
		panel.add(LabName);
		panel.add(LabcardBank);
		panel.add(LabGender);
		panel.add(LabAddress);
		panel.add(LabTel);
		panel.add(LabID);
		panel.add(LabRigisterTime);

		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "<html><font size=6>确认</font><br>&nbsp;Comfirm</html>") {
			CreateCardInputPasswordFrame f = new CreateCardInputPasswordFrame(newUser,newCard);
			f.show();
			f.listen();
			frame.dispose();
		}else if(e.getActionCommand() == "<html><font size=6>取消</font><br>&nbsp;Cancel</html>") {
			AddUserFrame f = new AddUserFrame();
			f.show();
			f.userName.setText(newCard.getName());
			f.userID.setText(newCard.getId());
			f.userAddress.setText(newUser.getAddress());
			f.userTel.setText(newUser.getTel());
			f.cardBank.setText(newCard.getBank());
			f.b1.setSelected(newUser.getGender().equals("男")?true:false);
			f.b2.setSelected(newUser.getGender().equals("女")?true:false);
			if(newUser.getGender().equals("男"))
				f.sexStr = "男";
			else
				f.sexStr = "女";
			f.userName.setForeground(Color.BLACK);
			f.userID.setForeground(Color.BLACK);
			f.userAddress.setForeground(Color.BLACK);
			f.userTel.setForeground(Color.BLACK);
			f.cardBank.setForeground(Color.BLACK);
			f.listen();
			frame.dispose();
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
}