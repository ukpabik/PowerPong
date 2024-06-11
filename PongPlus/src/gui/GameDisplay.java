package gui;

import shapes.Circle;
import shapes.Rectangle;

public interface GameDisplay {

	public void setUpGame();

	public Rectangle getPlayerTwo();

	public Rectangle getPlayerOne();

	public Circle getPointBall();

	public Rectangle getBackground();

	public void setBackgroundSize(int width, int height);

}
