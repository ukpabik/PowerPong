package shapes;

import java.beans.PropertyChangeEvent;

public class APlayer extends ARectangle implements Player{

	
	protected int points;
	
	
	
	public APlayer() {
		this.points = 0;
	}
	
	
	@Override
	public int getPoints() {
		return points;
	}
	
	@Override
	public void setPoints(int newPoints) {
		int oldPoints = this.points;
		this.points = newPoints;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "points", oldPoints, newPoints));
	}
	
	
	
}
