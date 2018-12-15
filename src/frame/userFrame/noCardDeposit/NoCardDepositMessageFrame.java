package frame.userFrame.noCardDeposit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import dao.userDao.DepositDao;
import frameInterface.FrameInterface;
import model.MessageFrame;

/**
 * @author ckx
 *无卡操作存款成功界面
 */
public class NoCardDepositMessageFrame implements FrameInterface,ActionListener{
	MessageFrame frame;

	@Override
	public void init() {
		frame = new MessageFrame();
	}

	@Override
	public void show() {
		init();
		frame.show();
	}

	@Override
	public void listen() {//按钮添加监听
		frame.frame.cancelButton.addActionListener(this);
		frame.frame.confirmButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html><font size=6>返回</font><br>&nbsp;Cancel</html>"))
		{
			NoCardDepositFrame d = new NoCardDepositFrame();
			d.show();
			d.listen();
			frame.frame.dispose();
		}
		else
		{//确认按钮
			DepositDao dp = new DepositDao();
			List<String> list = dp.gettickertape();
			NoCardDepositSlipFrame slip = new NoCardDepositSlipFrame();
			slip.frame.label1.setText(slip.frame.label1.getText()+list.get(0));
			slip.frame.label2.setText(slip.frame.label2.getText()+list.get(1));
			slip.frame.label3.setText(slip.frame.label3.getText()+list.get(2));
			slip.frame.label4.setText(slip.frame.label4.getText()+list.get(3));
			slip.frame.label5.setText(slip.frame.label5.getText()+" ￥ "+list.get(4));
			slip.frame.label6.setText(slip.frame.label6.getText()+list.get(5));
			slip.frame.label7.setText(slip.frame.label7.getText()+list.get(6));
			slip.frame.label8.setText(slip.frame.label8.getText()+list.get(7));
			slip.frame.label9.setText(slip.frame.label9.getText()+" ￥ "+list.get(8));
			slip.show();;
			slip.listen();
			frame.frame.dispose();
		}
	}
}