package ui.controller;

import javax.swing.JOptionPane;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import persistence.dao.impl.OrderDAO;
import persistence.entity.impl.Order;
import ui.enums.UI_EVENT;
import ui.model.OrderStatusModel;
import ui.view.OrderStatusView;
import webservices.ServiceWS;
import webservices.ServiceWSImplService;

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

		if (event.getEventId() == UI_EVENT.SEARCH.ordinal()) {
			System.out.println("starte suche...");
			ServiceWSImplService gsservice = new ServiceWSImplService();
			ServiceWS gs = gsservice.getServiceWSImplPort();
			OrderDAO orderdao = new OrderDAO();
			String id = (String) event.getData();
			System.out.println("ID: " + id);
			Order order = orderdao.getById(Long.parseLong(id));
			if (order != null) {
				System.out.println("test" + gs.getState(order.getId())+"test");
				if (gs.getState(order.getId()) != "") {
					registeredViews.get(0).showStatus(order.getId(),
							gs.getState(order.getId()));
				}else{
				registeredViews.get(0).incorrectInput();
				}

			} else {
				registeredViews.get(0).incorrectInput();
			}
		}
	}
}
