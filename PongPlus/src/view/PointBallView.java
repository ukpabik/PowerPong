package view;

import java.awt.Graphics2D;

import shapes.Circle;

public class PointBallView extends AShapeView{
	Circle b = game.getPointBall();
	
	@Override
	public void paint(Graphics2D g) {
		
		if (b.isVisible()) {
			this.drawBall(g, b);
		}
	}

	
	
}
