package gui;

import shapes.Player;

public interface PointSystem {

	public void addPoints(Player p);

	public void removePoints(Player p);

	public void resetPoints(GameDisplay game);

}
