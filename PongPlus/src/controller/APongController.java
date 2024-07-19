package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

import audio.SoundEffects;
import collision.ACollisionChecker;
import factory.PongFactory;
import gui.GameDisplay;
import gui.GameState;
import gui.Points;
import shapes.BoundedShape;
import shapes.Player;
import view.APongPainter;
import view.AShapeView;

public class APongController implements PongController{
	static final int 
		
		COLLISION_DELAY = 15,
		MAX_X_BALL_MOVEMENT = 6,
		MIN_X_BALL_MOVEMENT = 2,
		MAX_Y_BALL_MOVEMENT = 4,
		MIN_Y_BALL_MOVEMENT = 2,
		MAX_MOVEMENT_LENGTH = 5,
		MAX_CPU_MOVEMENT_LENGTH = 2,
		UPDATE_INTERVAL = 4,
		THREAD_DIVISOR = 1000000
	;
	
	
	
	//BALL AND PLAYER MOVEMENT
	static int 
		ballXMovement = MAX_X_BALL_MOVEMENT,
		ballYMovement = MAX_Y_BALL_MOVEMENT,
		randomizeXBound = MIN_Y_BALL_MOVEMENT,
		randomizeYBound = MAX_Y_BALL_MOVEMENT + 1,
		CPU_MOVEMENT_LENGTH = MAX_CPU_MOVEMENT_LENGTH,
		MOVEMENT_LENGTH = MAX_MOVEMENT_LENGTH
		
	;


	//MENU SELECTION
	public static int currentSelection = 0;
	
	//INPUT VARIABLES
	boolean 
		upPress = false, 
		downPress = false,
		wPress = false,
		sPress = false,
		canCheckCollision = true,
		running = false,
		cpu = false
	;
	
	 
	//TIMER AND GAME STATE VARIABLES
	Thread gameThread;
	private Timer collisionCheckTimer;
	
	
	
	
	
	private int justScoredCounter, ballCounter;
	private boolean justScored, roundStarted, gameStarted = false;
	
	
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
		if (game.getCurrentState() == GameState.MAIN_MENU) {
			resetGame();
		}
		running = true;
		gameStarted = true;
		gameThread = new Thread(this);
		gameThread.start();
		game.setCurrentState(GameState.PLAYING);
	}
	
	@Override
	public void run() {
		while (running) {
			if (game.getCurrentState() == GameState.PLAYING) {
				long startTime = System.nanoTime();
				
				
				
				/*
				 * DELAY THE BALL FROM MOVING UNTIL THE GAME HAS BEGUN
				 * OR A SECOND AFTER THE PLAYER SCORES
				 */
				roundBallTimer();
				
				movePlayer(game.getPlayerOne());
				if (cpu == true) {
					moveCPU(game.getPlayerTwo()); 
				}
				else {
					movePlayer(game.getPlayerTwo());
				}
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
		
		//SCALING
		case KeyEvent.VK_LEFT:
			if (game.getCurrentState() == GameState.OPTIONS && currentSelection == 1) {
				AShapeView.changeGUISize(-1);
				game.guiScale(AShapeView.guiScale);
			}
			break;
			
		case KeyEvent.VK_RIGHT:
			if (game.getCurrentState() == GameState.OPTIONS && currentSelection == 1) {
				AShapeView.changeGUISize(1);
				game.guiScale(AShapeView.guiScale);
			}
			break;
			
		//MOVEMENT
		case KeyEvent.VK_UP: 
			upPress = true;
			changeSelection(1);
			break;
		
		case KeyEvent.VK_DOWN: 
			downPress = true;
			changeSelection(-1);
			break;
		case KeyEvent.VK_W:
			wPress = true;
			break;
		case KeyEvent.VK_S:
			sPress = true;
			break;
			
		//ACTIONS
		case KeyEvent.VK_P:
			if (game.getCurrentState() == GameState.PLAYING) {
                pauseGame();
                System.out.println("Pause");
            } 
			else if (game.getCurrentState() == GameState.PAUSED) {
                resumeGame();
                System.out.println("Unpause");
            }
            break;
		case KeyEvent.VK_SPACE:
			select();
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
		
		int oldX = ball.getX();
        int oldY = ball.getY();
        
        
        
        //ONLY MOVE BALL IF THE GAME HAS STARTED
        
        if (running && roundStarted && !justScored) {
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
                    ballHit();
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
                    ballHit();
                }
                
                //FOR TOP AND BOTTOM COLLISION 
                else if (ball.getY() <= game.getTopScreen()) {
                	changeYSign(1);
                	ballHit();
                }
                else if (ball.getY() + ball.getHeight() >= game.getBotScreen()) {
                	changeYSign(-1);
                	ballHit();
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

		/*
		 * STORE OLD X AND Y VALUES TO KEEP OBJECTS FROM GOING INSIDE
		 * EACH OTHER.
		 */
		
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

	
	//METHOD THAT ALLOWS FOR PLAYER VS CPU
	
	@Override
	public void moveCPU(Player player) {
		if (roundStarted && !justScored) {
			int movement = game.getPointBall().getY();
			
			if (player.getY() > movement) {
				player.move(player.getX(), player.getY() - CPU_MOVEMENT_LENGTH);
			}
			else if (player.getY() < movement) {
				player.move(player.getX(), player.getY() + CPU_MOVEMENT_LENGTH);
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
	private void randomizeMovement() {
		ballYMovement = randomizer.nextInt(randomizeXBound, randomizeYBound);
	}
	
	//THREAD HELPER METHOD FOR TIMERS BETWEEN ROUNDS
	private void roundBallTimer() {
		if (!roundStarted) {
			ballCounter++;
			if (ballCounter >= (1000 / UPDATE_INTERVAL)) {
				ballCounter = 0;
				ballXMovement *= negativeOrPositive();
				roundStarted = true;
				randomizeMovement();
				game.getPointBall().setVisible(true);
			}
		}
		else if (justScored) {
			justScoredCounter++;
			if (justScoredCounter >= (1000 / UPDATE_INTERVAL)) {
				justScoredCounter = 0;
				
				ballXMovement *= negativeOrPositive();
				
				justScored = false;
				randomizeMovement();
				game.getPointBall().setVisible(true);
			}
		}
	}
	
	public void ballHit() {
		try {
			SoundEffects.playSound("Ball-Hit.wav");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	/*
	 * HELPER METHOD THAT RETURNS -1 or 1, HAS A 50% CHANCE
	 * THIS HELPS WITH CHANGING THE X DIRECTION OF THE BALL EVERY ROUND
	 */
	private int negativeOrPositive() {
		boolean isPositive = randomizer.nextBoolean();
		
		if (isPositive) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	
	//METHOD FOR PAUSING THE GAME
	@Override
	public void pauseGame() {
        if (game.getCurrentState() == GameState.PLAYING) {
        	game.setCurrentState(GameState.PAUSED);
        	running = false;
        	painter.repaint();
        }
    }

	//METHOD FOR RESUMING THE GAME
	@Override
    public void resumeGame() {
        if (game.getCurrentState() == GameState.PAUSED) {
        	game.setCurrentState(GameState.PLAYING);
        	running = true;
        	gameThread = new Thread(this);
        	gameThread.start();
        }
    }

	
	// METHOD FOR SHOWING SELECTION IN VARIOUS MENUS
	@Override
	public void select() {
		if (game.getCurrentState() == GameState.MAIN_MENU) {
			mainMenuSelect();
		}
		else if (game.getCurrentState() == GameState.PAUSED) {
			pauseMenuSelect();
		}
		else if (game.getCurrentState() == GameState.OPTIONS) {
			optionsMenuSelect();
		}
	}
	
	//SELECTION FOR MAIN MENU
	
	@Override
	public void mainMenuSelect() {
		switch(currentSelection) {
		case 0:
			startGame();
			break;
		case 1:
			cpu = true;
			startGame();
			break;
		case 2:
			game.setCurrentState(GameState.OPTIONS);
			break;
		case 3:
			System.exit(0);
			break;
			
		}
		currentSelection = 0;
		painter.repaint();
	}
	
	//SELECTION FOR PAUSE MENU
	
	@Override
	public void pauseMenuSelect() {
		switch(currentSelection) {
		case 0:
			resumeGame();
			break;
		case 1:
			game.setCurrentState(GameState.OPTIONS);
			break;
		case 2:
			game.setCurrentState(GameState.MAIN_MENU);
			resetGame();
			break;
		}
		currentSelection = 0;
		painter.repaint();
	}
	
	//SELECTION FOR OPTIONS MENU
	@Override
	public void optionsMenuSelect() {
		switch(currentSelection) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			if (gameStarted) {
				game.setCurrentState(GameState.PAUSED);
			}
			else {
				game.setCurrentState(GameState.MAIN_MENU);
			}
			currentSelection = 0;
			break;
			
		}
		painter.repaint();
	}
	
	// METHOD FOR SHOWING SELECTION IN MAIN MENU
	
	@Override
	public void changeSelection(int change) {
		
		if (game.getCurrentState() != GameState.PLAYING) {
			GameState g = game.getCurrentState();
			int max = 0;
			switch(g) {
			case MAIN_MENU:
				max = AShapeView.MAIN_MENU_STRINGS.size() - 1;
				break;
			case PAUSED:
				max = AShapeView.PAUSE_MENU_STRINGS.size() - 1;
				break;
			case OPTIONS:
				max = AShapeView.OPTIONS_MENU_STRINGS.size() - 1;
				break;
			default:
				break;
				
			}
			if (change == -1) {
				currentSelection++;
				if (currentSelection > max) {
					currentSelection = 0;
				}
			}
			else if (change == 1) {
				currentSelection--;
				if (currentSelection < 0) {
					currentSelection = max;
				}
			}
			try {
				SoundEffects.playSound("Select-Sound.wav");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			painter.repaint();
		}
		
	}
	
	//METHOD FOR RESETTING ALL VALUES
	
	@Override
	public void resetGame() {
		ballXMovement = MAX_X_BALL_MOVEMENT;
		ballYMovement = MAX_Y_BALL_MOVEMENT;
		MOVEMENT_LENGTH = MAX_MOVEMENT_LENGTH;
		CPU_MOVEMENT_LENGTH = MAX_CPU_MOVEMENT_LENGTH;
		Points.resetPoints(game);
		game.setPlayerAndBall();
		roundStarted = false;
		gameStarted = false;
		game.getPointBall().setVisible(false);
	}
	
	

}
