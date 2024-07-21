package view;


import java.awt.Graphics2D;

import shapes.Circle;
import shapes.Player;
import shapes.Rectangle;

public interface ShapeView extends View{

	public void drawRectangle(Graphics2D graphics, Rectangle rect);

	public void drawBall(Graphics2D graphics, Circle circle);
	
	public void drawPoints(Graphics2D graphics, Player p);

	public void drawPlayer(Graphics2D graphics, Player p);
}
