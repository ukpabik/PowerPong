package view;

import factory.PongFactory;
import gui.GameDisplay;

public class ADelegatingPongView implements DelegatingPongView{

	public ADelegatingPongView() {
		APongPainter painter = PongFactory.pongPainterFactoryMethod();
		GameDisplay game = PongFactory.gameDisplayFactoryMethod();
		addViewListeners(painter, game);
	}
	
	
	
	@Override
	public void addViewListeners(APongPainter painter, GameDisplay game) {
		
		
		
		
		
		//MAKE VIEWS FOR PLAYER ONE, TWO, AND THE BALL
		ABallView ballView = new ABallView();
		ARectangleView playerOneView = new PlayerOneView();
		ARectangleView playerTwoView = new PlayerTwoView();
		
		//ADD VIEWS AS PAINTLISTENERS AND PROPERTY CHANGE LISTENERS
		
		painter.addPaintListener(playerOneView);
		painter.addPaintListener(playerTwoView);
		painter.addPaintListener(ballView);
		
		
		
		game.getPlayerOne().addPropertyChangeListener(playerOneView);
		game.getPlayerTwo().addPropertyChangeListener(playerTwoView);
		game.getBall().addPropertyChangeListener(ballView);
		
		
		
	}

}
