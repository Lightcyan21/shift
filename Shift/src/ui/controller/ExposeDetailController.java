package ui.controller;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
import ui.model.ExposeDetailModel;
import ui.view.ExposeDetailView;
/**
 * hierbei handelt es sich um die Seite, die neue Exposés detailliert darstellt
 * @author Lappy
 *
 */
public class ExposeDetailController extends AbstractController<ExposeDetailView, ExposeDetailModel> {

	public ExposeDetailController(ExposeDetailModel model, ExposeDetailView view) {
		super(model, view);
		
	}

	@Override
	public void handleEvent(LocalUIEvent event) {
	
		
	}

}
