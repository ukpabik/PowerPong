package factory;


import controller.APongController;
import controller.PongController;
import fonts.FontCreator;
import gui.AGameDisplay;
import gui.GameDisplay;
import view.ADelegatingPongView;
import view.APongPainter;
import view.DelegatingPongView;

public abstract class PongFactory {
	static PongController controller;
	static APongPainter painter;
	static GameDisplay game;
	static DelegatingPongView delegate;
	static FontCreator fontManager;
	
	
	
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
	
	public static FontCreator fontManagerFactoryMethod() {
		if (fontManager == null) {
			fontManager = new FontCreator();
		}
		return fontManager;
	}
}
