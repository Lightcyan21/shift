

package mvc.view.abstrct;

import java.util.ArrayList;
import java.util.List;

import mvc.event.LocalUIEvent;
import mvc.event.LocalUIEventListener;
import mvc.model.IModel;
import mvc.view.IView;

public abstract class AbstractView implements IView {
	protected List<LocalUIEventListener> listeners = new ArrayList<LocalUIEventListener>();
	protected IModel model;
	
	public AbstractView(IModel model){
		this.model = model;
	}
	
	@Override
	public void addLocalUIEventListener(LocalUIEventListener listener) {
		if (listeners.contains(listener))
			return;

		listeners.add(listener);
	}
	
	
	public void fireLocalUIEvent(Object source, int eventId, Object data) {

		LocalUIEvent event = new LocalUIEvent(source, eventId, data);

		for (LocalUIEventListener l : listeners)
			l.handleEvent(event);
	}
	

	public void fireLocalUIEvent(Object source, int eventId) {
		this.fireLocalUIEvent(source, eventId, null);
	}


	protected abstract void initUI();
	

	@Override
	public void removeLocalUIEventListener(LocalUIEventListener listener) {
		listeners.remove(listener);
	}
}
