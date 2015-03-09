package ui.controller;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import ui.enums.UI_EVENT;
import ui.model.ExposeModel;
import ui.view.ExposeView;

/**
 * hierbei handelt es sich um die Seite, die neue Expos�s darstellt
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