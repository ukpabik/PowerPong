package listeners;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class PropertyListener implements Listener{

	protected List<PropertyChangeListener> propertyChangeListeners = new ArrayList<>();
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		this.propertyChangeListeners.add(arg0);
		
	}
	
	@Override
	public void notifyAllListeners(PropertyChangeEvent event) {
	      for (PropertyChangeListener listener : propertyChangeListeners) {
	          listener.propertyChange(event);
	      }
	}
	
	@Override
	public List<PropertyChangeListener> getPropertyChangeListeners() {
		return this.propertyChangeListeners;
	}

	
	
	
	
	

}
