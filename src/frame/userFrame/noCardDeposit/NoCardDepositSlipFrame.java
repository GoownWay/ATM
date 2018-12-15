package frame.userFrame.noCardDeposit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import frameInterface.FrameInterface;
import model.SlipFrame;

/**
 * @author ckx
 *无卡操作打印凭条的凭条显示界面
 */
public class NoCardDepositSlipFrame implements FrameInterface,ActionListener{
	public SlipFrame frame;

	public NoCardDepositSlipFrame() {
		frame= new SlipFrame();
		frame.init();

	}
	@Override
	public void init() {}

	@Override
	public void show() {
		frame.frame.confirmButton.setVisible(false);
		frame.frame.cancelButton.setBounds(700,550,250,60);
		frame.show();
	}

	@Override
	public void listen() {//按钮监听
		frame.frame.cancelButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		NoCardDepositFrame f = new NoCardDepositFrame();
		f.show();
		f.listen();
		frame.frame.dispose();
	}
}