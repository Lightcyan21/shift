package ui.controller;

import ui.model.OrderWindowModel;
import ui.view.OrderWindowView;
import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;

/**
 * hierbei handelt es sich um die Seite, die Aufträge darstellt
 * 
 * @author Lappy
 * 
 */
public class OrderWindowController extends
		AbstractController<OrderWindowView, OrderWindowModel> {

	public OrderWindowController(OrderWindowModel model, OrderWindowView view) {
		super(model, view);

	}

	@Override
	public void handleEvent(LocalUIEvent event) {

	}

}
