package shapes;

import powerups.PowerClass;

public interface BoundedShape extends Locatable, ScaleAndMove{
	public int getHeight();
	public int getWidth();
	public void setHeight(int newHeight);
	public void setWidth(int newWidth);
	public void setCurrentPowerUp(PowerClass newPowerUp);
	public PowerClass getCurrentPowerUp();
}
