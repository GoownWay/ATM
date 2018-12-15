package frame.userFrame.cardDeposit;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.userDao.DepositDao;
import frame.LoginFrame;
import frame.userFrame.noCardDeposit.NoCardDepositFrame;
import frame.userFrame.noCardDeposit.NoCardDepositShowFrame;
import frameInterface.FrameInterface;

/**
 * @author ckx
 *有卡存款信息确认界面
 */
public class CardDepositShowFrame implements FrameInterface,ActionListener{
	public NoCardDepositShowFrame frame;
	@Override
	public void init() {
		frame = new NoCardDepositShowFrame();
		frame.init();
	}
	@Override
	public void show() {
		frame.accountLabel.setVisible(false);
		frame.bankLabel.setVisible(false);
		frame.nameLabel.setVisible(false);
		frame.remarkLabel.setVisible(false);

		frame.moneyLabel.setText("      放入"+frame.moneyLabel.getText()+"￥ "+NoCardDepositFrame.mon);
		frame.frame.cancelButton.setText("<html><font size=6>继续放钞</font><br>&nbsp;Continue to increase</html>");
		frame.frame.confirmButton.setText("<html><font size=6>结束放钞</font><br>&nbsp;End add</html>");
		frame.show();
	}

	@Override
	public void listen() {//按钮添加监听
		frame.frame.cancelButton.addActionListener(this);
		frame.frame.confirmButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<html><font size=6>继续放钞</font><br>&nbsp;Continue to increase</html>")
		{
			CardDepositFrame f = new CardDepositFrame();
			f.show();
			f.listen();
			f.frame.money.setText(NoCardDepositFrame.mon);
			f.frame.money.setForeground(Color.BLACK);
			frame.frame.dispose();
		}
		else
		{//结束放钞
			DepositDao dp = new DepositDao();
			boolean flag = dp.updateInfoByAccount(LoginFrame.loginedAccount,NoCardDepositFrame.mon);
			if(flag)
			{
				CardDepositMessageFrame message = new CardDepositMessageFrame();
				message.show();
				message.listen();
				frame.frame.dispose();
			}
		}
	}
}