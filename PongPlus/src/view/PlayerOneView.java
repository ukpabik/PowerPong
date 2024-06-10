package view;

import java.awt.Graphics2D;

public class PlayerOneView extends ARectangleView{
	@Override
	public void paint(Graphics2D g) {
		this.drawRectangle(g, game.getPlayerOne());
	}
}
