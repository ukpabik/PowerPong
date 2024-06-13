package shapes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ABoundedShape extends ALocatable implements BoundedShape{
	
	protected int width, height;
	
	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public void setHeight(int newHeight) {
		int oldHeight = this.height;
		this.height = newHeight;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "height", oldHeight, newHeight));
		
	}

	@Override
	public void setWidth(int newWidth) {
		int oldWidth = this.width;
		this.width = newWidth;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "width", oldWidth, newWidth));
	}
	
	
	@Override
	public void scale(double scaleFactor) {
		int oldWidth = this.getWidth();
        int oldHeight = this.getHeight();
        int oldX = this.getX();
        int oldY = this.getY();
        
        this.setWidth((int) (this.getWidth() * scaleFactor));
        this.setHeight((int) (this.getHeight() * scaleFactor));
        this.setX((int) (this.getX() * scaleFactor));
        this.setY((int) (this.getY() * scaleFactor));

        propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "width", oldWidth, this.width));
        propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "height", oldHeight, this.height));
        propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "x", oldX, this.x));
        propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "y", oldY, this.y));
		
	}
	@Override
	public void move(int newX, int newY) {
		int oldX = this.x;
        int oldY = this.y;
        this.x = newX;
        this.y = newY;
        propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "x", oldX, this.x));
        propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "y", oldY, this.y));
	}
	
	
	public static void addPropertyChangeListener(BoundedShape shape, PropertyChangeListener listener) {
	    shape.addPropertyChangeListener(listener);
	}
	

}
