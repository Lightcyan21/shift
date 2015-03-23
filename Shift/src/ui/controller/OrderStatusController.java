package ui.controller;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import persistence.dao.impl.ApartmentDAO;
import persistence.entity.impl.Apartment;
import persistence.entity.impl.House;
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

		if (event.getEventId() == UI_EVENT.ORDER_SEND.ordinal()) {
			event.getSource();
			ApartmentDAO aptdao = new ApartmentDAO();
			Apartment apt = aptdao.getApartment("1.1.1");
			House house = apt.getHouse();
			int size = 0;
			switch ("Treppenreinigung") {
			case "Treppenreinigung":
				size = house.getStockwerke();
				break;
			case "Instandhaltung":
				size = house.getAnzahlWohnungen();
				break;
			case "Schlüsseldienst":
				size = 1;
				break;
			case "Installationen":
				size = house.getAnzahlWohnungen();
				break;
			case "Reparaturen":
				size = 1;
				break;
			case "Hecke schneiden":
				size = 1;
				break;
			default:
				return;
			}

		}
	}

}
