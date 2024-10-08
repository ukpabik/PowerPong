package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.APongController;
import enums.OptionsMenuSelections;
import factory.PongFactory;
import fonts.FontManager;
import powerups.PowerUpEnum;
import powerups.ReverseControls;
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
		NUMBER_PLACEMENT = 100,
		IMAGE_OFFSET = 50,
		LINE_HEIGHT = 2,
		POWERUP_MENU_OFFSET = 100
	;
	
	//FOR CUSTOM FONTS
	FontManager fontManager = PongFactory.fontManagerFactoryMethod();	
	
	
	//WINDOW SIZE
	public static int contentWidth, contentHeight;
	
	
	
	
	//FONTS AND COLORS
	public static final Color TRANSPARENT = new Color(255, 0, 0, 0);
	public static final Color SEMI_TRANSPARENT = new Color(0.0f, 0.0f, 0.0f, 0.7f);
	public Font 
		POINT_FONT = fontManager.pointFont(),
		MAIN_MENU_FONT = fontManager.pixelFontMainMenu(),
		SELECTABLE_FONT = fontManager.pixelFontSelectables(),
		OPTIONS_FONT = fontManager.pixelFontOptions(),
		CONTROLS_FONT = fontManager.controlsFont(),
		NUMBERS_FONT = fontManager.numberFont(),
		INFO_FONT = fontManager.infoFont()
	;
	
	
	
	
	//MAIN MENU SELECTABLES
	public static final List<String> MAIN_MENU_STRINGS = new ArrayList<>(Arrays.asList("VS PLAYER", "VS CPU", "OPTIONS", "QUIT GAME"));
	static final String MAIN_TITLE = "POWER PONG";
	
	//PAUSE MENU SELECTABLES
	public static final List<String> PAUSE_MENU_STRINGS = new ArrayList<>(Arrays.asList("RESUME", "OPTIONS", "MAIN MENU"));
	static final String PAUSE_TITLE = "PAUSED";
	
	//OPTIONS MENU SELECTABLES
	public static final List<String> OPTIONS_MENU_STRINGS = new ArrayList<>(Arrays.asList("HOW TO PLAY", "CONTROLS", "POWERUPS", "BACK"));
	public static final List<String> CONTROLS_STRINGS = new ArrayList<>(Arrays.asList("A / Left Arrow", "D / Right Arrow", 
			"W / Up Arrow", "S / Down Arrow", "Spacebar", "Move Left", "Move Right", "Move Up", "Move Down", "Select"));
	static final String OPTIONS_TITLE = "OPTIONS";
	static final String GUI_TITLE = "GUI SCALE";

	
	public static final List<PowerUpEnum> POWERUP_LIST = new ArrayList<PowerUpEnum>(Arrays.asList(PowerUpEnum.values()));
	static final String POWERUP_TITLE = "POWERUPS";
	
	
	//FOR SETTING PLACEMENT OF MENU ITEMS
	static boolean firstIteration = false;
	
	
	
	@Override
	public void drawRectangle(Graphics2D graphics, Rectangle rect) {
	    graphics.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
		
	}
	
	@Override
	public void drawPlayer(Graphics2D graphics, Player p) {
		graphics.setColor(p.getCurrentColor());
		drawRectangle(graphics, p);
		
	}
	
	@Override
	public void drawBall(Graphics2D graphics, Circle circle) {
		
		graphics.setColor(circle.getCurrentColor());
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
	    		g.drawString(s, x - 1, y - 1);
	    	}
	    }
	    
	    
	}

	public void drawPauseMenu(Graphics2D g, int contentPaneWidth, int contentPaneHeight) {
		
		drawTransparentScreen(g, contentPaneWidth, contentPaneHeight);
		drawMenu(g, contentPaneWidth, contentPaneHeight, PAUSE_MENU_STRINGS, PAUSE_TITLE);
	    
	    
	}
	
	public void drawMainMenu(Graphics2D g, int contentPaneWidth, int contentPaneHeight) {
		
		drawMenu(g, contentPaneWidth, contentPaneHeight, MAIN_MENU_STRINGS, MAIN_TITLE);
	    
	    
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
	    		y += TEXT_SPACE;
	    	    g.drawString(s, x, y);
	    	}
	    	if (s == OPTIONS_MENU_STRINGS.get(APongController.currentSelection)) {
	    		g.drawString(s, x - 1, y - 1);
	    	}
	    }
	    
	    
	    //DRAWING TEXT IN RIGHT BOX
	    switch(OptionsMenuSelections.values()[APongController.currentSelection]) {
	    
	    case CONTROLS:
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
		    			y += TEXT_SPACE;
		    		}
		    	    g.drawString(s, x, y);
		    	}
		
		    	
		    	
		    }
	    	break;
	    	
	    case INFO:
	    	g.setFont(INFO_FONT);
		    fm = g.getFontMetrics(INFO_FONT);
		    
		    drawWrappedText(g, List.of(fontManager.loadFromFile()), rightX + CONTENT_OFFSET, rightY + TEXT_OFFSET, rightWidth, fm);
			
			break;
	    case POWERUPS:
	    	g.setFont(OPTIONS_FONT);
		    fm = g.getFontMetrics(OPTIONS_FONT);
		    
		    
		    //TITLE
		    x = rightX + (rightWidth / 2) - (fm.stringWidth(POWERUP_TITLE) / 2);
	    	y = rightY + POWERUP_MENU_OFFSET;
	    	g.drawString(POWERUP_TITLE, x, y);
	    	
	    	
	    	g.setFont(INFO_FONT);
	    	
	    	//LIST OF POWERUPS
	    	x = rightX + CONTENT_OFFSET;
	    	y += POWERUP_MENU_OFFSET;
	    	
	    	Font origFont = g.getFont();
	    	Font bigFont = g.getFont().deriveFont((float) (origFont.getSize() * 1.5));
	    	
	    	for (PowerUpEnum s : POWERUP_LIST) {
	    		String name = s.toString() + ": ";
	    		switch(s) {
	    		case INCREASE_SIZE:
	    			drawIncreaseSizeText(g, name, s.getDescription(), x, y, bigFont, origFont);
	    			break;
	    		case REVERSE_CONTROLS:
	    			g.setColor(ReverseControls.reverseColor);
	    			int wordWidth = g.getFontMetrics().stringWidth(name);
	    			g.drawString(name, x, y);
	    			g.setColor(Color.white);
	    			g.drawString(s.getDescription(), x + wordWidth, y);
	    			break;
	    		}
	    		
	    		y += TEXT_SPACE;
	    	}
	    	break;
	    case BACK:
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

	//HELPER METHOD FOR PRINTING LISTS
	private void drawWrappedText(Graphics2D g, List<String> textLines, int startX, int startY, int maxWidth, FontMetrics fm) {
	    int x = startX;
	    int y = startY;
	    int lineHeight = fm.getHeight() * LINE_HEIGHT;
	    
	    for (String text : textLines) {
	        String[] words = text.split(" ");
	        StringBuilder line = new StringBuilder();
	        
	        for (String word : words) {
	            String tempLine = line + word + " ";
	            if (fm.stringWidth(tempLine) > maxWidth - CONTENT_OFFSET) {
	                g.drawString(line.toString(), x, y);
	                line = new StringBuilder(word + " ");
	                y += lineHeight;
	            } else {
	                line = new StringBuilder(tempLine);
	            }
	        }
	        
	        g.drawString(line.toString(), x, y);
	        y += lineHeight;
	    }
	}
	
	//HELPER METHOD FOR PRINTING POWER UP TEXT FOR INCREASE SIZE 
	private void drawIncreaseSizeText(Graphics2D g, String firstWord, String description, int x, int y, Font largeFont, Font smallFont) {
	    // Set the font for the first word (larger size)
	    g.setFont(largeFont);

	    // Draw the first word
	    g.drawString(firstWord, x, y);

	    // Measure the width of the first word
	    int firstWordWidth = g.getFontMetrics().stringWidth(firstWord);

	    // Set the font for the description (smaller size)
	    g.setFont(smallFont);

	    // Draw the description next to the first word
	    g.drawString(description, x + firstWordWidth, y);
	}


}
