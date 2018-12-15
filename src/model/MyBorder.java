package model;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.AbstractBorder;
//自定义的组件边界模板
public class MyBorder extends AbstractBorder{

	private static final long serialVersionUID = 1L;
	private static final Color BACKGROUND01 = Color.CYAN;//边界颜色

	public void paintBorder(Component c,Graphics g,int x,int y, int width,int height){
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(BACKGROUND01);
		g2d.drawRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
	}

	public Insets getBorderInsets(Component c){
		return new Insets(0,10,0,0);
	}
}