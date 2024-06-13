package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import collision.ACollisionChecker;
import factory.PongFactory;
import gui.GameDisplay;
import shapes.BoundedShape;
import view.APongPainter;

public class APongController implements PongController{
	static final int 
		MOVEMENT_LENGTH = 8,
		COLLISION_DELAY = 15
	;
	int 
		ballXMovement = 7,
		ballYMovement = 7
	;
	private Timer movementTimer, collisionCheckTimer;
	boolean 
		upPress = false, 
		downPress = false,
		wPress = false,
		sPress = false,
		canCheckCollision = true,
		spacePress = false
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
				movePlayer(game.getPlayerOne());
				movePlayer(game.getPlayerTwo());
				moveBall();
			}
		});
		movementTimer.start();
		
		
		//ALLOWS TIME IN BETWEEN COLLISION CHECKS TO GIVE BOUNCE EFFECT
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
			
		case KeyEvent.VK_SPACE:
			spacePress = true;
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
	
	@Override
	public void movePlayer(BoundedShape player) {
		
		/*
		 * STORE OLD X AND Y VALUES TO KEEP OBJECTS FROM GOING INSIDE
		 * EACH OTHER.
		 */
		int oldX = player.getX();
		int oldY = player.getY();
		
		
		if (player.equals(game.getPlayerOne())) {
			if (upPress) {
				
				player.move(player.getX(), player.getY() - MOVEMENT_LENGTH);
			}
			if (downPress) {
				player.move(player.getX(), player.getY() + MOVEMENT_LENGTH);
			}
		}
		else if (player.equals(game.getPlayerTwo())) {
			if (wPress) {
				
				player.move(player.getX(), player.getY() - MOVEMENT_LENGTH);
			}
			if (sPress) {
				player.move(player.getX(), player.getY() + MOVEMENT_LENGTH);
			}	
		}
		
		
		if (ACollisionChecker.intersects(player, game.getPointBall())) {
			player.move(oldX, oldY);
		}
	}
	
	
	@Override
	public void moveBall() {
		
		//LOGIC FOR MOVING BALL
		
		int oldX = game.getPointBall().getX();
        int oldY = game.getPointBall().getY();
        
        
        
        //ONLY MOVE BALL IF THE GAME HAS STARTED
        
        if (spacePress) {
        	game.getPointBall().move(oldX + ballXMovement, oldY + ballYMovement);

            
            //ONLY CHANGE MOVEMENT IF YOU ARE ABLE TO CHECK COLLISION
            if (canCheckCollision) {
                if (ACollisionChecker.intersects(game.getPointBall(), game.getPlayerTwo())) {
                	negateXMovement();
                    
                    //CHECKS IF BALL HITS THE TOP OR BOTTOM OF THE PLAYER
                    if (game.getPointBall().getY() >= game.getPlayerTwo().getY() + game.getPlayerTwo().getHeight() / 2) {
                    	negateYMovement();
                    }
                } 
                else if (ACollisionChecker.intersects(game.getPointBall(), game.getPlayerOne())) {
                	negateXMovement();
                	
                	
                	//CHECKS IF BALL HITS THE TOP OR BOTTOM OF THE PLAYER
                    if (game.getPointBall().getY() >= game.getPlayerOne().getY() + game.getPlayerOne().getHeight() / 2) {
                    	negateYMovement();
                    }
                }
                
                //FOR TOP AND BOTTOM COLLISION 
                else if (game.getPointBall().getY() <= game.getTopScreen()) {
                	negateYMovement();
                }
                else if (game.getPointBall().getY() >= game.getBotScreen()) {
                	negateYMovement();
                }
            }
        }
		
	}
	
	
	/*
	 * HELPER METHODS FOR STARTING COLLISION TIMER WHEN BALL COLLIDES
	 */
	
	private void negateXMovement() {
		ballXMovement = -ballXMovement;
        canCheckCollision = false;
        collisionCheckTimer.restart();
	}
	private void negateYMovement() {
		ballYMovement  = -ballYMovement;
    	canCheckCollision = false;
        collisionCheckTimer.restart();
	}

}
