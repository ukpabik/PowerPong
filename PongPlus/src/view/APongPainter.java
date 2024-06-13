package view;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import listeners.PaintListener;

@SuppressWarnings("serial")
public class APongPainter extends Component implements PongPainter{
	protected List<PaintListener> paintListeners = new ArrayList<>();
	
	
	public APongPainter() {
		setFocusable(true);
	}
	
	@Override
	public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        
        for (PaintListener p : paintListeners) {

            p.paint(g2);
        }
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
