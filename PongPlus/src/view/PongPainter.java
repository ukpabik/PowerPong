package view;

import java.beans.PropertyChangeListener;

import listeners.PaintListener;

public interface PongPainter extends PropertyChangeListener{
	public void addPaintListener(PaintListener listener);
}
