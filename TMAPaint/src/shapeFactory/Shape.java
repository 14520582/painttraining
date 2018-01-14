package shapeFactory;

import javax.swing.JPanel;
import component.State;

public abstract class Shape extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected boolean isRemove;
	protected int status;
	
	public Shape() {
		super();
		setRemove(false);
		setStatus(State.Status.NEW);
	}
	
	public boolean isRemove() {
		return isRemove;
	}

	public void setRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void draw(int x, int y, int radius){};
	public void draw(int x, int y, int width, int height){};
	public String info(Shape shape) {
		return null;
	}
}

