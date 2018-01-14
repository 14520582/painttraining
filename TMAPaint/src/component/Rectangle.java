package component;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape{

	public Rectangle(){
		super();
		isCircle = false;
	}
	
	@Override
	public void paint(Graphics g){
		g.setColor(Color.RED);
		if(is3d)
			g.fill3DRect(0, 0, this.getWidth()/2, this.getHeight()/2, true);
		else
			g.drawRect(0, 0, this.getWidth()/2, this.getHeight()/2);
	}
	
	@Override
	public void draw(int x, int y, int radius, boolean is3d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(int x, int y, int width, int height, boolean is3d) {
		this.setBounds(x, y, width*2, height*2);
		this.is3d = is3d;
		this.repaint();
		
	}
	
	

}
