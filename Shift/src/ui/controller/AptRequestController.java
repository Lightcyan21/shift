package ui.controller;

import mvc.controller.abstrct.AbstractController;
import mvc.event.LocalUIEvent;
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
