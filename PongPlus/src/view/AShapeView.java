package view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.beans.PropertyChangeEvent;

import factory.PongFactory;
import gui.GameDisplay;
import shapes.Circle;
import shapes.Rectangle;

public class AShapeView extends Views implements ShapeView{

	
	@Override
	public void drawRectangle(Graphics2D graphics, Rectangle rect) {
		graphics.setColor(Color.WHITE); 
	     
	    graphics.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		
	}
	
	@Override
	public void drawBall(Graphics2D graphics, Circle circle) {
		
		graphics.setColor(Color.WHITE);
		
		graphics.fillOval(circle.getX(), circle.getY(), circle.getWidth(), circle.getHeight());
		
	}
	@Override
	public void drawBackground(Graphics2D graphics, Rectangle rect) {
	    graphics.setColor(Color.BLACK); 
	     
	    graphics.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		
	}



}
