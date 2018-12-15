package frame.userFrame.noCardDeposit;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import dao.userDao.DepositDao;
import frameInterface.FrameInterface;
import model.MyFrame;

/**
 * @author ckx
 *无卡操作存款的账户信息确认
 */
public class NoCardDepositShowFrame implements FrameInterface,ActionListener{
	public MyFrame frame;
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
	public void listen() {//按钮添加监听
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<html><font size=6>取消</font><br>&nbsp;Cancel</html>")
		{
			NoCardDepositFrame f = new NoCardDepositFrame();
			f.show();
			f.listen();
			f.text.setText(NoCardDepositFrame.tex);
			f.text.setForeground(Color.BLACK);
			f.money.setText(NoCardDepositFrame.mon);
			f.money.setForeground(Color.BLACK);
			if(NoCardDepositFrame.rem==""||NoCardDepositFrame.rem.length()==0)
			{
				f.remark.setText("请输入备注:");
				f.remark.setForeground(Color.lightGray);
			}
			else
			{
				f.remark.setText(NoCardDepositFrame.rem);
				f.remark.setForeground(Color.BLACK);
			}
			frame.dispose();
		}
		else
		{//确认按钮	
			DepositDao dp = new DepositDao();
			boolean flag = dp.updateInfoByAccount(NoCardDepositFrame.tex,NoCardDepositFrame.mon);
			if(flag)
			{
				NoCardDepositMessageFrame message = new NoCardDepositMessageFrame();
				message.show();
				message.listen();
				frame.dispose();
			}
		}
	}
}