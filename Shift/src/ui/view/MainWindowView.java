package ui.view;

import java.awt.GridLayout;
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

public class MainWindowView extends AbstractView {
	private ShiftFrame frame;

	public MainWindowView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {
		frame = ShiftFrame.getInstance();
		frame.getCardlayout().show(frame.getContentpanel(), "main");
		frame.setHeadline(Definitions.TITLE);
		frame.validate();
		return null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

	}

	@Override
	protected void initUI() {

		// erzeugt neue Instanz des Shiftframes
		frame = ShiftFrame.getInstance();
		frame.setHeadline(Definitions.TITLE);

		// Hinzufuegen der UI Komponenten
		ShiftPanel2 mainWindowPanel = new ShiftPanel2();
		ShiftButton orders = new ShiftButton(Definitions.ORDERS);
		ShiftButton check_status = new ShiftButton(Definitions.CHECK_STATUS);
		ShiftButton houses = new ShiftButton(Definitions.HOUSES);
		ShiftButton search = new ShiftButton(Definitions.SEARCH);
		ShiftButton info = new ShiftButton(Definitions.INFO);

		// ActionListener hinzufügen
		orders.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.ORDERS.ordinal());
			}
		});
		check_status.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.CHECK_STATUS.ordinal());
			}
		});
		houses.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.HOUSES.ordinal());
			}
		});
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.SEARCH.ordinal());
			}
		});
		info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.INFO.ordinal());
			}
		});

		// Hinzufuegen der Komponeten zum Panel
		mainWindowPanel.setLayout(new GridLayout(3, 2, 100, 100));
		mainWindowPanel.add(orders);
		mainWindowPanel.add(check_status);
		mainWindowPanel.add(houses);
		mainWindowPanel.add(search);
		mainWindowPanel.add(info);

		// hinzufuegen zum eigentlichen Fenster
		frame.getContentpanel().add(mainWindowPanel, "main");
		frame.validate();
	}

}
