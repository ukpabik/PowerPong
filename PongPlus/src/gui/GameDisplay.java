package gui;

import shapes.Circle;
import shapes.Player;
import shapes.Rectangle;

public interface GameDisplay {


	public Player getPlayerTwo();

	public Player getPlayerOne();

	public Circle getPointBall();

	public Rectangle getBackground();

	public void setUpGame(int backgroundWidth, int backgroundHeight);

	public int getBotScreen();

	public int getTopScreen();

	public void setPlayerAndBall();

	public int getRightScreen();

	public int getLeftScreen();

	public void scored(Player player);

}
