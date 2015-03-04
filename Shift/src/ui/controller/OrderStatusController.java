package ui.controller;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
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

	public OrderStatusController(OrderStatusModel model, OrderStatusView view) {
		super(model, view);

	}

	@Override
	public void handleEvent(LocalUIEvent event) {

	}

}
