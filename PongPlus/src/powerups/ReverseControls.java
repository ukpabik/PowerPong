package powerups;


import java.awt.Color;

import shapes.BoundedShape;


public class ReverseControls extends PowerClass{
	
	public static Color reverseColor = new Color(160, 32, 240);
	
	@Override
	public void action(BoundedShape object) {
		super.action(object);
		object.setColor(reverseColor);
		if (object != display.getPointBall()) {
			colorChangingTimer.start();
			object.setChangeControls(true);
		}
		
	}
	
	public void resetAction(BoundedShape object) {
		
		if (object != display.getPointBall()) {
			colorChangingTimer.stop();
			object.setChangeControls(false);
			currentObject = null;
		}
		else {
			currentBall = null;
		}
		object.setColor(Color.white);
		super.resetAction(object);
	}
}
