package component;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public abstract class Shape extends JPanel implements IShape{
	protected boolean isCircle;
	protected boolean is3d;
	protected boolean isRemove;
	protected int status;
	
	public Shape() {
		super();
		isCircle = false;
		isRemove = false;
		status = State.Status.NEW;
	}


	public boolean isCircle() {
		return isCircle;
	}

	public void setCircle(boolean isCircle) {
		this.isCircle = isCircle;
	}

	public boolean isIs3D() {
		return is3d;
	}

	public void setIs3D(boolean is3d) {
		this.is3d = is3d;
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
//	protected void paintComponent(Graphics g) {
//    super.paintComponent(g);
//    g.drawRect(0, 0, this.getWidth(), this.getHeight());
//}
}