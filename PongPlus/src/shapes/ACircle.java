package shapes;

import java.awt.Color;

public class ACircle extends ABoundedShape implements Circle{
	
	public static final int DEFAULT_RADIUS = 10;
	static final int 
		DEFAULT_X = 50,
		DEFAULT_Y = 50
	;
	protected int radius;
	protected boolean visible;
	
	
	public ACircle() {
		this(DEFAULT_RADIUS, DEFAULT_X, DEFAULT_Y);
	}
	public ACircle(int theRadius, int theX, int theY) {
		this.width = theRadius;
		this.height = theRadius;
		this.x = theX;
		this.y = theY;
		this.color = Color.white;
	}
	
	
	
	
	
	
	@Override
	public void setRadius(int newRadius) {
		this.radius = newRadius;
		setHeight(newRadius);
		setWidth(newRadius);
	}

	@Override
	public int getRadius() {
		return this.radius;
	}
	
	@Override
	public boolean isVisible() {
		return visible;
	}
	
	@Override
	public void setVisible(boolean visibility) {
		this.visible = visibility;
	}
	
	
	
	

	
}
