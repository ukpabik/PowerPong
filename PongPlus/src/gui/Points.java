package gui;

import shapes.Player;

public class Points implements PointSystem{
	static Player lastScoringPlayer;
	
	@Override
	public void addPoints(Player p) {
		p.setPoints(p.getPoints() + 1);
	}
	
	@Override
	public void removePoints(Player p) {
		p.setPoints(p.getPoints() - 1);
	}
	
	@Override
	public void resetPoints(GameDisplay game) {
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
