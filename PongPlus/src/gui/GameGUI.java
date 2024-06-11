package gui;

import java.awt.Color;

import javax.swing.JFrame;

import controller.PongController;
import factory.PongFactory;
import view.APongPainter;
import view.DelegatingPongView;

public class GameGUI extends JFrame{
	public static final int 
		FRAME_X = 800,
		FRAME_Y = 800
	;
	
	GameDisplay game = PongFactory.gameDisplayFactoryMethod();
	APongPainter painter = PongFactory.pongPainterFactoryMethod();
	PongController controller = PongFactory.pongControllerFactoryMethod(painter);
	DelegatingPongView pView = PongFactory.delegatingPainterViewFactoryMethod();
	
	
	public GameGUI() {
		super("Pong Plus");
		
		this.add(painter);
		this.setSize(FRAME_X, FRAME_Y);
		
		this.setVisible(true);
	}
}
