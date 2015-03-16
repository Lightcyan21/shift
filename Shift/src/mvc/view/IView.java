package mvc.view;

import java.beans.PropertyChangeListener;

import mvc.event.LocalUIEventListener;


public interface IView extends PropertyChangeListener {

	void addLocalUIEventListener(LocalUIEventListener listener);

	Object getMainSurface();


	void removeLocalUIEventListener(LocalUIEventListener listener);
}
