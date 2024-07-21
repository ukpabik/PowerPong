package gui;

import factory.PongFactory;
import shapes.Player;

public abstract class Points{
	static GameDisplay game = PongFactory.gameDisplayFactoryMethod();
	static Player lastScoringPlayer;
	static int numberOfRounds = 0;
	
	public static void addPoints(Player p) {
		p.setPoints(p.getPoints() + 1);
	}
	
	public static void removePoints(Player p) {
		p.setPoints(p.getPoints() - 1);
	}
	
	public static void resetPoints(GameDisplay game) {
		game.getPlayerOne().setPoints(0);
		game.getPlayerTwo().setPoints(0);
	}
	
	public static Player getLastScoringPlayer() {
		return lastScoringPlayer;
	}
	
	public static void setLastScoringPlayer(Player p) {
		lastScoringPlayer = p;
	}
	
	public static int getNumberOfRounds() {
		return numberOfRounds;
	}
	
	public static void updateNumberOfRounds() {
		numberOfRounds = game.getPlayerOne().getPoints() + game.getPlayerTwo().getPoints();
	}
	
	
}
