package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import collision.ACollisionChecker;
import factory.PongFactory;
import gui.GameDisplay;
import gui.Points;
import shapes.BoundedShape;
import shapes.Player;
import view.APongPainter;

public class APongController implements PongController{
	static final int 
		MOVEMENT_LENGTH = 8,
		COLLISION_DELAY = 15,
		MAX_BALL_MOVEMENT = 7,
		MIN_BALL_MOVEMENT = 3
	;
	int 
		ballXMovement = MAX_BALL_MOVEMENT,
		ballYMovement = MAX_BALL_MOVEMENT
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
				moveBall(game.getPointBall());
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
	public void movePlayer(Player player) {
		
		/*
		 * STORE OLD X AND Y VALUES TO KEEP OBJECTS FROM GOING INSIDE
		 * EACH OTHER.
		 */
		int oldX = player.getX();
		int oldY = player.getY();
		
		
		if (player.equals(game.getPlayerOne())) {
			movePlayerAndBall(player, game.getPointBall(), upPress, downPress);
		}
		else if (player.equals(game.getPlayerTwo())) {
			movePlayerAndBall(player, game.getPointBall(), wPress, sPress);
		}
		
		if (ACollisionChecker.intersects(player, game.getPointBall())) {
			player.move(oldX, oldY);
		}
	}
	
	
	@Override
	public void moveBall(BoundedShape ball) {
		
		//LOGIC FOR MOVING BALL
		
		int oldX = game.getPointBall().getX();
        int oldY = game.getPointBall().getY();
        
        
        
        //ONLY MOVE BALL IF THE GAME HAS STARTED
        
        if (spacePress) {
        	ball.move(oldX + ballXMovement, oldY + ballYMovement);

            
            //ONLY CHANGE MOVEMENT IF YOU ARE ABLE TO CHECK COLLISION
            if (canCheckCollision) {
                if (ACollisionChecker.intersects(ball, game.getPlayerTwo())) {
                	changeMovement();
                	changeXSign(-1);
                	
                	
                	
                    //CHECKS IF BALL HITS THE TOP OR BOTTOM OF THE PLAYER
                    if (ball.getY() >= game.getPlayerTwo().getY() + game.getPlayerTwo().getHeight() / 2) {
                    	negateYMovement();
                    }
                } 
                else if (ACollisionChecker.intersects(ball, game.getPlayerOne())) {
                	changeMovement();
                	changeXSign(1);
                	
                	//CHECKS IF BALL HITS THE TOP OR BOTTOM OF THE PLAYER
                    if (ball.getY() >= game.getPlayerOne().getY() + game.getPlayerOne().getHeight() / 2) {
                    	negateYMovement();
                    }
                }
                
                //FOR TOP AND BOTTOM COLLISION 
                else if (ball.getY() <= game.getTopScreen()) {
                	negateYMovement();
                }
                else if (ball.getY() >= game.getBotScreen()) {
                	negateYMovement();
                }
            }
            
            
            //IF BALL GOES ONTO PLAYER TWO'S SIDE
            if (ball.getX() >= game.getRightScreen()) {
            	game.scored(game.getPlayerOne());
            	spacePress = false;
            	
    		}
    		
            //IF BALL GOES ONTO PLAYER ONE'S SIDE
    		else if (ball.getX() <= game.getLeftScreen()) {
    			game.scored(game.getPlayerTwo());
    			spacePress = false;
    		}
        }
		
	}
	
	
	
	/*
	 * METHOD THAT HELPS WITH MOVING BALL AND PLAYER TOGETHER
	 */
	
	@Override
	public void movePlayerAndBall(Player player, BoundedShape ball, boolean moveUp, boolean moveDown) {
		
	    if (moveUp) {
	        player.move(player.getX(), player.getY() - MOVEMENT_LENGTH);
	        if (!spacePress && player.equals(Points.getLastScoringPlayer())) {
	            ball.move(ball.getX(), ball.getY() - MOVEMENT_LENGTH);
	        }
	    }
	    if (moveDown) {
	        player.move(player.getX(), player.getY() + MOVEMENT_LENGTH);
	        if (!spacePress && player.equals(Points.getLastScoringPlayer())) {
	        	ball.move(ball.getX(), ball.getY() + MOVEMENT_LENGTH);
	        }
	    }
	}
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * HELPER METHODS FOR STARTING COLLISION TIMER WHEN BALL COLLIDES
	 */
	
	private void changeXSign(int sign) {
		if (sign == 1) {
			ballXMovement = Math.abs(ballXMovement);
		}
		else if (sign == -1) {
			ballXMovement = -ballXMovement;
		}
        canCheckCollision = false;
        collisionCheckTimer.restart();
	}
	private void negateYMovement() {
		ballYMovement  = -ballYMovement;
    	canCheckCollision = false;
        collisionCheckTimer.restart();
	}
	
	/*
	 * THIS METHOD PROVIDES RANDOM CHANGES IN X AND Y SPEED 
	 * FOR MORE VARIABILITY IN GAMEPLAY
	 */
	
	private void changeMovement() {
		int newMovement = (int) (Math.random() * (MAX_BALL_MOVEMENT - MIN_BALL_MOVEMENT) + MIN_BALL_MOVEMENT);
		ballXMovement = newMovement;
		ballYMovement = newMovement;
		System.out.println(ballYMovement);
	}

}
