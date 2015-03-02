package ui.controller;

import ui.model.MainWindowModel;
import ui.view.MainWindowView;
import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;

public class MainWindowController extends
		AbstractController<MainWindowView, MainWindowModel> {
	public static MainWindowController instance;

	public MainWindowController(MainWindowModel model, MainWindowView view) {
		super(model, view);
	}

	public static MainWindowController getInstance() {
		if (instance == null) {
			MainWindowModel model = new MainWindowModel();
			MainWindowView view = new MainWindowView(model);
			instance = new MainWindowController(model, view);
		}
		return instance;
	}

	@Override
	public void handleEvent(LocalUIEvent event) {
		// TODO Auto-generated method stub

	}

}
