package frame.adminFrame.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.adminDao.UpdateATMAndQueryUserTransferDao;
import frame.userFrame.noCardDeposit.NoCardDepositFrame;
import frameInterface.FrameInterface;

/**
 * @author hjx
 * 查询用户交易记录
 */
public class QueryUserInfoInputFrame implements ActionListener,FrameInterface{
	NoCardDepositFrame frame;
	@Override
	public void init() {
		frame = new NoCardDepositFrame();
		frame.show();
	}

	@Override
	public void show() {
		init();
		frame.moneyLabel.setVisible(false);
		frame.money.setVisible(false);
		frame.remark.setVisible(false);
		frame.remarkLabel.setText("请输入要查询的用户账号");
		frame.text.setText("请输入用户账号:");

		frame.textLabel.setBounds(260, 270, 550, 50);
		frame.remarkLabel.setBounds(300, 170, 550, 50);
		frame.text.setBounds(360, 270, 350, 50);
	}

	@Override
	public void listen() {
		//监听文本框，第二个量为显示的提示内容
		frame.text.textListener(frame.text, "请输入用户账号:");
		//监听按钮
		frame.frame.cancelButton.addActionListener(this);
		frame.frame.confirmButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html><font size=6>取消</font><br>&nbsp;Cancel</html>"))
		{
			Search f = new Search();
			f.show();//窗口展示
			f.listen();//窗口组件监听
			this.frame.frame.dispose();//把当前窗口关掉
		}
		else
		{
			UpdateATMAndQueryUserTransferDao dp = new UpdateATMAndQueryUserTransferDao();
			boolean i = dp.queryIsExist(frame.text.getText());
			if(i){			
				AdminSelectDateFrame f = new AdminSelectDateFrame(frame.text.getText());
				f.show();
				f.listen();
				frame.frame.dispose();
			}
		}
	}
}