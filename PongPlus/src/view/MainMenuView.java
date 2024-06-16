package view;

import java.awt.Graphics2D;

public class MainMenuView extends AShapeView{
	
	static int contentWidth, contentHeight;
	
	@Override
	public void paint(Graphics2D g) {
		this.drawMainMenu(g, contentWidth, contentHeight);
	}
	
	
	
	
	public static void setContentPaneDimensions(int width, int height) {
        contentWidth = width;
        contentHeight = height;
    }
}
