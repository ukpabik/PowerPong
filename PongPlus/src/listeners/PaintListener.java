package listeners;

import java.awt.Graphics2D;
import java.beans.PropertyChangeListener;

public interface PaintListener extends PropertyChangeListener{
	void paint(Graphics2D g);
}
