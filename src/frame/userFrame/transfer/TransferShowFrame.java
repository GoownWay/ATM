package frame.userFrame.transfer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dao.userDao.TransferDao;
import frame.userFrame.noCardDeposit.NoCardDepositFrame;
import frameInterface.FrameInterface;
import model.MyFrame;

/**
 * @author ckx
 *转入账户信息确认界面
 */
public class TransferShowFrame implements FrameInterface,ActionListener{
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
		accountLabel.setFont(new Font("宋体", 1, 24));
		nameLabel.setFont(new Font("宋体", 1, 24));
		moneyLabel.setFont(new Font("宋体", 1, 24));
		bankLabel.setFont(new Font("宋体", 1, 24));
		remarkLabel.setFont(new Font("宋体", 1, 24));

		accountLabel.setForeground(Color.white);
		nameLabel.setForeground(Color.white);
		moneyLabel.setForeground(Color.white);
		bankLabel.setForeground(Color.white);
		remarkLabel.setForeground(Color.white);

		panel.setOpaque(false);
		panel.setLayout(null);//将panel的布局设置为空
		//设置组件定位和大小
		nameLabel.setBounds(300,150,200,50);
		accountLabel.setBounds(300,200,400,50);
		bankLabel.setBounds(300,250,400,50);
		moneyLabel.setBounds(300,300,350,50);
		remarkLabel.setBounds(300,350,350,50);

		panel.add(accountLabel);
		panel.add(nameLabel);
		panel.add(moneyLabel);
		panel.add(bankLabel);
		panel.add(remarkLabel);

		frame.getContentPane().add(panel);
		frame.setVisible(true);//窗口可视化
	}

	@Override
	public void listen() {
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<html><font size=6>取消</font><br>&nbsp;Cancel</html>")
		{
			TransferFrame f = new TransferFrame();
			f.show();
			f.listen();
			f.frame.text.setText(NoCardDepositFrame.tex);
			f.frame.text.setForeground(Color.BLACK);
			f.frame.money.setText(NoCardDepositFrame.mon);
			f.frame.money.setForeground(Color.BLACK);
			if(NoCardDepositFrame.rem==""||NoCardDepositFrame.rem.length()==0)
			{
				f.frame.remark.setText("请输入备注:");
				f.frame.remark.setForeground(Color.lightGray);
			}
			else
			{
				f.frame.remark.setText(NoCardDepositFrame.rem);
				f.frame.remark.setForeground(Color.BLACK);
			}
			frame.dispose();
		}
		else
		{//确认按钮	
			TransferDao dp = new TransferDao();
			boolean flag = dp.updateInfoByAccount(NoCardDepositFrame.tex,NoCardDepositFrame.mon);
			if(flag)
			{
				TransferMessageFrame message = new TransferMessageFrame();
				message.show();
				message.listen();
				frame.dispose();
			}
		}
	}
}