package view;

import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;

import factory.PongFactory;
import gui.GameDisplay;

public abstract class Views implements View{
	APongPainter painter = PongFactory.pongPainterFactoryMethod();
	GameDisplay game = PongFactory.gameDisplayFactoryMethod();
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		painter.repaint();
		
	}
	
	@Override
	public void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
}
