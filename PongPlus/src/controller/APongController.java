package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import factory.PongFactory;
import gui.GameDisplay;
import view.APongPainter;

public class APongController implements PongController{
	public static final int MOVEMENT_LENGTH = 5;
	GameDisplay game = PongFactory.gameDisplayFactoryMethod();
	
	private Timer movementTimer;
	boolean 
		upPress = false, 
		downPress = false,
		wPress = false,
		sPress = false
		
	;
	
	public APongController(APongPainter pongPainter) {
		pongPainter.addKeyListener(this);
		pongPainter.addMouseListener(this);
		
		/*
		 * ALLOWS FOR SMOOTHER MOVEMENT IN HIGHER FRAMES
		 * THIS TIMER CHECKS FOR MOVEMENT EVERY MILLISECOND
		 */
		movementTimer = new Timer(1, new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				movePlayer();
			}
		});
		movementTimer.start();
		
		
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		System.out.println(keyCode);
		switch(keyCode) {
		case KeyEvent.VK_UP: 
			upPress = true;
			break;
		
		case KeyEvent.VK_DOWN: 
			downPress = true;
			break;
		case KeyEvent.VK_W:
			wPress = true;
			break;
		case KeyEvent.VK_S:
			sPress = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		System.out.println(keyCode);
		switch(keyCode) {
		case KeyEvent.VK_UP: 
			upPress = false;
			break;
		
		case KeyEvent.VK_DOWN: 
			downPress = false;
			break;
		case KeyEvent.VK_W:
			wPress = false;
			break;
		case KeyEvent.VK_S:
			sPress = false;
			break;
		}
	}
	
	private void movePlayer() {
		if (upPress) {
			game.getPlayerOne().move(game.getPlayerOne().getX(), game.getPlayerOne().getY() - MOVEMENT_LENGTH);
		}
		if (downPress) {
			game.getPlayerOne().move(game.getPlayerOne().getX(), game.getPlayerOne().getY() + MOVEMENT_LENGTH);
		}
		if (wPress) {
			game.getPlayerTwo().move(game.getPlayerTwo().getX(), game.getPlayerTwo().getY() - MOVEMENT_LENGTH);
		}
		if (sPress) {
			game.getPlayerTwo().move(game.getPlayerTwo().getX(), game.getPlayerTwo().getY() + MOVEMENT_LENGTH);
		}
	}

}
