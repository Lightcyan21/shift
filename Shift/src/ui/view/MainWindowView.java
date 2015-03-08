package ui.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JButton;

import ui.enums.UI_EVENT;

import components.ShiftFrame;
import components.ShiftPanel2;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;

public class MainWindowView extends AbstractView {

	public MainWindowView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {

		return null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

	}

	@Override
	protected void initUI() {

		// erzeugt neue Instanz des Shiftframes
		ShiftFrame sf = ShiftFrame.getInstance();

		// Hinzufuegen der UI Komponenten
		ShiftPanel2 mainWindowPanel = new ShiftPanel2();
		JButton button1 = new JButton("button1");
		JButton button2 = new JButton("button2");
		JButton button3 = new JButton("button3");
		JButton button4 = new JButton("button4");
		JButton button5 = new JButton("button5");

		// ActionListener hinzufügen
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BUTTON1.ordinal());
			}
		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BUTTON2.ordinal());
			}
		});
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BUTTON3.ordinal());
			}
		});
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BUTTON4.ordinal());
			}
		});
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BUTTON5.ordinal());
			}
		});
		
		// Hinzufuegen der Komponeten zum Panel
		mainWindowPanel.setLayout(new GridLayout(3, 2, 100, 100));
		mainWindowPanel.add(button1);
		mainWindowPanel.add(button2);
		mainWindowPanel.add(button3);
		mainWindowPanel.add(button4);
		mainWindowPanel.add(button5);

		// hinzufuegen zum eigentlichen Fenster
		sf.getContentpanel().add(mainWindowPanel);
		sf.validate();
	}

}
