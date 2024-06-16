package gui;


import controller.APongController;
import shapes.ACircle;
import shapes.APlayer;
import shapes.Circle;
import shapes.Player;
 
public class AGameDisplay implements GameDisplay{
	
	public static final int PLAYER_TWO_OFFSET = 3;
			
			
	GameState state;
	Player playerOne, playerTwo;
	Circle ball;
	
	public int topScreen, botScreen, leftScreen, rightScreen;
	

	
	
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
		

	}
	
	@Override
	public void setPlayerAndBall() {
		//Offsets the x values based off of the width of both players
		int widthOffset = playerTwo.getWidth() + playerOne.getWidth();
		int playerTwoX = GameGUI.FRAME_X - playerOne.getX() - widthOffset;
		int middleRectY = (GameGUI.FRAME_Y / 2) - playerOne.getHeight();
		
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
	
	
	
	
	
	
	
}
