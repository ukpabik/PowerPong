package gui;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import controller.PongController;
import factory.PongFactory;
import view.APongPainter;
import view.AShapeView;
import view.DelegatingPongView;

@SuppressWarnings("serial")
public class GameGUI extends JFrame{
	public static final int 
		FRAME_X = 1000,
		FRAME_Y = 1000
	;
	
	GameDisplay game = PongFactory.gameDisplayFactoryMethod();
	APongPainter painter = PongFactory.pongPainterFactoryMethod();
	PongController controller = PongFactory.pongControllerFactoryMethod(painter);
	DelegatingPongView pView = PongFactory.delegatingPainterViewFactoryMethod();
	
	
	
	public GameGUI() {
		super("Pong Plus");
		this.setSize(FRAME_X, FRAME_Y);
		this.add(painter);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setVisible(true);
		this.getContentPane().setBackground(Color.BLACK);
		
		
		
		/*
		 * Creates the GUI before calculating the pane width and height
		 * This ensures that these values are calculated correctly.
		 */
		SwingUtilities.invokeLater(() -> {
            int contentPaneWidth = this.getContentPane().getWidth();
            int contentPaneHeight = this.getContentPane().getHeight();
            AShapeView.setContentPaneDimensions(contentPaneWidth, contentPaneHeight);
            this.game.setUpGame(contentPaneWidth, contentPaneHeight);
        });
		
		
		/**
		 * USING A COMPONENT LISTENER FOR THE GUI TO 
		 * ALLOW FOR RESIZING OF THE SCREEN
		 */
		this.addComponentListener(new ComponentListener() {
		@Override
		public void componentResized(ComponentEvent e) {
			int contentPaneWidth = getContentPane().getWidth();
			int contentPaneHeight = getContentPane().getHeight();
			AShapeView.setContentPaneDimensions(contentPaneWidth, contentPaneHeight);
			game.resizeGame(contentPaneWidth, contentPaneHeight);
		}

		@Override
		public void componentMoved(ComponentEvent e) {}

		@Override
		public void componentShown(ComponentEvent e) {}

		@Override
		public void componentHidden(ComponentEvent e) {}
		});
	}
}
