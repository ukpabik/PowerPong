package powerups;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import factory.PongFactory;
import gui.GameDisplay;
import shapes.BoundedShape;

public abstract class PowerClass {
	public static final int 
		MAX_OBJECT_SIZE = 3,
		POWER_UP_DELAY = 7000
		
	;
	public static int objectSize = 1;
	
	Image image;
	Timer powerUpTimer;
	BoundedShape currentObject;
	boolean powerUpComplete = false;
	GameDisplay display = PongFactory.gameDisplayFactoryMethod();
	
	
	
	
	public PowerClass() {
		//LENGTH OF HAVING A POWER UP
		powerUpTimer = new Timer(POWER_UP_DELAY, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				powerUpComplete = true;
				resetAction(currentObject);
			}
			
		});
	}
	
	
	//STATIC METHOD FOR CHANGING OBJECT SIZE
	public static void changeObjectSize(int size) {
		
		objectSize += size;
		if (objectSize < 1) {
			objectSize = 1;
		}
		else if (objectSize > MAX_OBJECT_SIZE) {
			objectSize = MAX_OBJECT_SIZE;
		}
	}
	
	
	//FOR BALL POWER UPS
	public Image getImage() {
		return image;
	}
	
	public void setImage(String fileName) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/images/" + fileName));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	// THE POWER UP ACTION
	public void action(BoundedShape object) {
		powerUpTimer.restart();
		currentObject = object;
	}
	
	public abstract void resetAction(BoundedShape object);
	
	
	
	
	//METHOD FOR CHANGING THE SIZE OF THE OBJECT
	public void objectScale(double scaleFactor, BoundedShape object) {
		
		if (object.equals(display.getPlayerOne()) || object.equals(display.getPlayerTwo())) {
			object.setWidth((int)(display.getPlayerOriginalWidth() * scaleFactor));
			object.setHeight((int)(display.getPlayerOriginalHeight() * scaleFactor));
		}
		
		else if (object.equals(display.getPointBall())) {
			object.setWidth((int)(display.getBallOriginalWidth() * scaleFactor));
			object.setHeight((int)(display.getBallOriginalHeight() * scaleFactor));
		}
		
	}
	
	/**
	 * The object needs to be on top of the ball, to show the power up the ball currently has.
	 * The ball needs to change to a powered up ball every 3 or so rounds.
	 * When the ball is scored by a player, that player gains a power up. 
	 * This could mean the player gains size, or the other player loses size, or the ball goes in slo motion when the scoring player hits it.
	 * This could also mean the player moves slower or faster.
	 * 
	 * 
	 * The ball will use the powerup image to display it on itself. The players will use the action from the powerup. The ball will not use the action,
	 * unless it is a specific ball power up, like increasing size of ball or speed of ball.
	 */
}
