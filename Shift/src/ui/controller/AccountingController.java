package ui.controller;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
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

	public AccountingController(AccountingModel model, AccountingView view) {
		super(model, view);

	}

	@Override
	public void handleEvent(LocalUIEvent event) {

	}

	public static AccountingController getInstance() {
		// TODO Instanziierung
		return null;
	}

}
