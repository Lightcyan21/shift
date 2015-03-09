package ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import ui.enums.UI_EVENT;

import components.ShiftFrame;
import components.ShiftPanel2;

public class OrderWindowView extends AbstractView {

	private ShiftFrame frame;

	public OrderWindowView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {
		frame.getCardlayout().show(frame.getContentpanel(), "page");
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

		// Gestalten des Panels
		ShiftPanel2 sp2 = new ShiftPanel2();
		JButton button = new JButton("Back");
		sp2.add(button);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
			}
		});

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(sp2, "page");
		frame.getCardlayout().show(frame.getContentpanel(), "page");
		frame.validate();
	}
}
