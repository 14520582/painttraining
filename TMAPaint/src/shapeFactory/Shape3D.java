package shapeFactory;

public class Shape3D extends ShapeFactory{

	@Override
	public ProductCircle createCirle() {
		Circle3D tdc = new Circle3D();
		return tdc;
	}

	@Override
	public ProductRectangle createRectangle() {
		Rectangle3D tdr = new Rectangle3D();
		return tdr;
	}
	
	

}