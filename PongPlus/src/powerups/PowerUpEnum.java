package powerups;

public enum PowerUpEnum {
	INCREASE_SIZE("Increase size of paddle."),
	REVERSE_CONTROLS("Reverses controls of enemy player.");
	
	private final String description;
	
	PowerUpEnum(String string) {
		this.description = string;
	}
	
	public String getDescription() {
		return description;
	}
}
