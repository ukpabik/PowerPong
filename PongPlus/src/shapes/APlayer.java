package shapes;

public class APlayer extends ARectangle implements Player{

	
	int points;
	
	
	
	public APlayer() {
		this.points = 0;
	}
	
	
	@Override
	public int getPoints() {
		return points;
	}
	
	@Override
	public void setPoints(int newPoints) {
		this.points = newPoints;
	}
	
	
	
}
