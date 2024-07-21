package powerups;

import java.util.Random;

public class PowerUpManager implements PowerManager{
	
	static Random rand = new Random();
	
	static PowerClass increaseSize = new IncreaseSize();
	
	
	@Override
	public PowerClass getIncreaseSize() {
		return increaseSize;
	}
	
	public static PowerClass getRandomPowerUp() {
		int randomNumber = rand.nextInt(0, (PowerUpEnum.values().length));
		
		switch(PowerUpEnum.values()[randomNumber]) {
		case INCREASE_SIZE:
			return increaseSize;
		}
		return null;
		
	}
}
