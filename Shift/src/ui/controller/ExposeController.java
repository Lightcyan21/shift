package ui.controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import persistence.dao.impl.HouseDAO;
import persistence.dao.impl.InsuranceDAO;
import persistence.entity.impl.House;
import persistence.entity.impl.Insurance;
import ui.enums.UI_EVENT;
import ui.model.ExposeModel;
import ui.view.ExposeView;
import webservices.impl.BVWSImplService;
import webservices.impl.BVWebService;

import com.sun.xml.internal.ws.client.ClientTransportException;
import components.Definitions;

/**
 * hierbei handelt es sich um die Seite, die neue Exposés darstellt
 * 
 * @author Lappy
 * 
 */
public class ExposeController extends
		AbstractController<ExposeView, ExposeModel> {

	private static ExposeController instance;

	public ExposeController(ExposeModel model, ExposeView view) {
		super(model, view);

	}

	@Override
	public void handleEvent(LocalUIEvent event) {
		if (event.getEventId() == UI_EVENT.PUSH_BACK_BUTTON.ordinal()) {
			System.out.println("--- Wechsle zum Hauptmenu");
			MainWindowController.getInstance();
		}
		if (event.getEventId() == UI_EVENT.PUSH_INSURANCE.ordinal()) {
			BVWSImplService bankservice = new BVWSImplService();
			BVWebService bank = bankservice.getBVWSImplPort();
			if(!(event.getData() instanceof List<?>)){
				JOptionPane.showMessageDialog(new JFrame(), "Fehler beim Casten der Objekte");
				return;
			}
			List<Object> data =  (List<Object>) event.getData();
			Double area = (Double) data.get(0);
			Integer rowdel = (Integer) data.get(1);
			Long id = (Long)data.get(2);
			
			try {
				Double betrag = bank.getGebVersicherung(Definitions.iban,
						area.floatValue());
				if (betrag == 0) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Fehler beim Eintragen der Versicherung",
							Definitions.ERROR_TITLE, JOptionPane.ERROR_MESSAGE,
							null);
				} else {
					InsuranceDAO insudao = new InsuranceDAO();
					Insurance insu = insudao.create();
					insu.setBetrag(betrag);
					if (insudao.persist(insu)) {
						System.out.println("Versicherung gespeichert!");
						registeredViews.get(0).deleteThisRow(rowdel);
						HouseDAO housedao = new HouseDAO();
						House house = housedao.getById(id);
						house.setSeen(true);
						housedao.persist(house);

					} else {

						JOptionPane.showMessageDialog(new JFrame(),
								"Fehler beim Speichern der Versicherung",
								Definitions.ERROR_TITLE,
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (ClientTransportException e) {
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(new JFrame(),
						Definitions.NO_CONNECTION_BANK,
						Definitions.ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	public static ExposeController getInstance() {
		if (instance == null) {
			ExposeModel model = new ExposeModel();
			ExposeView view = new ExposeView(model);
			instance = new ExposeController(model, view);
		} else {
			instance.registeredViews.get(0).getMainSurface();

		}
		return instance;
	}

}
