package ui.controller;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import ui.enums.UI_EVENT;
import ui.model.MainWindowModel;
import ui.view.MainWindowView;

import components.Definitions;
import components.ShiftFrame;

public class MainWindowController extends
		AbstractController<MainWindowView, MainWindowModel> {
	public static MainWindowController instance;
	public static ShiftFrame frame;

	public MainWindowController(MainWindowModel model, MainWindowView view) {
		super(model, view);
	}

	public static MainWindowController getInstance() {
		if (instance == null) {
			MainWindowModel model = new MainWindowModel();
			MainWindowView view = new MainWindowView(model);
			instance = new MainWindowController(model, view);
			frame = ShiftFrame.getInstance();
		} else {
			instance.registeredViews.get(0).getMainSurface();

		}
		return instance;
	}

	@Override
	public void handleEvent(LocalUIEvent event) {
		String debug = "--- Wechsle zu ";
		if (event.getEventId() == UI_EVENT.ORDERS.ordinal()) {
			System.out.println(debug + Definitions.ORDERS);
			OrderWindowController.getInstance();
		}

		if (event.getEventId() == UI_EVENT.CHECK_STATUS.ordinal()) {
			System.out.println(debug + Definitions.CHECK_STATUS);
			OrderStatusController.getInstance();
		}

		if (event.getEventId() == UI_EVENT.HOUSES.ordinal()) {

			System.out.println(debug + Definitions.HOUSES);
			ExposeController.getInstance();
		}

		if (event.getEventId() == UI_EVENT.SEARCH.ordinal()) {
			System.out.println(debug + Definitions.SEARCH);
			AptRequestController.getInstance();
		}

		if (event.getEventId() == UI_EVENT.INFO.ordinal()) {
			System.out.println(debug + Definitions.INFO);
			AccountingController.getInstance();
		}

	}
}
