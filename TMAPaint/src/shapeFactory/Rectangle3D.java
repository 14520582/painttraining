package shapeFactory;

import java.awt.Color;
import java.awt.Graphics;


public class Rectangle3D extends ProductRectangle {

	public Rectangle3D(){
		super();
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fill3DRect(0, 0, this.getWidth()/2, this.getHeight()/2, true);
	}

	@Override
	public void draw(int x, int y, int width, int height) {
		this.setBounds(x, y, width*2, height*2);
		this.repaint();
	}

}