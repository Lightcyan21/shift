package ui.controller;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import ui.enums.UI_EVENT;
import ui.model.AccountingModel;
import ui.view.AccountingView;

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
