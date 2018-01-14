package component;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape  {
	private int radius;

	public Circle(){
		super();
		isCircle = true;
	}

	public int getRadius() {
		return radius;
		
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	@Override
	public void paint(Graphics g){
		g.setColor(Color.DARK_GRAY);
		if(is3d)
			g.fillOval(0, 0, this.getWidth()/2, this.getHeight()/2);
		else
			g.drawOval(0, 0, this.getWidth()/2, this.getHeight()/2);
	}

	@Override
	public void draw(int x, int y, int radius, boolean is3d) {
		this.setBounds(x, y, radius*2, radius*2);
		this.is3d = is3d;
		this.repaint();
		
	}

	@Override
	public void draw(int x, int y, int width, int height, boolean is3D) {
		// TODO Auto-generated method stub
		
	}


	

}
