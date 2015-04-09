package ui.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import components.Definitions;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import ui.enums.UI_EVENT;
import ui.model.AccountingModel;
import ui.view.AccountingView;
import webservices.impl.BuchhaltungWS;
import webservices.impl.BuchhaltungWsImplService;

/**
 * hierbei handelt es sich um die Seite, die Informationen der Buchhaltung
 * darstellt
 * 
 * @author Lappy
 * 
 */
public class AccountingController extends
		AbstractController<AccountingView, AccountingModel> {

	private static AccountingController instance;

	public AccountingController(AccountingModel model, AccountingView view) {
		super(model, view);

	}

	@Override
	public void handleEvent(LocalUIEvent event) {
		if (event.getEventId() == UI_EVENT.PUSH_BACK_BUTTON.ordinal()) {
			System.out.println("--- Wechsle zum Hauptmenu");
			MainWindowController.getInstance();
		}
		if (event.getEventId() == UI_EVENT.PUSH_MAHNUNG.ordinal()) {
			String data = (String) event.getData();
			String vz = data.substring(0, 10);
			int rowdel = Integer.parseInt(data.substring(11));

			System.out.println("Mahnung senden...");
			BuchhaltungWsImplService bhservice = new BuchhaltungWsImplService();
			BuchhaltungWS bh = bhservice.getBuchhaltungWsImplPort();
			try {
				String result = bh.uebergabeMahnauftrag(vz);
				System.out.println(result);
				if (result != "Mahnauftrag erhalten") {
					JOptionPane.showMessageDialog(new JFrame(), result,
							"Fehler!", JOptionPane.ERROR_MESSAGE);
				} else {
					this.registeredViews.get(0).deleteThisRow(rowdel);
				}

			} catch (com.sun.xml.internal.ws.client.ClientTransportException e) {
				JOptionPane.showMessageDialog(new JFrame(),
						Definitions.NO_CONNECTION_BH,
						Definitions.ERROR_TITLE,
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static AccountingController getInstance() {
		if (instance == null) {
			AccountingModel model = new AccountingModel();
			AccountingView view = new AccountingView(model);
			instance = new AccountingController(model, view);
		} else {
			instance.registeredViews.get(0).getMainSurface();
		}
		return instance;
	}

}
