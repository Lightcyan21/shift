package ui.controller;

import persistence.dao.impl.OrderDAO;
import persistence.entity.impl.Order;
import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import ui.enums.UI_EVENT;
import ui.model.OrderWindowModel;
import ui.view.OrderWindowView;
import webservices.ServiceWS;
import webservices.ServiceWSImplService;

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
		if (event.getEventId() == UI_EVENT.AUFTRAG_ERTEILEN.ordinal()) {
			System.out.println("Auftrag erhalten");
			System.out.println(event.getData());
			ServiceWSImplService gsservice = new ServiceWSImplService();
			ServiceWS gs = gsservice.getServiceWSImplPort();
			OrderDAO orderdao = new OrderDAO();
			Order order = orderdao.create();
			System.out.println(gs.sendOrderToFm(event.getData().toString(),
					"1.1.1", 1, order.getId()));
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
