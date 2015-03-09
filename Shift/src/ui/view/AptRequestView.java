package ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;

import ui.enums.UI_EVENT;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;

import components.Definitions;
import components.ShiftFrame;
import components.ShiftPanel2;

public class AptRequestView extends AbstractView {

	private ShiftFrame frame;

	public AptRequestView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {

		frame.getCardlayout().show(frame.getContentpanel(), "search");
		frame.setHeadline(Definitions.SEARCH);
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
		frame.setHeadline(Definitions.SEARCH);

		// Gestalten des Panels
		ShiftPanel2 content = new ShiftPanel2();
		JButton button = new JButton("Back");
		content.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
			}
		});

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(content, "search");
		frame.getCardlayout().show(frame.getContentpanel(), "search");
		frame.validate();

	}

}