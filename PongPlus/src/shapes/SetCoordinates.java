package shapes;

import listeners.PropertyRegisterer;

public interface SetCoordinates extends PropertyRegisterer{
	public void setX(int newX);
	public void setY(int newY);
}
