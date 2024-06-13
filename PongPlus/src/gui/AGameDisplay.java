package gui;


import shapes.ACircle;
import shapes.APlayer;
import shapes.ARectangle;
import shapes.Circle;
import shapes.Player;
import shapes.Rectangle;

public class AGameDisplay implements GameDisplay{
	
	Rectangle background;
	Player playerOne, playerTwo;
	Circle ball;
	
	public int topScreen, botScreen;
	
	
	
	public AGameDisplay() {
		ball = new ACircle();
		playerOne = new APlayer();
		playerTwo = new APlayer();
		background = new ARectangle();
	}

	@Override
	public Player getPlayerOne() {
		return playerOne;
	}
	@Override
	public Player getPlayerTwo() {
		return playerTwo;
	}
	@Override
	public Circle getPointBall() {
		return ball;
	}
	
	@Override
	public Rectangle getBackground() {
		return background;
	}
	
	@Override
	public int getBotScreen() {
		return botScreen;
	}
	
	@Override
	public int getTopScreen() {
		return topScreen;
	}
	
	@Override
	public void setUpGame(int backgroundWidth, int backgroundHeight) {
		
		
		/*
		 * SETTING UP THE VALUES FOR THE RECTANGLES
		 */
		
		//Offsets the x values based off of the width of both players
		int widthOffset = playerTwo.getWidth() + playerOne.getWidth();
		int playerTwoX = GameGUI.FRAME_X - playerOne.getX() - widthOffset;
		int middleRectY = (GameGUI.FRAME_Y / 2) - playerOne.getHeight();
		int middleCircleY = (GameGUI.FRAME_Y / 2) - playerOne.getHeight() / 2 - ACircle.DEFAULT_RADIUS / 2;
		
		//Sets both of the players to the middle of the screen
		playerTwo.setX(playerTwoX);
		playerTwo.setY(middleRectY);
		playerOne.setY(middleRectY);
		
		
		/*
		 * SETTING UP THE VALUES FOR THE BALL
		 */
		
		ball.setX(playerOne.getX() + playerOne.getWidth() + 1);
		ball.setY(middleCircleY);
		
		//SETTING BACKGROUND TO THE CONTENTPANE SIZE
		background.setHeight(backgroundHeight);
		background.setWidth(backgroundWidth);
		background.setX(0);
		background.setY(0);
		
		
		//STORING VALUES FOR THE TOP AND BOTTOM OF SCREEN FOR COLLISION
		topScreen = 0;
		botScreen = backgroundHeight;
		
	}
	
	
	
	
}
