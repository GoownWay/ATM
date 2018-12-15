package frame.adminFrame.DRFLT;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import bean.Card;
import dao.Method;
import dao.adminDao.*;
import frameInterface.FrameInterface;
import model.MyFrame;

/**
 * @author hjx
 * 用户信息确认
 */
public class UserInfoFrame implements FrameInterface, ActionListener {
	MyFrame frame;
	JPanel panel;
	JLabel labAccount, labName,labID,labBank,labRegisterTime,labPrivilege;
	Card card = new Card();
	Method method = new Method();
	int flag;

	@Override
	public void init() {
		frame = new MyFrame("用户信息确认");
		panel = new JPanel();
		labAccount = new JLabel();
		labName = new JLabel();
		labID = new JLabel();
		labBank = new JLabel();
		labRegisterTime = new JLabel();
		labPrivilege = new JLabel();
	}

	@Override
	public void show() {
		init();
		//设置标签位置和大小		
		labAccount.setBounds(280, 160, 550, 40);
		labName.setBounds(280,210, 550, 40);
		labID.setBounds(280, 260, 550, 40);
		labBank.setBounds(280, 310, 550, 40);
		labRegisterTime.setBounds(280, 360, 550, 40);
		labPrivilege.setBounds(280, 410, 550, 40);

		this.changeCSS(labAccount);
		this.changeCSS(labName);
		this.changeCSS(labID);
		this.changeCSS(labBank);
		this.changeCSS(labRegisterTime);
		this.changeCSS(labPrivilege);

		panel.setOpaque(false);// 将panel设置为透明，背景图片才能显示出来
		panel.setLayout(null);// 将panel的布局设置为空，这样能自定义组件位置

		panel.add(labAccount);
		panel.add(labName);
		panel.add(labRegisterTime);
		panel.add(labPrivilege);
		panel.add(labID);
		panel.add(labBank);

		frame.cancelButton.setText("<html><font size=6>返回</font><br>&nbsp;Cancel</html>");
		frame.confirmButton.setText("<html><font size=6>确认</font><br>&nbsp;Comfirm</html>");

		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<html><font size=6>返回</font><br>&nbsp;Cancel</html>") {
			//根据flag返回到原本的界面
			if(flag ==0) {
				//冻结
				FreezeUserFrame f = new FreezeUserFrame();
				f.show();
				f.listen();
				frame.dispose();
			}else if(flag == 1) {
				//挂失
				LostUserFrame f = new LostUserFrame();
				f.show();
				f.listen();
				frame.dispose();
			}else if(flag == 2) {
				//解冻
				ThawUserFrame f = new ThawUserFrame();
				f.show();
				f.listen();
				frame.dispose();
			}else if(flag == 3) {
				//解挂
				ReleaseUserFrame f = new ReleaseUserFrame();
				f.show();
				f.listen();
				frame.dispose();
			}else if(flag == 4) {
				//注销
				DeleteUserFrame f = new DeleteUserFrame();
				f.show();
				f.listen();
				frame.dispose();
			}
		}else if(e.getActionCommand()=="<html><font size=6>确认</font><br>&nbsp;Comfirm</html>") {
			//根据flag跳转到特定的Dao进行操作
			if(flag ==0) {
				//冻结
				FreezeUserDao freeze = new FreezeUserDao();
				boolean i = freeze.queryInfoToFreeze(card);
				if(i) {
					DRFLTSuccessFrame f = new DRFLTSuccessFrame();
					f.show();
					f.listen();
					f.setAccount(card);
					frame.dispose();
				}else JOptionPane.showMessageDialog(null, "冻结失败", "错误提示", JOptionPane.ERROR_MESSAGE);
			}else if(flag == 1) {
				//挂失
				LostUserDao lost = new LostUserDao();
				boolean i = lost.queryInfoToLost(card);
				if(i) {
					DRFLTSuccessFrame f = new DRFLTSuccessFrame();
					f.show();
					f.listen();
					f.setAccount(card);
					frame.dispose();
				}else JOptionPane.showMessageDialog(null, "挂失失败", "错误提示", JOptionPane.ERROR_MESSAGE);
			}else if(flag == 2 || flag == 3) {
				//解冻解挂
				ThawReleaseUserDao tr = new ThawReleaseUserDao();
				boolean i = tr.queryInfoToThawRelease(card);
				if(i) {
					DRFLTSuccessFrame f = new DRFLTSuccessFrame();
					f.show();
					f.listen();
					f.setAccount(card);
					frame.dispose();
				}else JOptionPane.showMessageDialog(null, "恢复正常失败！", "错误提示", JOptionPane.ERROR_MESSAGE);
			}else if(flag == 4) {
				//注销
				DeleteUserDao lost = new DeleteUserDao();
				boolean i = lost.queryInfoToDelete(card);
				if(i) {
					DRFLTSuccessFrame f = new DRFLTSuccessFrame();
					f.show();
					f.listen();
					f.setAccount(card);
					frame.dispose();
				}else JOptionPane.showMessageDialog(null, "注销失败", "错误提示", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
	}

	public void setAccount(Card card, int flag) {
		this.card = card;
		this.flag = flag;
		labID.setText("身 份 证："+ card.getId());
		labBank.setText("所属银行：" + card.getBank());
		labRegisterTime.setText("注册时间：" + String.valueOf(card.getDate()).substring(0,19));//String.valueOf(card.getRemainMoney()));
		labPrivilege.setText("状    态："+ card.getPrivilege());
		labAccount.setText("账    号：" + card.getAccount());
		labName.setText("姓    名：" + card.getName());
	}

	public void changeCSS(JLabel j ) {
		j.setForeground(Color.white);
		j.setFont(new Font("宋体", 0, 30));
	}
}