package gui;

import shapes.Circle;
import shapes.Rectangle;

public interface GameDisplay {


	public Rectangle getPlayerTwo();

	public Rectangle getPlayerOne();

	public Circle getPointBall();

	public Rectangle getBackground();

	public void setUpGame(int backgroundWidth, int backgroundHeight);

	public int getBotScreen();

}
