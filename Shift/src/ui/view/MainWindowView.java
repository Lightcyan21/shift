package ui.view;

import java.beans.PropertyChangeEvent;

import components.ShiftFrame;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;

public class MainWindowView extends AbstractView {

	public MainWindowView(IModel model) {
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
		ShiftFrame sf = new ShiftFrame();

	}

}
