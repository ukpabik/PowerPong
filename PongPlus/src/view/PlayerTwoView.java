package view;

import java.awt.Graphics2D;

import gui.AGameDisplay;

public class PlayerTwoView extends AShapeView{
	@Override
	public void paint(Graphics2D g) {
		this.drawRectangle(g, game.getPlayerTwo());
		this.drawPoints(g, game.getPlayerTwo());
	}
}
