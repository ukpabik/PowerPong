package view;

import java.awt.Graphics2D;

import shapes.Rectangle;

public class ARectangleView extends AShapeView{

	
	
	
	@Override
	public void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	public void drawRectangle(Graphics2D graphics, Rectangle rect) {
		graphics.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		System.out.println("Drawing: " + rect);
	}
}
