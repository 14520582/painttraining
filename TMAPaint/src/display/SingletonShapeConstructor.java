package display;


import shapeFactory.ShapeConstructor;

public class SingletonShapeConstructor {
	private static SingletonShapeConstructor singletonObj = null;
	private ShapeConstructor builder;
	private SingletonShapeConstructor() {
		builder = new ShapeConstructor();
	}

	public static synchronized SingletonShapeConstructor getInstance() {
		if (singletonObj == null) {
			singletonObj = new SingletonShapeConstructor();
		}
		return singletonObj;
	}

	public ShapeConstructor getBuilder() {
		return builder;
	}
}