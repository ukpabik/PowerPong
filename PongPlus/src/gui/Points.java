package gui;

import shapes.Player;

public abstract class Points implements PointSystem {
	static Player lastScoringPlayer;
	
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
	
	
	
}
