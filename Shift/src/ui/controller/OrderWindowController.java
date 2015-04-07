package ui.controller;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import ui.enums.UI_EVENT;
import ui.model.OrderWindowModel;
import ui.view.OrderWindowView;

/**
 * hierbei handelt es sich um die Seite, die Aufträge darstellt
 * 
 * @author Lappy
 * 
 */
public class OrderWindowController extends
		AbstractController<OrderWindowView, OrderWindowModel> {

	private static OrderWindowController instance;

	public OrderWindowController(OrderWindowModel model, OrderWindowView view) {
		super(model, view);

	}

	@Override
	public void handleEvent(LocalUIEvent event) {
		if (event.getEventId() == UI_EVENT.PUSH_BACK_BUTTON.ordinal()) {
			System.out.println("--- Wechsle zum Hauptmenu");
			MainWindowController.getInstance();
		}
		
	}

	public static OrderWindowController getInstance() {
		if (instance == null) {
			OrderWindowModel model = new OrderWindowModel();
			OrderWindowView view = new OrderWindowView(model);
			instance = new OrderWindowController(model, view);
		} else {
			instance.registeredViews.get(0).getMainSurface();

		}
		return instance;
	}

}
