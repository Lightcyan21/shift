package ui.view;

import java.beans.PropertyChangeEvent;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;

public class OrderStatusView extends AbstractView {

	public OrderStatusView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {

		return null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

	}

	@Override
	protected void initUI() {

	}

}
