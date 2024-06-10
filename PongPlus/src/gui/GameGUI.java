package gui;

import javax.swing.JFrame;

import controller.PongController;
import factory.PongFactory;
import view.APongPainter;
import view.DelegatingPongView;

public class GameGUI extends JFrame{
	public static final int FRAME_SIZE = 800;
	
	GameDisplay game = PongFactory.gameDisplayFactoryMethod();
	APongPainter painter = PongFactory.pongPainterFactoryMethod();
	PongController controller = PongFactory.pongControllerFactoryMethod();
	DelegatingPongView pView = PongFactory.delegatingPainterViewFactoryMethod();
	
	
	public GameGUI() {
		super("Pong Plus");
		this.add(painter);
		this.setSize(FRAME_SIZE, FRAME_SIZE);
		this.setVisible(true);
	}
}
