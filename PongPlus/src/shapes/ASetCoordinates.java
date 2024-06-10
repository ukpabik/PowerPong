package shapes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import listeners.Listener;
import listeners.PropertyListener;

public class ASetCoordinates extends PropertyListener implements SetCoordinates{
	
	protected Listener propertySupport = new PropertyListener();
	protected int x, y;
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * TODO: MAKE THESE SETTERS PROPERTY LISTENERS
	 */
	
	
	@Override
	public void setX(int newX) {
		int oldX = this.x;
		this.x = newX;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "x", oldX, newX));
	}

	@Override
	public void setY(int newY) {
		int oldY = this.y;
		this.y = newY;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "y", oldY, newY));
	}
	
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		propertySupport.addPropertyChangeListener(arg0);
	}

	@Override
	public List<PropertyChangeListener> getPropertyChangeListeners() {
		return propertySupport.getPropertyChangeListeners();
	}

}
