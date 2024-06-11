package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import collision.ACollisionChecker;
import factory.PongFactory;
import gui.GameDisplay;
import view.APongPainter;

public class APongController implements PongController{
	static final int 
		MOVEMENT_LENGTH = 3,
		COLLISION_DELAY = 1000
	;
	int 
		ballXMovement = MOVEMENT_LENGTH,
		ballYMovement = MOVEMENT_LENGTH
	;
	private Timer movementTimer, collisionCheckTimer;
	boolean 
		upPress = false, 
		downPress = false,
		wPress = false,
		sPress = false,
		canCheckCollision = true,
		rightPress = false
	;
	GameDisplay game = PongFactory.gameDisplayFactoryMethod();
	
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
				movePlayerOne();
				movePlayerTwo();
				moveBall();
			}
		});
		movementTimer.start();
		collisionCheckTimer = new Timer(COLLISION_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canCheckCollision = true;
            }
        });
		
		
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
		case KeyEvent.VK_RIGHT:
			rightPress = true;
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
		case KeyEvent.VK_RIGHT:
			rightPress  = false;
			break;
		}
	}
	
	private void movePlayerOne() {
		int oldX = game.getPlayerOne().getX();
		int oldY = game.getPlayerOne().getY();
		if (upPress) {
			
			game.getPlayerOne().move(game.getPlayerOne().getX(), game.getPlayerOne().getY() - MOVEMENT_LENGTH);
		}
		if (downPress) {
			game.getPlayerOne().move(game.getPlayerOne().getX(), game.getPlayerOne().getY() + MOVEMENT_LENGTH);
		}
		if (rightPress) {
			game.getPlayerOne().move(game.getPlayerOne().getX() + MOVEMENT_LENGTH, game.getPlayerOne().getY());
		}
		if (ACollisionChecker.intersects(game.getPlayerOne(), game.getPointBall())) {
			game.getPlayerOne().move(oldX, oldY);
		}
		
		
		
	}
	private void movePlayerTwo() {
		int oldX = game.getPlayerTwo().getX();
		int oldY = game.getPlayerTwo().getY();
		if (wPress) {
			game.getPlayerTwo().move(game.getPlayerTwo().getX(), game.getPlayerTwo().getY() - MOVEMENT_LENGTH);
		}
		if (sPress) {
			game.getPlayerTwo().move(game.getPlayerTwo().getX(), game.getPlayerTwo().getY() + MOVEMENT_LENGTH);
		}
		if (ACollisionChecker.intersects(game.getPlayerTwo(), game.getPointBall())) {
			game.getPlayerTwo().move(oldX, oldY);
		}
		
		
		
	}
	
	private void moveBall() {
		int oldX = game.getPointBall().getX();
        int oldY = game.getPointBall().getY();
        
        game.getPointBall().move(oldX + ballXMovement, oldY);

        if (canCheckCollision) {
            if (ACollisionChecker.intersects(game.getPointBall(), game.getPlayerTwo())) {
            	ballXMovement = -ballXMovement;
                canCheckCollision = false;
                collisionCheckTimer.restart(); // Restart the timer for the next collision check
            } else if (ACollisionChecker.intersects(game.getPointBall(), game.getPlayerOne())) {
            	ballXMovement = -ballXMovement;
                canCheckCollision = false;
                collisionCheckTimer.restart(); // Restart the timer for the next collision check
            }
        }
		
	}

}
