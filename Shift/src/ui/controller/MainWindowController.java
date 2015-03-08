package ui.controller;

import ui.enums.UI_EVENT;
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
		if (event.getEventId() == UI_EVENT.PUSH_BUTTON1.ordinal()) {
			System.out.println("Button1");
		}

		if (event.getEventId() == UI_EVENT.PUSH_BUTTON2.ordinal()) {
			System.out.println("Button2");
		}

		if (event.getEventId() == UI_EVENT.PUSH_BUTTON3.ordinal()) {
			System.out.println("Button3");
		}

		if (event.getEventId() == UI_EVENT.PUSH_BUTTON4.ordinal()) {
			System.out.println("Button4");
		}

		if (event.getEventId() == UI_EVENT.PUSH_BUTTON5.ordinal()) {
			System.out.println("Button5");
		}

	}

}
