package view;

import java.awt.Graphics2D;


public class PlayerTwoView extends AShapeView{
	@Override
	public void paint(Graphics2D g) {
		this.drawPlayer(g, game.getPlayerTwo());
		this.drawPoints(g, game.getPlayerTwo());
	}
}
