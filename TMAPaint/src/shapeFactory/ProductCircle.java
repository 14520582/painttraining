package shapeFactory;
import java.awt.Graphics;

public abstract class ProductCircle extends Shape {
	public ProductCircle() {
		super();
	}

	public void paint(Graphics g) {
	};

	@Override
	public void draw(int x, int y, int radius) {
	}

	@Override
	public String info(Shape shape) {
		StringBuilder infor = new StringBuilder();
		infor.append("  Radius: " + shape.getWidth() / 2);
		infor.append("  -  Pos (" + shape.getX() + "  : " + shape.getY() + ")");
		return infor.toString();
	}
}
