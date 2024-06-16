package view;

import factory.PongFactory;
import gui.GameDisplay;

public class ADelegatingPongView implements DelegatingPongView{

	public static PointBallView ballView = new PointBallView();
	public static PlayerOneView playerOneView = new PlayerOneView();
	public static PlayerTwoView playerTwoView = new PlayerTwoView();
	
	
	public ADelegatingPongView() {
		APongPainter painter = PongFactory.pongPainterFactoryMethod();
		GameDisplay game = PongFactory.gameDisplayFactoryMethod();
		addViewListeners(painter, game);
	}
	
	
	
	@Override
	public void addViewListeners(APongPainter painter, GameDisplay game) {
		
		
		
		
		
		//MAKE VIEWS FOR PLAYER ONE, TWO, AND THE BALL
		PointBallView ballView = new PointBallView();
		PlayerOneView playerOneView = new PlayerOneView();
		PlayerTwoView playerTwoView = new PlayerTwoView();
		MainMenuView mainView = new MainMenuView();
		
		//ADD VIEWS AS PAINTLISTENERS AND PROPERTY CHANGE LISTENERS
		painter.addPaintListener(playerOneView);
		painter.addPaintListener(playerTwoView);
		painter.addPaintListener(ballView);
		painter.addPaintListener(mainView);
		
		
		
		game.getPlayerOne().addPropertyChangeListener(playerOneView);
		game.getPlayerTwo().addPropertyChangeListener(playerTwoView);
		game.getPointBall().addPropertyChangeListener(ballView);
		
		
		
	}

}
