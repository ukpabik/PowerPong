package shapes;

import java.beans.PropertyChangeEvent;

public class ACircle extends ABoundedShape implements Circle{

	static final int 
		DEFAULT_RADIUS = 50,
		DEFAULT_X = 50,
		DEFAULT_Y = 50
	;
	protected int radius;
	
	
	public ACircle() {
		this(DEFAULT_RADIUS, DEFAULT_X, DEFAULT_Y);
	}
	public ACircle(int theRadius, int theX, int theY) {
		this.radius = theRadius;
		this.x = theX;
		this.y = theY;
	}
	
	
	
	
	
	
	@Override
	public void setRadius(int newRadius) {
		double oldRadius = this.radius;
		this.radius = newRadius;
		setHeight(newRadius);
		setWidth(newRadius);
	}

	@Override
	public int getRadius() {
		return this.radius;
	}
	
	
	
	

	
}
