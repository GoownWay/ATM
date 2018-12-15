package frame.userFrame.withdraw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import frame.userFrame.LoginedFrame;
import frameInterface.FrameInterface;
import model.SlipFrame;

/**
 * @author ckx
 *存款后选择打印凭条的凭条显示界面
 */
public class WithdrawSlipFrame implements FrameInterface,ActionListener{
	public SlipFrame frame;

	public WithdrawSlipFrame() {
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
	public void listen() {
		frame.frame.cancelButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LoginedFrame f = new LoginedFrame();
		f.show();
		f.listen();
		frame.frame.dispose();
	}
}