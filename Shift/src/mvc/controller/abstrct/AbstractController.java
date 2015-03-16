

package mvc.controller.abstrct;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import mvc.controller.IController;
import mvc.model.abstrct.AbstractModel;
import mvc.view.IView;


public abstract class AbstractController<V extends IView, M extends AbstractModel>
		implements IController<V, M>, PropertyChangeListener {

	public M registeredModel;
	protected List<V> registeredViews;
	

	public AbstractController(M model, V view) {
		this.registeredViews = new ArrayList<V>();

		setModel(model);
		addView(view);
	}

	
	@Override
	public void addView(V view) {
		if (view == null)
			return;

		registeredViews.add(view);
		view.addLocalUIEventListener(this);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		if (evt == null)
			return;
		for (V view : registeredViews) {
			view.propertyChange(evt);
		}
	}

	@Override
	public void removeView(V view) {
		if (view == null)
			return;

		registeredViews.remove(view);
		view.removeLocalUIEventListener(this);
	}

	@Override
	public void setModel(M model) {
		if (model == null)
			return;

		this.registeredModel = model;
		model.addPropertyChangeListener(this);
	}

	protected void setModelProperty(String propertyName, Object newValue) {

		try {
			Method method = registeredModel.getClass().getMethod(
					"set" + propertyName, new Class[] { newValue.getClass() }

			);
			method.invoke(registeredModel, newValue);

		} catch (Exception ex) {
		
		}
	}
}
