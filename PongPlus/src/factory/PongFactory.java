package factory;


import controller.APongController;
import controller.PongController;
import gui.AGameDisplay;
import gui.GameDisplay;
import view.ADelegatingPongView;
import view.APongPainter;
import view.DelegatingPongView;

public class PongFactory {
	static PongController controller;
	static APongPainter painter;
	static GameDisplay game;
	static DelegatingPongView delegate;
	
	
	
	
	public static GameDisplay gameDisplayFactoryMethod() {
		if (game == null) {
			game = new AGameDisplay();
		}
		return game;
	}
	
	
	public static PongController pongControllerFactoryMethod(APongPainter painter) {
		if (controller == null) {
			controller = new APongController(pongPainterFactoryMethod());
		}
		return controller;
	}
	
	public static APongPainter pongPainterFactoryMethod() {
		if (painter == null) {
			painter = new APongPainter();
		}
		return painter;
	}


	public static DelegatingPongView delegatingPainterViewFactoryMethod() {
		if (delegate == null) {
			delegate = new ADelegatingPongView();
		}
		return delegate;
	}
}
