package ui.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import persistence.dao.impl.HouseDAO;
import persistence.dao.impl.OrderDAO;
import persistence.entity.impl.House;
import persistence.entity.impl.Order;
import ui.enums.UI_EVENT;
import ui.model.OrderWindowModel;
import ui.view.OrderWindowView;
import webservices.ServiceWS;
import webservices.ServiceWSImplService;

import com.sun.xml.internal.ws.client.ClientTransportException;
import components.Definitions;

/**
 * hierbei handelt es sich um die Seite, die Auftr�ge darstellt
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
				flaeche = (int) house.getFlaeche(); // in db ist Fl�che ein int.
													// evtl �ndern
			} else {
				flaeche = (int) house.getGartenflaeche(); // in db ist
															// gartenflaeche ein
															// int. evtl �ndern
			}
			long orderID = ord.getId(); // javadoc verlangt long als datentyp
			try {
				String result = gebaeude.sendOrderToFm(name, apartmentID, flaeche, orderID);
				ord.setDatum(result);
				orderdao.persist(ord);

			} catch (ClientTransportException e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(new JFrame(),
						Definitions.NO_CONNECTION_GS, Definitions.ERROR_TITLE,
						JOptionPane.ERROR_MESSAGE);
			}

		}
		if (event.getEventId() == UI_EVENT.REMOVE_ORDER.ordinal()) {
			System.out.println("--- Auftrag l�schen");

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
