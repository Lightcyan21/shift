package ui.controller;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import ui.enums.UI_EVENT;
import ui.model.OrderStatusModel;
import ui.view.OrderStatusView;

/**
 * hierbei handelt es sich um die Seite, die den Status der Aufträge darstellt
 * 
 * @author Lappy
 * 
 */
public class OrderStatusController extends
		AbstractController<OrderStatusView, OrderStatusModel> {

	private static OrderStatusController instance;

	public OrderStatusController(OrderStatusModel model, OrderStatusView view) {
		super(model, view);

	}

	public static OrderStatusController getInstance() {
		if (instance == null) {
			OrderStatusModel model = new OrderStatusModel();
			OrderStatusView view = new OrderStatusView(model);
			instance = new OrderStatusController(model, view);
		} else {
			instance.registeredViews.get(0).getMainSurface();

		}
		return instance;
	}

	@Override
	public void handleEvent(LocalUIEvent event) {
		if (event.getEventId() == UI_EVENT.PUSH_BACK_BUTTON.ordinal()) {
			System.out.println("--- Wechsle zum Hauptmenu");
			MainWindowController.getInstance();
		}
	}

}
