package ui.controller;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
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

	public AptRequestController(AptRequestModel model, AptRequestView view) {
		super(model, view);

	}

	@Override
	public void handleEvent(LocalUIEvent event) {

	}

	public static AptRequestController getInstance() {
		// TODO Instanziierung
		return null;
	}

}
