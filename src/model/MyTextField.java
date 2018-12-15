package model;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;
//自定义的文本框，带文本框监听方法
public class MyTextField extends JTextField{

	private static final long serialVersionUID = 1L;

	public void textListener(MyTextField text,String s1){

		text.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				text.addFocusListener(new FocusListener() {
					@Override
					public void focusLost(FocusEvent e) {
						//如果没有输入，依然显示提示内容
						if(text.getText().equals("")){
							text.setText(s1);
							text.setForeground(Color.lightGray);
						}
					}
					@Override
					public void focusGained(FocusEvent e) {
						//鼠标在文本框时，提示内容失去
						if(text.getText().equals(s1)){
							text.setText("");
							text.setForeground(Color.BLACK);
						}
					}
				});
				text.setFocusable(true);
				text.requestFocus();//请求获得焦点，即鼠标
			}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
	}
}