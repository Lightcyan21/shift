package ui.controller;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import persistence.dao.impl.HouseDAO;
import persistence.entity.impl.Apartment;
import persistence.entity.impl.House;
import ui.enums.UI_EVENT;
import ui.model.AptRequestModel;
import ui.view.AptRequestView;

/**
 * hierbei handelt es sich um die Seite, auf der Details zu den Wohnungen
 * abgerufen werden kann
 * 
 * @author Lappy
 * 
 */
public class AptRequestController extends
		AbstractController<AptRequestView, AptRequestModel> {

	private static AptRequestController instance;

	public AptRequestController(AptRequestModel model, AptRequestView view) {
		super(model, view);

	}

	@Override
	public void handleEvent(LocalUIEvent event) {
		if (event.getEventId() == UI_EVENT.PUSH_BACK_BUTTON.ordinal()) {
			System.out.println("--- Wechsle zum Hauptmenu");
			MainWindowController.getInstance();
		}
		if (event.getEventId() == UI_EVENT.PUSH_DETAILS.ordinal()) {
			System.out.println("---Details zeigen");
			Apartment apt = (Apartment) event.getData();
			System.out.println("Zeige Informationen zu Apartment "+apt.getAptID()+" an...");
			String[] arr = apt.getAptID().split("\\.");

			HouseDAO housedao = new HouseDAO();
			System.out.println(Long.parseLong(arr[0]));
			House house = housedao.getById(Long.parseLong(arr[0]));

			JLabel message = new JLabel("<html><body>Apartment: "
					+ apt.getAptID() + "<br><br>Anzahl Mieter: "
					+ apt.getMieteranzahl() + "<br>Wohnfl‰che: "
					+ apt.getWohnflaeche() + "<br>Anzahl Zimmer: "
					+ apt.getZimmeranzahl() + "<br>PLZ: " + house.getPlz()
					+ "<br>Ort: " + house.getOrt() + "<br>Straﬂe: "
					+ house.getStrasse() + " " + house.getHausnr());
			JOptionPane.showMessageDialog(new JFrame(), message,
					"Wohnungsinformationen", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public static AptRequestController getInstance() {
		if (instance == null) {
			AptRequestModel model = new AptRequestModel();
			AptRequestView view = new AptRequestView(model);
			instance = new AptRequestController(model, view);
		} else {
			instance.registeredViews.get(0).getMainSurface();

		}
		return instance;
	}

}
