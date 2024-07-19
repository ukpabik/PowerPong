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
		TITLE_PLACEMENT_Y = 4,
		OPTIONS_PLACEMENT_Y = 8,
		OPTIONS_RECT_OFFSET = 100, 
		SCREEN_OFFSET = 10,
		CONTENT_OFFSET = 20,
		ARC_SIZE = 10,
		TEXT_OFFSET = 50,
		GUI_RECT_HEIGHT = 50,
		MAX_GUI_SCALE = 5
	;
	
	
	//WINDOW SIZE
	public static int contentWidth, contentHeight;
	
	//GUI SIZE
	public static int guiScale = 1;
	
	
	//FONTS AND COLORS
	public static final Color TRANSPARENT = new Color(255, 0, 0, 0);
	public static final Color SEMI_TRANSPARENT = new Color(0.0f, 0.0f, 0.0f, 0.7f);
	public static final Font 
		POINT_FONT = new Font("Calibri", Font.BOLD, 64),
		MAIN_MENU_FONT = new Font("Arial", Font.BOLD, 128),
		SELECTABLE_FONT = new Font("Arial", Font.ITALIC, 32),
		OPTIONS_FONT = new Font("Arial", Font.BOLD, 64),
		CONTROLS_FONT = new Font("Arial", Font.BOLD, 24)
		
	
	;
	
	
	
	//MAIN MENU SELECTABLES
	public static final List<String> MAIN_MENU_STRINGS = new ArrayList<>(Arrays.asList("VS PLAYER", "VS CPU", "OPTIONS", "QUIT GAME"));
	static final String MAIN_TITLE = "PONG PLUS";
	
	//PAUSE MENU SELECTABLES
	public static final List<String> PAUSE_MENU_STRINGS = new ArrayList<>(Arrays.asList("RESUME", "OPTIONS", "MAIN MENU"));
	static final String PAUSE_TITLE = "PAUSED";
	
	//OPTIONS MENU SELECTABLES
	public static final List<String> OPTIONS_MENU_STRINGS = new ArrayList<>(Arrays.asList("CONTROLS", "GUI SCALE", "BACK"));
	public static final List<String> CONTROLS_STRINGS = new ArrayList<>(Arrays.asList("A / Left Arrow", "D / Right Arrow", 
			"W / Up Arrow", "S / Down Arrow", "Spacebar", "Move Left", "Move Right", "Move Up", "Move Down", "Select"));
	static final String OPTIONS_TITLE = "OPTIONS";
	static final String GUI_TITLE = "GUI SCALE";
	
	
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
	
	public void drawMenu(Graphics2D g, int contentPaneWidth, int contentPaneHeight, List<String> strings, String title) {
		firstIteration = true;
		
		
		g.setFont(MAIN_MENU_FONT);
	    g.setColor(Color.WHITE);
	    
	    
	    //FONTMETRICS HELPS WITH GETTING THE TEXT TO BE IN THE MIDDLE OF SCREEN
	    FontMetrics fm = g.getFontMetrics(MAIN_MENU_FONT);
	    
	   
	    int x = (contentPaneWidth - fm.stringWidth(title)) / TITLE_PLACEMENT_X;
	    int y = ((contentPaneHeight - fm.getHeight()) / TITLE_PLACEMENT_Y);
	    
	    
	    g.drawString(title, x, y);
	    
	    
	    g.setFont(SELECTABLE_FONT);
	    fm = g.getFontMetrics(SELECTABLE_FONT);
	    
	    
	    for(String s : strings) {
	    	
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
	    	if (s == strings.get(APongController.currentSelection)) {
	    		g.drawString(s, x - 2, y);
	    	}
	    }
	}

	public void drawPauseMenu(Graphics2D g, int contentPaneWidth, int contentPaneHeight) {
		
		drawTransparentScreen(g, contentPaneWidth, contentPaneHeight);
		drawMenu(g, contentPaneWidth, contentPaneHeight, PAUSE_MENU_STRINGS, PAUSE_TITLE);
	    
	    
	}
	
	public void drawOptionsMenu(Graphics2D g, int contentPaneWidth, int contentPaneHeight) {
		firstIteration = true;
		g.setFont(OPTIONS_FONT);
	    g.setColor(Color.WHITE);
	    
	    
	    //FONTMETRICS HELPS WITH GETTING THE TEXT TO BE IN THE MIDDLE OF SCREEN
	    FontMetrics fm = g.getFontMetrics(OPTIONS_FONT);
	    
	   
	    int x = (contentPaneWidth - fm.stringWidth(OPTIONS_TITLE)) / TITLE_PLACEMENT_X;
	    int y = ((contentPaneHeight - fm.getHeight()) / OPTIONS_PLACEMENT_Y);
	    
	    g.drawString(OPTIONS_TITLE, x, y);
	    
	    //PLACEMENT OF THE STRINGS ON THE OPTIONS MENU
	    
	    int leftHeight = y * OPTIONS_MENU_STRINGS.size();
	    int leftWidth = contentPaneWidth / 2 - OPTIONS_RECT_OFFSET;
	    int leftX = SCREEN_OFFSET;
	    int leftY = y + OPTIONS_RECT_OFFSET;
	    g.drawRoundRect(leftX, leftY, leftWidth, leftHeight, ARC_SIZE, ARC_SIZE);
	    
	    
	    //MAKES SURE THE RECTANGLE DOESNT GO OFFSCREEN
	    int rightHeight = contentPaneHeight - y - OPTIONS_RECT_OFFSET - SCREEN_OFFSET;
	    int rightWidth = contentPaneWidth - leftWidth - CONTENT_OFFSET - SCREEN_OFFSET;
	    int rightX = leftWidth + CONTENT_OFFSET;
	    int rightY = y + OPTIONS_RECT_OFFSET;
	    g.drawRoundRect(rightX, rightY, rightWidth, rightHeight, ARC_SIZE, ARC_SIZE);
	    
	    
	    g.setFont(SELECTABLE_FONT);
	    fm = g.getFontMetrics(SELECTABLE_FONT);
	    //DRAWING TEXT IN LEFT BOX FOR SELECTABLES
	    for(String s : OPTIONS_MENU_STRINGS) {
	    	
	    	if (firstIteration) {
	    		x = (leftWidth - fm.stringWidth(s)) / TITLE_PLACEMENT_X;
		    	y = leftY + TEXT_OFFSET;
		    	g.drawString(s, x, y);
		    	firstIteration = false;
	    	}
	    	else {
	    		y = y + TEXT_SPACE;
	    	    g.drawString(s, x, y);
	    	}
	    	if (s == OPTIONS_MENU_STRINGS.get(APongController.currentSelection)) {
	    		g.drawString(s, x - 2, y);
	    	}
	    }
	    
	    
	    //DRAWING TEXT IN RIGHT BOX
	    switch(APongController.currentSelection) {
	    
	    case 0:
	    	g.setFont(CONTROLS_FONT);
		    fm = g.getFontMetrics(CONTROLS_FONT);
	    	//LOGIC FOR DRAWING CONTROLS
	    	firstIteration = true;
	    	int middleIndex = CONTROLS_STRINGS.size() / 2;
	    	
	    	for(String s : CONTROLS_STRINGS) {
		    	
		    	if (firstIteration) {
		    		x = rightX + TEXT_OFFSET;
			    	y = rightY + TEXT_OFFSET;
			    	g.drawString(s, x, y);
			    	firstIteration = false;
		    	}
		    	
		    	else {
		    		int current = CONTROLS_STRINGS.indexOf(s);
		    		if (current != 0 && current == middleIndex) {
			    		x = rightX + rightWidth / 2;
			    		y = rightY + TEXT_OFFSET;
			    	}
		    		else {
		    			y = y + TEXT_SPACE;
		    		}
		    	    g.drawString(s, x, y);
		    	}
		
		    	
		    	
		    }
	    	break;
	    case 1:
	    	//DRAWING THE GUI TITLE
	    	g.setFont(SELECTABLE_FONT);
		    fm = g.getFontMetrics(SELECTABLE_FONT);
	    	x = (rightWidth - fm.stringWidth(GUI_TITLE)) / TITLE_PLACEMENT_X + rightX;
	    	y = rightY + TEXT_OFFSET;
	    	g.drawString(GUI_TITLE, x, y);
	    	
	    	
	    	//DRAWING THE GUI RECTANGLE
	    	int guiRectWidth = (rightWidth - CONTENT_OFFSET * 2) / MAX_GUI_SCALE;
	    	int guiRectX = rightX + CONTENT_OFFSET;
	    	int guiRectY = rightY + OPTIONS_RECT_OFFSET;
	    	g.setColor(Color.WHITE);
	    	g.drawRoundRect(guiRectX, guiRectY, guiRectWidth * MAX_GUI_SCALE, GUI_RECT_HEIGHT, ARC_SIZE, ARC_SIZE);
	    	g.fillRoundRect(guiRectX, guiRectY, guiRectWidth * guiScale, GUI_RECT_HEIGHT, ARC_SIZE, ARC_SIZE);
	    	
	    	//DRAWING THE MIN AND MAXES
	    	
	    	
	    	break;
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

	//STATIC METHOD FOR CHANGING GUI SIZE
	public static void changeGUISize(int size) {
		guiScale += size;
		if (guiScale < 1) {
			guiScale = 1;
		}
		else if (guiScale > MAX_GUI_SCALE) {
			guiScale = MAX_GUI_SCALE;
		}
	}


}
