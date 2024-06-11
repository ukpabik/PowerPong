package view;

import java.awt.Graphics2D;

public class PointBallView extends AShapeView{
	
	
	@Override
	public void paint(Graphics2D g) {
		this.drawBall(g, game.getPointBall());
	}
	
	
}
