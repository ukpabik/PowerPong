package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import shapes.Circle;
import shapes.Player;
import shapes.Rectangle;

public abstract class AShapeView extends Views implements ShapeView{
	
	//PLACEMENT OF TEXT
	public static final int 
		PLACEMENT_VALUE = 5,
		PLAYER_TWO_X_OFFSET = 35,
		TEXT_SPACE = 50, 
		TITLE_PLACEMENT_X = 2,
		TITLE_PLACEMENT_Y = 4
	;
	
	
	//FONTS AND COLORS
	static final Color TRANSPARENT = new Color(255, 0, 0, 0);
	static final Color SEMI_TRANSPARENT = new Color(255, 255, 255, 128);
	static final Font 
		POINT_FONT = new Font("Calibri", Font.BOLD, 128),
		MAIN_MENU_FONT = new Font("Arial", Font.BOLD, 128),
		SELECTABLE_FONT = new Font("Arial", Font.ITALIC, 32);
		
	
	;
	
	
	
	//MAIN MENU SELECTABLES
	static final List<String> STRINGS = new ArrayList<>(Arrays.asList("VS PLAYER", "VS CPU", "CONTROLS", "QUIT GAME"));
	static final String TITLE = "PONG PLUS";
	
	
	
	
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
	
	public void drawMainMenu(Graphics2D g, int contentPaneWidth, int contentPaneHeight) {
		boolean firstIteration = true;
		
		
		g.setFont(MAIN_MENU_FONT);
	    g.setColor(Color.WHITE);
	    
	    
	    //FONTMETRICS HELPS WITH GETTING THE TEXT TO BE IN THE MIDDLE OF SCREEN
	    FontMetrics fm = g.getFontMetrics(MAIN_MENU_FONT);
	    
	   
	    int x = (contentPaneWidth - fm.stringWidth(TITLE)) / TITLE_PLACEMENT_X;
	    int y = ((contentPaneHeight - fm.getHeight()) / TITLE_PLACEMENT_Y);
	    
	    
	    g.drawString(TITLE, x, y);
	    
	    
	    g.setFont(SELECTABLE_FONT);
	    fm = g.getFontMetrics(SELECTABLE_FONT);
	    
	    
	    for(String s : STRINGS) {
	    	if (firstIteration) {
	    		x = (contentPaneWidth - fm.stringWidth(s)) / TITLE_PLACEMENT_X;
		    	y = contentPaneHeight - y;
		    	g.drawString(s, x, y);
		    	firstIteration = false;
	    	}
	    	else {
	    		y = y + TEXT_SPACE;
	    	    g.drawString(s, x, y);
	    	}
	    }
	    
	}



}
