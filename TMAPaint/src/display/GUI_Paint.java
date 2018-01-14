package display;

import gui.Paint;

public class GUI_Paint {
	private static GUI_Paint singletonObj = null;
	private Paint display;
	private GUI_Paint() {
		display = new Paint();
	}

	public static synchronized GUI_Paint getInstance() {
		if (singletonObj == null) {
			singletonObj = new GUI_Paint();
		}
		return singletonObj;
	}

	public Paint getDisplay() {
		return display;
	}
}
