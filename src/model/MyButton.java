package model;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
//按钮模板，带自定义的样式
public class MyButton extends JButton {

	private static final long serialVersionUID = 1L;
	public Graphics2D g2d;
	public MyButton(String text) {
		super(text);//按钮文字
		setOpaque(false);
		setContentAreaFilled(false); //使按钮的样式能显示出来
		setFocusable(false);//焦点从按钮中移除
		setBorderPainted(false);//点击按钮的时候按钮的边界不会显示出来
		//鼠标在按钮上的时候，鼠标变为小手模式
		this.addMouseListener(new MouseAdapter()   {   
			public void mouseEntered(MouseEvent e)   {   
				setCursor(new Cursor(Cursor.HAND_CURSOR));   
			}   

			public void mouseExited(MouseEvent   e)   {   
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));   
			}   
		}); 
	}

	@Override
	public void paintComponent(Graphics g) {
		int width = this.getWidth();//获取按钮的宽度
		int height = this.getHeight();//获取按钮的高度

		g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if(this.getText()=="<html><font size=6>退卡</font><br>&nbsp;&nbsp;&nbsp;Exit</html>")
			g2d.setColor(Color.CYAN);//按钮设置为白色
		else
			g2d.setColor(Color.WHITE);//按钮设置为白色
		//按按钮的大小填充按钮，弧度15
		g2d.fillRoundRect(0,0,width,height,15,15);

		super.paintComponent(g);
	}

	//按钮的删除文本框内容的方法
	public void delect(JTextField text){
		int length = text.getText().length();
		if(length!=0)
			text.setText(text.getText().substring(0,length-1));
	}
}