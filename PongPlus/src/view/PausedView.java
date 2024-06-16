package view;

import java.awt.Graphics2D;

public class PausedView extends AShapeView{
	
	@Override
	public void paint(Graphics2D g) {
		this.drawPauseScreen(g, contentWidth, contentHeight);
	}
	
	
}
