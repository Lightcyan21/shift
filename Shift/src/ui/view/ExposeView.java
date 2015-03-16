package ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import ui.enums.UI_EVENT;

import components.Definitions;
import components.ShiftButton;
import components.ShiftFrame;
import components.ShiftPanel2;

public class ExposeView extends AbstractView {

	private ShiftFrame frame;

	public ExposeView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {

		frame.getCardlayout().show(frame.getContentpanel(), "expose");
		frame.setHeadline(Definitions.HOUSES);
		frame.validate();
		return null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

	}

	@Override
	protected void initUI() {
		// initialisieren der Variablen
		frame = ShiftFrame.getInstance();
		frame.setHeadline(Definitions.HOUSES);

		// Gestalten des Panels
		ShiftPanel2 content = new ShiftPanel2();
		ShiftButton button = new ShiftButton("Back");
		content.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
			}
		});

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(content, "expose");
		frame.getCardlayout().show(frame.getContentpanel(), "expose");
		frame.validate();

	}

}
