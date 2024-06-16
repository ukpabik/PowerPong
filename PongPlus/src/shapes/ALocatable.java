package shapes;


public abstract class ALocatable extends ASetCoordinates implements Locatable{
	
	
	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}
	

}
