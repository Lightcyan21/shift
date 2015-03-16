
package mvc.model.abstrct;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import mvc.model.IModel;

public abstract class AbstractModel implements IModel {
	protected PropertyChangeSupport propertyChangeSupport;

	public AbstractModel() {
		initDefault();
		this.propertyChangeSupport = new PropertyChangeSupport(this);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		propertyChangeSupport.firePropertyChange(propertyName, oldValue,
				newValue);
	}

	abstract protected void initDefault();


	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
}
