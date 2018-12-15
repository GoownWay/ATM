package frame.userFrame.noCardDeposit;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dao.userDao.DepositDao;
import frame.WelcomeFrame;
import frameInterface.FrameInterface;
import model.MyFrame;
import model.MyTextField;													

/**
 * @author ckx
 *无卡操作输入界面
 */
public class NoCardDepositFrame implements FrameInterface,ActionListener{
	public MyFrame frame;
	JPanel panel,panel1;
	public MyTextField text,money,remark;
	public static String tex,mon,rem;
	public JLabel textLabel,moneyLabel,remarkLabel;
	public NoCardDepositFrame() {
		frame = new MyFrame("无卡操作");
	}
	@Override
	public void init() {
		panel = new JPanel();
		panel1 = new JPanel();
		text = new MyTextField();//输入账号文本框
		money = new MyTextField();//金额文本框
		remark = new MyTextField();//金额文本框
		textLabel = new JLabel("账号：");
		moneyLabel = new JLabel("金额：");
		remarkLabel = new JLabel("备注：");
	}

	@Override
	public void show() {
		init();//加载组件
		panel.setOpaque(false);//将panel设置为透明，背景图片才能显示出来
		panel.setLayout(null);//将panel的布局设置为空，这样能自定义组件位置
		text.setText("请输入存入账号:");//提示内容
		money.setText("请输入存入金额:");
		remark.setText("请输入备注:");

		money.setForeground(Color.lightGray);//将文本框的字体设置为提示的颜色
		text.setForeground(Color.lightGray);
		remark.setForeground(Color.lightGray);

		text.setFocusable(false);//text文本框失去焦点，即鼠标不在文本框上
		money.setFocusable(false);
		remark.setFocusable(false);

		//添加组件
		panel.add(text);
		panel.add(money);
		panel.add(remark);
		panel.add(textLabel);
		panel.add(moneyLabel);
		panel.add(remarkLabel);

		//设置组件大小和位置
		text.setBounds(360,170,350, 50);//设置文本框的位置和大小
		money.setBounds(360,270,350,50);
		remark.setBounds(360,370,350,50);
		textLabel.setBounds(250, 170, 100, 50);
		moneyLabel.setBounds(250, 270, 100, 50);
		remarkLabel.setBounds(250, 370, 100, 50);

		textLabel.setForeground(Color.WHITE);
		moneyLabel.setForeground(Color.WHITE);
		remarkLabel.setForeground(Color.WHITE);

		text.setFont(new Font("宋体", 1, 30));//设置文本框字体的大小
		money.setFont(new Font("宋体", 1, 30));
		remark.setFont(new Font("宋体", 1, 30));
		textLabel.setFont(new Font("宋体", 1, 30));
		moneyLabel.setFont(new Font("宋体", 1, 30));
		remarkLabel.setFont(new Font("宋体", 1, 30));

		frame.getContentPane().add(panel);
		frame.setVisible(true);//窗口可视化
	}

	//监听组件
	@Override
	public void listen() {
		//监听按钮
		frame.cancelButton.addActionListener(this);
		frame.confirmButton.addActionListener(this);
		//监听文本框
		money.textListener(money, "请输入存入金额:");
		text.textListener(text, "请输入存入账号:");
		remark.textListener(remark, "请输入备注:");
	}

	//对监听的组件作出反应
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<html><font size=6>取消</font><br>&nbsp;Cancel</html>")
		{
			WelcomeFrame f = new WelcomeFrame();
			f.show();
			f.listen();
			frame.dispose();
		}
		else//确认按钮
		{
			DepositDao dp = new DepositDao();
			List<String> list = dp.queryInfoByAccount(text.getText(),money.getText());

			if(list != null){
				tex = text.getText();
				mon = money.getText();
				if(remark.getText().equals("请输入备注:"))
					rem = "";
				else
					rem = remark.getText();
				NoCardDepositShowFrame f = new NoCardDepositShowFrame();
				f.init();
				f.accountLabel.setText(f.accountLabel.getText()+text.getText());
				f.nameLabel.setText(f.nameLabel.getText()+list.get(0));
				f.moneyLabel.setText(f.moneyLabel.getText()+"￥ "+money.getText());
				f.bankLabel.setText(f.bankLabel.getText()+list.get(1));
				f.remarkLabel.setText(f.remarkLabel.getText()+rem);
				f.show();
				f.listen();
				frame.dispose();
			}
		}
	}
}