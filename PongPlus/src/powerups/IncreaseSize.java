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
		currentObject = null;
	}
	
}
