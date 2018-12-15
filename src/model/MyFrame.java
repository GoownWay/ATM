package model;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//窗体类模板，带背景设置方法和确定、取消按钮
public class MyFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	public JLabel label;
	public MyButton cancelButton,confirmButton;
	public MyFrame(String title) {
		super(title);//标题

		cancelButton = new MyButton("<html><font size=6>取消</font><br>&nbsp;Cancel</html>");
		confirmButton = new MyButton("<html><font size=6>确认</font><br>&nbsp;Comfirm</html>");

		cancelButton.setBounds(20,550,250,60);//设置按钮的位置和大小
		confirmButton.setBounds(700,550,250,60);

		this.add(cancelButton);//添加按钮
		this.add(confirmButton);

		this.setSize(975,675);//设置窗口大小
		this.setResizable(false);//界面不可缩放
		this.setLocationRelativeTo(null);//运行程序后界面在电脑屏幕正中央显示

		backGround();//调用设置背景的方法
		//this.add(button);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口即结束程序
	}

	//设置背景的方法
	public void backGround(){
		ImageIcon bg = new ImageIcon("./src/image/background.png");
		label = new JLabel(bg);//把背景图片加载到label上

		//设置label大小和在界面的位置
		label.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());

		//把label添加到容器第二层
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		JPanel jp = (JPanel)this.getContentPane();//获取容器的第一层
		jp.setOpaque(false);//把容器的第一层设置为透明
	}
}