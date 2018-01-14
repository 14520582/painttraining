package shapeFactory;

import java.awt.Color;
import java.awt.Graphics;


public class Rectangle2D extends ProductRectangle {

	public Rectangle2D(){
		super();
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(0, 0, this.getWidth()/2, this.getHeight()/2);
	}

	@Override
	public void draw(int x, int y, int width, int height) {
		this.setBounds(x, y, width*2, height*2);
		this.repaint();
	}

}