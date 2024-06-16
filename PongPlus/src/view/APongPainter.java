package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import factory.PongFactory;
import gui.GameDisplay;
import gui.GameState;
import listeners.PaintListener;

@SuppressWarnings("serial")
public class APongPainter extends Component implements PongPainter{
	protected List<PaintListener> paintListeners = new ArrayList<>();
	GameDisplay game = PongFactory.gameDisplayFactoryMethod();
	
	int contentPaneWidth, contentPaneHeight;
	
	
	public APongPainter() {
		setFocusable(true);
	}
	
	@Override
	public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        
        GameState currentState = game.getCurrentState();
        
        for (PaintListener p : paintListeners) {

        	switch(currentState) {
            case MAIN_MENU:
            	if (p instanceof MainMenuView) {
            		p.paint(g2);
            	}
            	break;
            case PLAYING:
            	if (!(p instanceof MainMenuView) && !(p instanceof PausedView)) {
            		p.paint(g2);
            	}
            	break;
    		case GAME_OVER:
    			break;
    		case PAUSED:
    			if (!(p instanceof MainMenuView)) {
                    p.paint(g2);
                }
    			break;
    		default:
    			break;
            }
        }
        
    }
	
	@Override
	public void setContentPaneDimensions(int width, int height) {
        this.contentPaneWidth = width;
        this.contentPaneHeight = height;
    }
	
	
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt);
	}

	@Override
	public void addPaintListener(PaintListener listener) {
		this.paintListeners.add(listener);
		
	}
	
	public List<PaintListener> getPaintListeners(){
		return paintListeners;
	}

}
