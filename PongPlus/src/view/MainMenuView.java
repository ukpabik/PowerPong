package view;

import java.awt.Graphics2D;

public class MainMenuView extends AShapeView{
	
	@Override
	public void paint(Graphics2D g) {
		this.drawMainMenu(g, contentWidth, contentHeight);
	}
	
	
}
