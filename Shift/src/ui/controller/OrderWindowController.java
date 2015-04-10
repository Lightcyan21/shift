package ui.controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import persistence.dao.impl.OrderDAO;
import persistence.entity.impl.Order;
import ui.enums.UI_EVENT;
import ui.model.OrderWindowModel;
import ui.view.OrderWindowView;
import webservices.ServiceWS;
import webservices.ServiceWSImplService;

import com.sun.xml.internal.ws.client.ClientTransportException;
import com.sun.xml.internal.ws.fault.ServerSOAPFaultException;

import components.Definitions;
import components.ShiftLabel;

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
			ServiceWSImplService gebaeudeservice = new ServiceWSImplService();
			ServiceWS gebaeude = gebaeudeservice.getServiceWSImplPort();
			OrderDAO orderdao = new OrderDAO();
			@SuppressWarnings("unchecked")
			List<Object> objectlist = (List<Object>) event.getData();
			Order ord = (Order) objectlist.get(0);
			ShiftLabel entry5 = (ShiftLabel) objectlist.get(1);
			ShiftLabel entry6 = (ShiftLabel) objectlist.get(2);
			String name = ord.getJobName();
			String apartmentID = ord.getWohnungsID();
			int flaeche = 1;
			// Instandhaltung
			// Schlüsseldienst
			// Installationen
			// Reparaturen
			// Hecke schneiden

			long orderID = ord.getId(); // javadoc verlangt long als datentyp
			try {

				String result = gebaeude.sendOrderToFm(name, apartmentID,
						flaeche, orderID);
				if (result.equals("")) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Fehlerhafte Parameter für Auftragsübergabe");
				} else {
					System.out.println("Ausführungsdatum: " + result);
					ord.setDatum(result);
					ord.setStatusBestaetigung(true);
					orderdao.persist(ord);
					registeredViews.get(0).setzeBestaetigung(entry5, entry6);
				}

			} catch (ClientTransportException | ServerSOAPFaultException e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(new JFrame(),
						Definitions.NO_CONNECTION_GS, Definitions.ERROR_TITLE,
						JOptionPane.ERROR_MESSAGE);
			}

		}
		if (event.getEventId() == UI_EVENT.REMOVE_ORDER.ordinal()) {
			System.out.println("--- Auftrag löschen");

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
