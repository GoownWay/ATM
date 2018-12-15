package model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;

public class  MyPasswordField extends JPasswordField {
	private static final long serialVersionUID = 1L;

	public int limit = 1;
	private boolean numberOnly = true;  //只能接受数字

	public MyPasswordField(){
		addKeyListener(new KeyAdapter(){
			@Override 
			public void keyTyped(KeyEvent e){
				if(getPassword().length+1 > limit){
					deleteInputChar(e);
					return;
				}
				if(numberOnly){
					char input = e.getKeyChar();
					if(!Character.isDigit(input)){
						deleteInputChar(e);
					}
				}
			}

			private void deleteInputChar(KeyEvent source){
				source.setKeyChar((char) KeyEvent.VK_CLEAR);
			}
		});	
	}
}