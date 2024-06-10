package listeners;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public interface Listener{
	
	
	public void addPropertyChangeListener(PropertyChangeListener listener);
	public List<PropertyChangeListener> getPropertyChangeListeners();
	public void notifyAllListeners(PropertyChangeEvent event);	
}
