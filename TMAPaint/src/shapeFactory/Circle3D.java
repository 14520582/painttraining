package shapeFactory;

import java.awt.Color;
import java.awt.Graphics;

public class Circle3D extends ProductCircle {

	public Circle3D(){
		super();
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillOval(0, 0, this.getWidth()/2, this.getHeight()/2);
	}

	@Override
	public void draw(int x, int y, int radius) {
		this.setBounds(x, y, radius*2, radius*2);
		this.repaint();

	}

}