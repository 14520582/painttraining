package display;
import java.util.ArrayList;
import java.util.List;

import shapeFactory.Shape;

public class ShapeList {
	private static ShapeList singletonObj = null;
	private List<Shape> listShapes;
	private ShapeList(){
		listShapes = new ArrayList<>();
	}
	
	public static synchronized ShapeList getInstance(){
		if(singletonObj == null){
			singletonObj = new ShapeList();
		}
		return singletonObj;
	}
	
	public List<Shape> getListShapes() {
		return listShapes;
	}


//	ABSTRACTFACTORY PF=GUIBUILDER.GETFACTORY("2D");
//	ABSTRACTPRODUCTCIRCLE PRODUCT=PF.CREATECIRLE();
	
}