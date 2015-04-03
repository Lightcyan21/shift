package ui.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.*;

import javafx.scene.layout.Border;
import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import ui.enums.UI_EVENT;
import util.SpringUtilities;
import components.Definitions;
import components.ShiftButton;
import components.ShiftFrame;
import components.ShiftLabel;
import components.ShiftPanel2;

public class ExposeView extends AbstractView {

	private ShiftFrame frame;
	private ShiftPanel2 table;
	private int rows;
	private int cols;

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
		JScrollPane centerpanel = new JScrollPane(erzeugeTabelle());

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
				refresh();

			}
		});
		content.setLayout(new BorderLayout());
		content.add(button, BorderLayout.SOUTH);
		content.add(centerpanel, BorderLayout.CENTER);

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(content, "expose");
		frame.getCardlayout().show(frame.getContentpanel(), "expose");
		frame.validate();

	}

	protected void refresh() {
		table.add(new JLabel("Test"));
		table.add(new JLabel("Test2"));
		table.add(new JLabel("Test3"));
		table.add(new JLabel("Test4"));
		table.add(new JLabel("Test5"));
		table.add(new JLabel("Test6"));
		table.add(new JLabel("Test7"));
		table.add(new JLabel("Test8"));
		rows++;
		SpringUtilities.makeCompactGrid(table, rows, cols, 20, 5, 20, 20);
		frame.validate();
	}

	private ShiftPanel2 erzeugeTabelle() {
		table = new ShiftPanel2();
		rows = 1;
		cols = 8;
		table.setLayout(new SpringLayout());

		// headlines hinzufuegen
		ShiftLabel nr = new ShiftLabel("Nr.");
		ShiftLabel street = new ShiftLabel("Straße");
		ShiftLabel ort = new ShiftLabel("Ort");
		ShiftLabel plz = new ShiftLabel("PLZ");
		ShiftLabel area = new ShiftLabel("Grundstücksfläche");
		ShiftLabel garden = new ShiftLabel("Gartenfläche");

		// headlines setzen
		table.add(nr);
		table.add(street);
		table.add(ort);
		table.add(plz);
		table.add(area);
		table.add(garden);
		table.add(new JLabel(""));
		table.add(new JLabel(" "));

		SpringUtilities.makeCompactGrid(table, rows, cols, 20, 5, 20, 20);
		return table;
	}
}
