package view;

import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;

import factory.PongFactory;
import gui.GameDisplay;
import shapes.Circle;
import shapes.Rectangle;

public class AShapeView extends Views implements ShapeView{

	
	@Override
	public void drawRectangle(Graphics2D graphics, Rectangle rect) {
		graphics.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		System.out.println("Drawing: " + rect);
	}
	
	@Override
	public void drawBall(Graphics2D graphics, Circle circle) {
		graphics.drawOval(circle.getX(), circle.getY(), circle.getWidth(), circle.getHeight());
		System.out.println("Drawing: " + circle);
	}



}
