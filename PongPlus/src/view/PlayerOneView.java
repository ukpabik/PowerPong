package view;

import java.awt.Graphics2D;

import gui.AGameDisplay;

public class PlayerOneView extends AShapeView{
	@Override
	public void paint(Graphics2D g) {
		this.drawRectangle(g, game.getPlayerOne());
		this.drawPoints(g, game.getPlayerOne());
	}
}
