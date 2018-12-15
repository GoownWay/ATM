package frame.userFrame.transfer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import frameInterface.FrameInterface;
import model.SlipFrame;

/**
 * @author ckx
 *转账成功后打印凭条的凭条显示界面
 */
public class TransferSlipFrame implements FrameInterface,ActionListener{
	public SlipFrame frame;

	public TransferSlipFrame() {
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
	public void listen() {//按钮添加监听
		frame.frame.cancelButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TransferFrame f = new TransferFrame();
		f.show();
		f.listen();
		frame.frame.dispose();
	}
}