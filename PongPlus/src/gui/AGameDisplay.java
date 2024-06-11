package gui;


import shapes.ACircle;
import shapes.ARectangle;
import shapes.Circle;
import shapes.Rectangle;

public class AGameDisplay implements GameDisplay{
	
	
	Rectangle playerOne, playerTwo, background;
	Circle ball;
	
	
	
	public AGameDisplay() {
		ball = new ACircle();
		playerOne = new ARectangle();
		playerTwo = new ARectangle();
		background = new ARectangle();
		setUpGame();
	}

	@Override
	public Rectangle getPlayerOne() {
		return playerOne;
	}
	@Override
	public Rectangle getPlayerTwo() {
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
	public void setUpGame() {
		
		
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
		
		
		
	}
	
	@Override
	public void setBackgroundSize(int width, int height) {
		background.setHeight(height);
		background.setWidth(width);
	}
	
	
	
	
}
