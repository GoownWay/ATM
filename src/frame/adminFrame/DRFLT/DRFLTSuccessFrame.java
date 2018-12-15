package frame.adminFrame.DRFLT;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import bean.Card;
import dao.Method;
import frame.adminFrame.AdminLoginedFrame;
import frameInterface.FrameInterface;
import model.MyFrame;

/**
 * @author hjx
 * 操作成功界面
 */
public class DRFLTSuccessFrame implements FrameInterface,ActionListener{
	public MyFrame frame;
	public JPanel panel;
	public JLabel label;
	Card card = new Card();
	Method method = new Method();
	JLabel labAccount, labName,labID,labBank,labRegisterTime,labPrivilege;

	@Override
	public void init() {
		frame = new MyFrame("成功界面");
		panel = new JPanel();
		labAccount = new JLabel();
		labName = new JLabel();
		labID = new JLabel();
		labBank = new JLabel();
		labRegisterTime = new JLabel();
		labPrivilege = new JLabel();
		label = new JLabel("<html><font size=7> 操作成功！</font><br><font size=5>Successful operation!</font></html>");
	}

	@Override
	public void show() {
		init();
		//设置标签位置和大小	
		labAccount.setBounds(280, 200, 550, 40);
		labName.setBounds(280,250, 550, 40);
		labID.setBounds(280, 300, 550, 40);
		labBank.setBounds(280, 350, 550, 40);
		labRegisterTime.setBounds(280, 400, 550, 40);
		labPrivilege.setBounds(280, 450, 550, 40);
		label.setBounds(380, 100, 550, 100);
		//成功标签位置大小
		frame.cancelButton.setText("<html><font size=6>返回</font><br>&nbsp;Cancel</html>");
		this.changeCSS(labAccount);
		this.changeCSS(labName);
		this.changeCSS(labID);
		this.changeCSS(labBank);
		this.changeCSS(labRegisterTime);
		this.changeCSS(labPrivilege);
		this.changeCSS(label);
		label.setFont(new Font("宋体", 0, 35));
		
		panel.add(labAccount);
		panel.add(labName);
		panel.add(labRegisterTime);
		panel.add(labPrivilege);
		panel.add(labID);
		panel.add(labBank);
		panel.add(label);
		panel.setLayout(null);//将panel的布局设置为空
		panel.setOpaque(false);//把panel设置为透明，这样容器的第二层也就是背景才能显示出来
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);	
		frame.confirmButton.setVisible(false);
	}

	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "<html><font size=6>返回</font><br>&nbsp;Cancel</html>") {
			AdminLoginedFrame f = new AdminLoginedFrame();
			f.show();
			f.listen();
			frame.dispose();
		}
	}
	
	public void changeCSS(JLabel j ) {
		j.setForeground(Color.white);
		j.setFont(new Font("宋体", 0, 30));
	}
	
	public void setAccount(Card card) {
		this.card = card;
		labID.setText("身 份 证："+ card.getId());
		labBank.setText("所属银行：" + card.getBank());
		labRegisterTime.setText("注册时间：" + String.valueOf(card.getDate()).substring(0,19));//String.valueOf(card.getRemainMoney()));
		labPrivilege.setText("状    态："+ card.getPrivilege());
		labAccount.setText("账    号：" + card.getAccount());
		labName.setText("姓    名：" + card.getName());
	}
}