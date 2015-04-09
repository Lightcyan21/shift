package ui.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.sun.xml.internal.ws.client.ClientTransportException;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import persistence.dao.impl.InsuranceDAO;
import persistence.entity.impl.Insurance;
import ui.enums.UI_EVENT;
import ui.model.ExposeModel;
import ui.view.ExposeView;
import webservices.impl.BVWSImplService;
import webservices.impl.BVWebService;
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
			Double area = (Double) event.getData();
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
					}
					registeredViews.get(0).deleteThisRow(i);
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
