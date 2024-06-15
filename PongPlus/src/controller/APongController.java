package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.Timer;

import collision.ACollisionChecker;
import factory.PongFactory;
import gui.GameDisplay;
import shapes.BoundedShape;
import shapes.Player;
import view.APongPainter;

public class APongController implements PongController{
	static final int 
		MOVEMENT_LENGTH = 5,
		COLLISION_DELAY = 15,
		MAX_X_BALL_MOVEMENT = 5,
		MIN_X_BALL_MOVEMENT = 2,
		MAX_Y_BALL_MOVEMENT = 5,
		MIN_Y_BALL_MOVEMENT = 2,
		UPDATE_INTERVAL = 4,
		THREAD_DIVISOR = 1000000
	;
	
	
	
	//BALL MOVEMENT
	static int 
		ballXMovement = MAX_X_BALL_MOVEMENT,
		ballYMovement = MAX_Y_BALL_MOVEMENT,
		randomizeXBound = MIN_Y_BALL_MOVEMENT,
		randomizeYBound = MAX_Y_BALL_MOVEMENT + 1
	;
	
	//INPUT VARIABLES
	boolean 
		upPress = false, 
		downPress = false,
		wPress = false,
		sPress = false,
		canCheckCollision = true,
		running = false
	;
	
	 
	//TIMER AND GAME STATE VARIABLES
	Thread gameThread;
	private Timer collisionCheckTimer;
	
	
	
	
	
	private int justScoredCounter, ballCounter;
	private boolean justScored, gameStarted = false;
	
	
	public Random randomizer = new Random();
	APongPainter painter = PongFactory.pongPainterFactoryMethod();
	GameDisplay game = PongFactory.gameDisplayFactoryMethod();

	
	
	public APongController(APongPainter pongPainter) {
		pongPainter.addKeyListener(this);
		pongPainter.addMouseListener(this);
		
		
		//ALLOWS TIME IN BETWEEN COLLISION CHECKS TO GIVE BOUNCE EFFECT
		collisionCheckTimer = new Timer(COLLISION_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canCheckCollision = true;
            }
        });
		
		
	}
	
	@Override
	public void startGame() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		while (running) {
			long startTime = System.nanoTime();
			
			
			
			/*
			 * DELAY THE BALL FROM MOVING UNTIL THE GAME HAS BEGUN
			 * OR A SECOND AFTER THE PLAYER SCORES
			 */
			if (!gameStarted) {
				ballCounter++;
				if (ballCounter >= (1000 / UPDATE_INTERVAL)) {
					ballCounter = 0;
					gameStarted = true;
					randomizeMovement();
				}
			}
			else if (justScored) {
				justScoredCounter++;
				if (justScoredCounter >= (1000 / UPDATE_INTERVAL)) {
					justScoredCounter = 0;
					justScored = false;
					randomizeMovement();
				}
			}
			
			movePlayer(game.getPlayerOne());
            movePlayer(game.getPlayerTwo());
            moveBall(game.getPointBall());
            
            painter.repaint();
            
            long elapsedTime = System.nanoTime() - startTime;
            long sleepTime = UPDATE_INTERVAL - (elapsedTime / 1000000);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
			
		}
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
		
		if (player.equals(game.getPlayerOne())) {
			moveSpecificPlayer(player, upPress, downPress);
		}
		else if (player.equals(game.getPlayerTwo())) {
			moveSpecificPlayer(player, wPress, sPress);
		}
		
		
		
	}
	
	
	@Override
	public void moveBall(BoundedShape ball) {
		
		//LOGIC FOR MOVING BALL
		
		int oldX = game.getPointBall().getX();
        int oldY = game.getPointBall().getY();
        
        
        
        //ONLY MOVE BALL IF THE GAME HAS STARTED
        
        if (running && gameStarted && !justScored) {
        	ball.move(oldX + ballXMovement, oldY + ballYMovement);

            
            //ONLY CHANGE MOVEMENT IF YOU ARE ABLE TO CHECK COLLISION
            if (canCheckCollision) {
                if (ACollisionChecker.intersects(ball, game.getPlayerTwo())) {
                	changeMovement();
                	changeXSign(-1);
                	
                	
                	
                    //CHECKS IF BALL HITS THE TOP OR BOTTOM OF THE PLAYER
                    if (ball.getY() >= game.getPlayerTwo().getY() + game.getPlayerTwo().getHeight() / 2) {
                    	changeYSign(-1);
                    }
                    else {
                    	changeYSign(1);
                    }
                } 
                else if (ACollisionChecker.intersects(ball, game.getPlayerOne())) {
                	changeMovement();
                	changeXSign(1);
                	
                	//CHECKS IF BALL HITS THE TOP OR BOTTOM OF THE PLAYER
                    if (ball.getY() >= game.getPlayerOne().getY() + game.getPlayerOne().getHeight() / 2) {
                    	changeYSign(-1);
                    }
                    else {
                    	changeYSign(-1);
                    }
                }
                
                //FOR TOP AND BOTTOM COLLISION 
                else if (ball.getY() <= game.getTopScreen()) {
                	changeYSign(1);
                }
                else if (ball.getY() + ball.getHeight() >= game.getBotScreen()) {
                	changeYSign(-1);
                }
            }
            
            
            //IF BALL GOES ONTO PLAYER TWO'S SIDE
            if (ball.getX() >= game.getRightScreen()) {
            	game.scored(game.getPlayerOne());
            	justScored = true;
            	
    		}
    		
            //IF BALL GOES ONTO PLAYER ONE'S SIDE
    		else if (ball.getX() <= game.getLeftScreen()) {
    			game.scored(game.getPlayerTwo());
    			justScored = true;
    		}
        }
		
	}
	
	
	
	/*
	 * METHOD THAT COMBINES MOVEMENT OF PLAYER ONE AND TWO INTO ONE METHOD
	 */
	
	@Override
	public void moveSpecificPlayer(Player player, boolean moveUp, boolean moveDown) {
		int oldX = player.getX();
		int oldY = player.getY();
		
	    if (moveUp) {
	        player.move(player.getX(), player.getY() - MOVEMENT_LENGTH);
	    }
	    if (moveDown) {
	        player.move(player.getX(), player.getY() + MOVEMENT_LENGTH);
	    }
	    
	    //HANDLES IF PLAYER REACHES TOP OR BOTTOM OF SCREEN
	    if (player.getY() <= game.getTopScreen()) {
			player.move(oldX, oldY);
		}
	    if (player.getY() + player.getHeight() >= game.getBotScreen()) {
	    	player.move(oldX, oldY);
	    }
	    
	    if (ACollisionChecker.intersects(player, game.getPointBall())) {
			player.move(oldX, oldY);
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
	private void changeYSign(int sign) {
		if (sign == 1) {
			ballYMovement = Math.abs(ballYMovement);
		}
		else if (sign == -1) {
			ballYMovement = -ballYMovement;
		}
    	canCheckCollision = false;
        collisionCheckTimer.restart();
	}
	
	/*
	 * MOVEMENT METHODS:
	 * THIS METHOD PROVIDES RANDOM CHANGES IN X AND Y SPEED 
	 * FOR MORE VARIABILITY IN GAMEPLAY
	 */
	
	public static void changeMovement() {
		int newXMovement = (int) (Math.random() * (MAX_X_BALL_MOVEMENT - MIN_X_BALL_MOVEMENT) + MIN_X_BALL_MOVEMENT);
		int newYMovement = (int) (Math.random() * (MAX_Y_BALL_MOVEMENT - MIN_Y_BALL_MOVEMENT) + MIN_Y_BALL_MOVEMENT);
		ballXMovement = newXMovement;
		ballYMovement = newYMovement;
		System.out.println("X MOVEMENT = " + ballXMovement);
		System.out.println("Y MOVEMENT = " + ballYMovement);
	}
	
	//RANDOMIZES THE Y MOVEMENT VALUE FOR MORE RANDOMIZED GAMEPLAY
	@Override
	public void randomizeMovement() {
		ballYMovement = randomizer.nextInt(randomizeXBound, randomizeYBound);
	}



	

}
