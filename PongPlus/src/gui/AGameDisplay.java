package gui;


import controller.APongController;
import shapes.ACircle;
import shapes.APlayer;
import shapes.Circle;
import shapes.Player;
 
public class AGameDisplay implements GameDisplay{
	
	public static final int 
		PLAYER_TWO_OFFSET = 3, 
		PLAYER_SCREEN_POSITION_DIVISOR = 8
	;
			
			
	GameState state;
	Player playerOne, playerTwo;
	Circle ball;
	
	int topScreen, botScreen, leftScreen, rightScreen;
	
	
	//USED FOR SCALING GUI
	int playerOriginalWidth, playerOriginalHeight, ballOriginalWidth, ballOriginalHeight;
	

	
	
	public AGameDisplay() {
		ball = new ACircle();
		playerOne = new APlayer();
		playerTwo = new APlayer();
		state = GameState.MAIN_MENU;
	}
	
	
	@Override
	public GameState getCurrentState() {
        return state;
    }

	@Override
    public void setCurrentState(GameState newState) {
        state = newState;
        
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
		//STORING VALUES FOR THE SIDES OF THE SCREEN FOR COLLISION
		topScreen = 0;
		botScreen = backgroundHeight;
		leftScreen = 0;
		rightScreen = backgroundWidth;
		
		//SETS THE BALL TO START ON PLAYER ONE
		Points.setLastScoringPlayer(playerOne);
		
		/*
		 * SETTING UP THE VALUES FOR THE PLAYERS AND BALL
		 */
		
		setPlayerAndBall();
		Points.resetPoints(this);
		playerOriginalWidth = playerOne.getWidth();
		playerOriginalHeight = playerOne.getHeight();
		ballOriginalWidth = ball.getWidth();
		ballOriginalHeight = ball.getHeight();

	}
	
	@Override
	public void setPlayerAndBall() {
		
		playerOne.setX(rightScreen / PLAYER_SCREEN_POSITION_DIVISOR);
		
		//Offsets the x values based off of the width of both players
		int widthOffset = playerTwo.getWidth() + playerOne.getWidth();
		int playerTwoX = rightScreen - playerOne.getX() - widthOffset;
		int middleRectY = (botScreen / 2) - playerOne.getHeight();
		
		//Sets both of the players to the middle of the screen
		playerTwo.setX(playerTwoX);
		playerTwo.setY(middleRectY);
		playerOne.setY(middleRectY);
		
		
		/*
		 * SETTING UP THE VALUES FOR THE BALL
		 */
		ball.setX(leftScreen + rightScreen / 2);
		
		ball.setY(topScreen);
	}
	
	
	
	@Override
	public void scored(Player player) {
		Points.addPoints(player);
		Points.setLastScoringPlayer(player);
		setPlayerAndBall();
		APongController.changeMovement();
		ball.setVisible(false);
	}
	
	@Override
	public void resizeGame(int backgroundWidth, int backgroundHeight) {
		botScreen = backgroundHeight;
		rightScreen = backgroundWidth;
		updatePlayerPositions();
	}
	
	
	//CHANGING PLAYERS POSITION AFTER RESIZE
	@Override
	public void updatePlayerPositions() {
		
		playerOne.setX(rightScreen / PLAYER_SCREEN_POSITION_DIVISOR);
		
		int widthOffset = playerTwo.getWidth() + playerOne.getWidth();
		int playerTwoX = rightScreen - playerOne.getX() - widthOffset;
		playerTwo.setX(playerTwoX);
	}
	
	
	//METHOD FOR CHANGING THE GUI SCALE OF THE GAME
	@Override
	public void guiScale(double scaleFactor) {
		playerOne.setWidth((int)(playerOriginalWidth * scaleFactor));
		playerOne.setHeight((int)(playerOriginalHeight * scaleFactor));
		playerTwo.setWidth((int)(playerOriginalWidth * scaleFactor));
		playerTwo.setHeight((int)(playerOriginalHeight * scaleFactor));
		
		ball.setWidth((int)(ballOriginalWidth * scaleFactor));
		ball.setHeight((int)(ballOriginalHeight * scaleFactor));
	}
	
	
}
