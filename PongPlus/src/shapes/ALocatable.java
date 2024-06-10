package shapes;

import java.beans.PropertyChangeListener;

public class ALocatable extends ASetCoordinates implements Locatable{
	
	
	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}
	

}
