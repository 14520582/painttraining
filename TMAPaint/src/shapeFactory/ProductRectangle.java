package shapeFactory;

import java.awt.Graphics;


public abstract class ProductRectangle extends Shape {
	private static final long serialVersionUID = 1L;
	public ProductRectangle() {
		super();
	}

	public void paint(Graphics g) {};

	@Override
	public void draw(int x, int y, int width, int height) {};
	
	@Override
	public String info(Shape shape){
		StringBuilder rec = new StringBuilder();
		rec.append("  Width: " + shape.getWidth() / 2);
		rec.append(", Height: " + shape.getHeight() / 2);
		rec.append("  -  Pos (" + shape.getX() + "  : " + shape.getY() + ")");
		return rec.toString();	
	}
}
