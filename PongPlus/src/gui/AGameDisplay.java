package gui;


import shapes.ACircle;
import shapes.APlayer;
import shapes.ARectangle;
import shapes.Circle;
import shapes.Player;
import shapes.Rectangle;

public class AGameDisplay implements GameDisplay{
	static final int PLAYER_TWO_OFFSET = 2;
	
	
	Rectangle background;
	Player playerOne, playerTwo;
	Circle ball;
	
	public int topScreen, botScreen, leftScreen, rightScreen;
	
	
	
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
	public int getRightScreen() {
		return rightScreen;
	}
	
	@Override
	public int getLeftScreen() {
		return leftScreen;
	}
	
	@Override
	public void setUpGame(int backgroundWidth, int backgroundHeight) {
		
		
		//SETS THE BALL TO START ON PLAYER ONE
		Points.setLastScoringPlayer(playerOne);
		
		/*
		 * SETTING UP THE VALUES FOR THE RECTANGLES
		 */
		
		setPlayerAndBall();
		
		//SETTING BACKGROUND TO THE CONTENTPANE SIZE
		background.setHeight(backgroundHeight);
		background.setWidth(backgroundWidth);
		background.setX(0);
		background.setY(0);
		
		
		//STORING VALUES FOR THE SIDES OF THE SCREEN FOR COLLISION
		topScreen = 0;
		botScreen = backgroundHeight;
		leftScreen = 0;
		rightScreen = backgroundWidth;
		
	}
	
	@Override
	public void setPlayerAndBall() {
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
		
		if (Points.getLastScoringPlayer().equals(playerOne)) {
			ball.setX(Points.getLastScoringPlayer().getX() + Points.getLastScoringPlayer().getWidth());
		}
		else {
			ball.setX(Points.getLastScoringPlayer().getX() - Points.getLastScoringPlayer().getWidth() - PLAYER_TWO_OFFSET);
		}
		
		ball.setY(middleCircleY);
	}
	
	
	
	@Override
	public void scored(Player player) {
		Points.addPoints(player);
		Points.setLastScoringPlayer(player);
		setPlayerAndBall();
	}
	
	
	
	
	
	
	
}
