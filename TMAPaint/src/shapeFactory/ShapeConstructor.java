package shapeFactory;

public class ShapeConstructor {
	private static ShapeFactory pf = null;

	public ShapeFactory getFactory(String choice) {
		if (choice.equals("3D")) {
			pf = new Shape3D();
		} else if (choice.equals("2D")) {
			pf = new Shape2D();
		}
		return pf;
	}
}
