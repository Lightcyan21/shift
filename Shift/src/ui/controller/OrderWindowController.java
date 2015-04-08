package ui.controller;

import persistence.dao.impl.HouseDAO;
import persistence.dao.impl.OrderDAO;
import persistence.entity.impl.House;
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
		if (event.getEventId() == UI_EVENT.AUFTRAG_WEITERLEITEN.ordinal()) {
			System.out.println("--- Auftrag wird weiter geleitet");
			Order ord = (Order) event.getData();
			ServiceWSImplService gebaeudeservice = new ServiceWSImplService();
			ServiceWS gebaeude = gebaeudeservice.getServiceWSImplPort();
			OrderDAO orderdao = new OrderDAO();
			String id = (String) event.getData();
			System.out.println("ID: " + id);
			ord = orderdao.getById(Long.parseLong(id));
			String name = ord.getJobName();
			String apartmentID = ord.getWohnungsID();
			String[] arr = apartmentID.split("\\.");
			Long houseID = Long.parseLong(arr[0]);
			HouseDAO housedao = new HouseDAO();
			House house = housedao.getById(houseID);
			int flaeche = 0;
			if (ord.getJobName() == "Test") {
				flaeche = (int) house.getFlaeche(); // in db ist Fläche ein int.
													// evtl ändern
			} else {
				flaeche = (int) house.getGartenflaeche(); // in db ist
															// gartenflaeche ein
															// int. evtl ändern
			}
			long orderID = ord.getId(); // javadoc verlangt long als datentyp
			gebaeude.sendOrderToFm(name, apartmentID, flaeche, orderID);

		}
		if (event.getEventId() == UI_EVENT.RECHNUNG_SENDEN.ordinal()) {
			System.out.println("--- Rechnung senden");
			OrderDAO ordD = new OrderDAO();
			Order ord = new Order();
			ord.isStatusRechnung();

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
