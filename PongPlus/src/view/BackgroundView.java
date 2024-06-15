package view;

import java.awt.Graphics2D;
public class BackgroundView extends AShapeView{

	

	@Override
	public void paint(Graphics2D g) {
		this.drawBackground(g, game.getBackground());
	}
	
}
