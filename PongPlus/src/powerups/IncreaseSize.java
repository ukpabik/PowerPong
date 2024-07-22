package powerups;

import shapes.BoundedShape;

public class IncreaseSize extends PowerClass{

	@Override
	public void action(BoundedShape object) {
		super.action(object);
		changeObjectSize(2);
		
		objectScale(objectSize, object);
		
		
	}
	
	public void resetAction(BoundedShape object) {
		
		changeObjectSize(-2);
		objectScale(objectSize, object);
		if (object != display.getPointBall()) {
			currentObject = null;
		}
		else {
			currentBall = null;
		}
		super.resetAction(object);
	}
	
}
