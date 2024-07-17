package view;

import java.awt.Graphics2D;

public class MainMenuView extends AShapeView{
	
	@Override
	public void paint(Graphics2D g) {
		this.drawMenu(g, contentWidth, contentHeight, MAIN_MENU_STRINGS, MAIN_TITLE);
	}
	
	
}
