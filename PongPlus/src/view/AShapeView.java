package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import shapes.Circle;
import shapes.Player;
import shapes.Rectangle;

public class AShapeView extends Views implements ShapeView{
	
	public static final int 
		PLACEMENT_VALUE = 5,
		PLAYER_TWO_X_OFFSET = 35
	;
	static final Color transparent = new Color(255, 0, 0, 0);
	
	static final Color SEMI_TRANSPARENT = new Color(255, 255, 255, 128);
	static final Font POINT_FONT = new Font("Calibri", Font.BOLD, 128);
	
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
	
	@Override
	public void drawPoints(Graphics2D graphics, Player p) {
		graphics.setFont(POINT_FONT);
		graphics.setColor(SEMI_TRANSPARENT);
		if (p.equals(game.getPlayerOne())){
			graphics.drawString("" + p.getPoints(), game.getLeftScreen() + game.getRightScreen() / PLACEMENT_VALUE, 
					game.getTopScreen() + game.getBotScreen() / PLACEMENT_VALUE);
		}
		else if (p.equals(game.getPlayerTwo())) {
			graphics.drawString("" + p.getPoints(), game.getRightScreen() - game.getRightScreen() / PLACEMENT_VALUE - PLAYER_TWO_X_OFFSET, 
					game.getTopScreen() + game.getBotScreen() / PLACEMENT_VALUE);
		}
	}



}
