package mvc.controller;

import java.beans.PropertyChangeListener;

import mvc.event.LocalUIEventListener;
import mvc.model.abstrct.AbstractModel;
import mvc.view.IView;


public interface IController<V extends IView, M extends AbstractModel> extends
		PropertyChangeListener, LocalUIEventListener {

	
	void addView(V view);
	
	void removeView(V view);

	
	void setModel(M Model);
}
