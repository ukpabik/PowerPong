package view;

import java.awt.Graphics2D;

public class OptionsView extends AShapeView{
	
	@Override
	public void paint(Graphics2D g) {
		this.drawOptionsMenu(g, contentWidth, contentHeight);
	}
}