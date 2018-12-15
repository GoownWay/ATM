package frame.userFrame.transfer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import dao.userDao.TransferDao;
import frame.userFrame.LoginedFrame;
import frame.userFrame.noCardDeposit.NoCardDepositFrame;
import frameInterface.FrameInterface;

/**
 * @author ckx
 *转账输入界面
 */
public class TransferFrame implements ActionListener, FrameInterface {

	NoCardDepositFrame frame;
	@Override
	public void init() {
		frame = new NoCardDepositFrame();
		frame.frame.setTitle("转账");
		frame.show();
	}

	@Override
	public void show() {
		init();//加载组件
		frame.text.setText("请输入转入账号:");
		frame.money.setText("请输入转入金额:");
		frame.remark.setText("请输入备注:");
	}

	//监听组件
	@Override
	public void listen() {
		frame.frame.cancelButton.addActionListener(this);
		frame.frame.confirmButton.addActionListener(this);
		frame.text.textListener(frame.text, "请输入转入账号:");
		frame.money.textListener(frame.money, "请输入转入金额:");
		frame.remark.textListener(frame.remark, "请输入备注:");
	}

	//对监听的组件作出反应
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<html><font size=6>取消</font><br>&nbsp;Cancel</html>")
		{
			LoginedFrame f = new LoginedFrame();
			f.show();
			f.listen();
			frame.frame.dispose();
		}
		else
		{//确认按钮
			TransferDao tf = new TransferDao();
			List<String> list = tf.queryInfoByAccount(frame.text.getText(),frame.money.getText());

			if(list != null){
				NoCardDepositFrame.tex = frame.text.getText();
				NoCardDepositFrame.mon = frame.money.getText();
				if(frame.remark.getText().equals("请输入备注:"))
					NoCardDepositFrame.rem = "";
				else
					NoCardDepositFrame.rem = frame.remark.getText();
				TransferShowFrame f = new TransferShowFrame();
				f.init();
				f.accountLabel.setText(f.accountLabel.getText()+frame.text.getText());
				f.nameLabel.setText(f.nameLabel.getText()+list.get(0));
				f.moneyLabel.setText(f.moneyLabel.getText()+"￥ "+frame.money.getText());
				f.bankLabel.setText(f.bankLabel.getText()+list.get(1));
				f.remarkLabel.setText(f.remarkLabel.getText()+NoCardDepositFrame.rem);
				f.show();
				f.listen();
				frame.frame.dispose();
			}
		}
	}
}