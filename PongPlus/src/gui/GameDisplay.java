package gui;

import enums.GameState;
import shapes.Circle;
import shapes.Player;

public interface GameDisplay {


	public Player getPlayerTwo();

	public Player getPlayerOne();

	public Circle getPointBall();

	public void setUpGame(int backgroundWidth, int backgroundHeight);

	public int getBotScreen();

	public int getTopScreen();

	public void setPlayerAndBall();

	public int getRightScreen();

	public int getLeftScreen();

	public void scored(Player player);

	public GameState getCurrentState();

	public void setCurrentState(GameState newState);

	public void resizeGame(int backgroundWidth, int backgroundHeight);

	public void updatePlayerPositions();

	public void guiScale(double scaleFactor);

	public void setBall();

}
