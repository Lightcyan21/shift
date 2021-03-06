package ui.controller;

import javax.swing.JFrame;
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

import components.Definitions;

/**
 * hierbei handelt es sich um die Seite, die den Status der Auftr�ge darstellt
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
			if (id.matches("[0-9]+")) {
				System.out.println("ID: " + id);
				Order order = orderdao.getById(Long.parseLong(id));
				if (order != null) {
					try {
						String state = gs.getState(order.getId());
						if (!state.equals("")) {
							registeredViews.get(0).showStatus(order.getId(),
									state);
						} else {
							registeredViews.get(0).incorrectInput();
						}

					} catch (com.sun.xml.internal.ws.client.ClientTransportException e) {
						System.out.println(e.getMessage());
						JOptionPane.showMessageDialog(new JFrame(),
								Definitions.NO_CONNECTION_GS,
								Definitions.ERROR_TITLE,
								JOptionPane.ERROR_MESSAGE);
						registeredViews.get(0).focus();
					}

				} else {
					registeredViews.get(0).incorrectInput();
				}
			} else {
				registeredViews.get(0).incorrectInputNumber();

			}
		}

	}
}
