package frame.userFrame.cardDeposit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import dao.userDao.DepositDao;
import frame.LoginFrame;
import frame.userFrame.LoginedFrame;
import frame.userFrame.noCardDeposit.NoCardDepositFrame;
import frameInterface.FrameInterface;

/**
 * @author ckx
 *有卡存款界面
 */
public class CardDepositFrame implements FrameInterface,ActionListener{

	NoCardDepositFrame frame;
	@Override
	public void init() {
		frame = new NoCardDepositFrame();
		frame.show();
	}

	@Override
	public void show() {
		init();
		frame.text.setVisible(false);
		frame.remark.setVisible(false);
		frame.textLabel.setVisible(false);
		frame.remarkLabel.setText("请输入少于10000的整百金额");

		frame.moneyLabel.setBounds(250, 320, 550, 50);
		frame.remarkLabel.setBounds(270, 170, 550, 50);
		frame.money.setBounds(350, 320, 350, 50);
	}

	@Override
	public void listen() {
		//监听文本框，第二个量为显示的提示内容
		frame.money.textListener(frame.money, "请输入存入金额:");
		//监听按钮
		frame.frame.cancelButton.addActionListener(this);
		frame.frame.confirmButton.addActionListener(this);
	}

	//对监听的组件作出反应
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("<html><font size=6>取消</font><br>&nbsp;Cancel</html>"))
		{
			LoginedFrame f = new LoginedFrame();
			f.show();//窗口展示
			f.listen();//窗口组件监听
			this.frame.frame.dispose();//把当前窗口关掉
		}
		else
		{
			DepositDao dp = new DepositDao();
			List<String> list = dp.queryInfoByAccount(LoginFrame.loginedAccount,frame.money.getText().trim());
			if(list!=null){
				NoCardDepositFrame.mon = frame.money.getText();
				//NoCardDepositFrame.mon = frame.money.getText();
				NoCardDepositFrame.rem = "";
				CardDepositShowFrame f = new CardDepositShowFrame();
				f.init();
				f.show();
				f.listen();
				frame.frame.dispose();
			}
		}
	}
}