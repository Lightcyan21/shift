package ui.controller;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import ui.model.ExposeModel;
import ui.view.ExposeView;
/**
 * hierbei handelt es sich um die Seite, die neue Exposés darstellt
 * @author Lappy
 *
 */
public class ExposeController extends AbstractController<ExposeView, ExposeModel> {

	public ExposeController(ExposeModel model, ExposeView view) {
		super(model, view);
	
	}

	@Override
	public void handleEvent(LocalUIEvent event) {

		
	}

}
