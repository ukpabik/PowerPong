package listeners;

import java.beans.PropertyChangeListener;
import java.util.List;

public interface PropertyRegisterer {
	
	public void addPropertyChangeListener(PropertyChangeListener arg0);

	public List<PropertyChangeListener> getPropertyChangeListeners();
}
