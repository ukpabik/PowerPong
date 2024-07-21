package powerups;

import shapes.BoundedShape;

public class IncreaseSize extends PowerClass{

	@Override
	public void action(BoundedShape object) {
		// TODO Auto-generated method stub
		changeObjectSize(2);
		
		objectScale(objectSize, object);
	}
	
	
}
