package shapeFactory;

public class Shape2D extends ShapeFactory{

	@Override
	public ProductCircle createCirle() {
		Circle2D tdc = new Circle2D();
		return tdc;
	}

	@Override
	public ProductRectangle createRectangle() {
		Rectangle2D tdr = new Rectangle2D();
		return tdr;
	}

}