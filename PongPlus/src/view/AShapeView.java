package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.APongController;
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
	
	
	//WINDOW SIZE
	public static int contentWidth, contentHeight;
	
	
	//FONTS AND COLORS
	public static final Color TRANSPARENT = new Color(255, 0, 0, 0);
	public static final Color SEMI_TRANSPARENT = new Color(0.0f, 0.0f, 0.0f, 0.7f);
	public static final Font 
		POINT_FONT = new Font("Calibri", Font.BOLD, 64),
		MAIN_MENU_FONT = new Font("Arial", Font.BOLD, 128),
		SELECTABLE_FONT = new Font("Arial", Font.ITALIC, 32)
		
	
	;
	
	
	
	//MAIN MENU SELECTABLES
	public static final List<String> MAIN_MENU_STRINGS = new ArrayList<>(Arrays.asList("VS PLAYER", "VS CPU", "OPTIONS", "QUIT GAME"));
	static final String MAIN_TITLE = "PONG PLUS";
	
	//PAUSE MENU SELECTABLES
	public static final List<String> PAUSE_MENU_STRINGS = new ArrayList<>(Arrays.asList("RESUME", "OPTIONS", "QUIT GAME"));
	static final String PAUSE_TITLE = "PAUSED";
	
	//FOR SETTING PLACEMENT OF MENU ITEMS
	static boolean firstIteration = false;
	
	
	
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
		graphics.setColor(Color.WHITE);
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
		firstIteration = true;
		
		
		g.setFont(MAIN_MENU_FONT);
	    g.setColor(Color.WHITE);
	    
	    
	    //FONTMETRICS HELPS WITH GETTING THE TEXT TO BE IN THE MIDDLE OF SCREEN
	    FontMetrics fm = g.getFontMetrics(MAIN_MENU_FONT);
	    
	   
	    int x = (contentPaneWidth - fm.stringWidth(MAIN_TITLE)) / TITLE_PLACEMENT_X;
	    int y = ((contentPaneHeight - fm.getHeight()) / TITLE_PLACEMENT_Y);
	    
	    
	    g.drawString(MAIN_TITLE, x, y);
	    
	    
	    g.setFont(SELECTABLE_FONT);
	    fm = g.getFontMetrics(SELECTABLE_FONT);
	    
	    
	    for(String s : MAIN_MENU_STRINGS) {
	    	
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
	    	if (s == MAIN_MENU_STRINGS.get(APongController.currentSelection)) {
	    		g.drawString(s, x - 2, y);
	    	}
	    }
	    System.out.println("DREW MAIN MENU");
	}

	public void drawPauseScreen(Graphics2D g, int contentPaneWidth, int contentPaneHeight) {
		
		drawTransparentScreen(g, contentPaneWidth, contentPaneHeight);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		firstIteration = true;
		
		g.setFont(MAIN_MENU_FONT);
	    g.setColor(Color.WHITE);
	    
	    
	    //FONTMETRICS HELPS WITH GETTING THE TEXT TO BE IN THE MIDDLE OF SCREEN
	    FontMetrics fm = g.getFontMetrics(MAIN_MENU_FONT);
	    
	   
	    int x = (contentPaneWidth - fm.stringWidth(PAUSE_TITLE)) / TITLE_PLACEMENT_X;
	    int y = ((contentPaneHeight - fm.getHeight()) / TITLE_PLACEMENT_Y);
	    
	    
	    g.drawString(PAUSE_TITLE, x, y);
	    
	    
	    g.setFont(SELECTABLE_FONT);
	    fm = g.getFontMetrics(SELECTABLE_FONT);
	    
	    
	    for(String s : PAUSE_MENU_STRINGS) {
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
	
	public void drawTransparentScreen(Graphics2D g, int contentPaneWidth, int contentPaneHeight) {
		g.setColor(SEMI_TRANSPARENT);
		g.fillRect(0, 0, contentPaneWidth, contentPaneHeight);
	}
	
	
	
	
	public static void setContentPaneDimensions(int width, int height) {
        contentWidth = width;
        contentHeight = height;
    }



}
