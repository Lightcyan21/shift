package ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import persistence.dao.impl.HouseDAO;
import persistence.entity.impl.House;
import util.SpringUtilities;

import components.Definitions;
import components.ShiftButton;
import components.ShiftButton2;
import components.ShiftButtonBack;
import components.ShiftFrame;
import components.ShiftLabel;
import components.ShiftPanel2;
import components.ShiftTableEntry;

public class ExposeView extends AbstractView {

	private ShiftFrame frame;
	private ShiftPanel2 table;
	private int rows;
	private int cols;
	private List<Component> tableentries;
	private int initX = 100;
	private int initY = 50;

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
		ShiftButtonBack button = new ShiftButtonBack();
		JScrollPane centerpanel = new JScrollPane(erzeugeTabelle());
		ShiftButton remove = new ShiftButton("Remove");
		ShiftPanel2 south = new ShiftPanel2();

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
				addRow();

			}
		});
		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});
		tableentries = new ArrayList<Component>();
		content.setLayout(new BorderLayout());
		south.add(button);
		south.add(remove);
		content.add(south, BorderLayout.SOUTH);
		content.add(centerpanel, BorderLayout.CENTER);

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(content, "expose");
		frame.getCardlayout().show(frame.getContentpanel(), "expose");
		frame.validate();
		// Tabelle laden
		// loadTable();

	}

	protected void remove() {
		table.remove(tableentries.get(0));
		table.remove(tableentries.get(1));
		table.remove(tableentries.get(2));
		table.remove(tableentries.get(3));
		table.remove(tableentries.get(4));
		table.remove(tableentries.get(5));
		table.remove(tableentries.get(6));
		table.remove(tableentries.get(7));

		rows--;
		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, 20, 20);
		frame.validate();
	}

	private void addRow() {

		ShiftTableEntry entry = new ShiftTableEntry(Integer.toString(rows));
		ShiftTableEntry entry2 = new ShiftTableEntry("house.getStrasse()");
		ShiftTableEntry entry3 = new ShiftTableEntry("house.getOrt()");
		ShiftTableEntry entry4 = new ShiftTableEntry("house.getPlz()");
		ShiftTableEntry entry5 = new ShiftTableEntry("Double.toString(house");
		ShiftTableEntry entry6 = new ShiftTableEntry("Double.toString(house");
		ShiftButton2 entry7 = new ShiftButton2("Versicherung");
		entry7.setIcon(new ImageIcon("res/WohnungsInfo.png"));
		ShiftButton2 entry8 = new ShiftButton2("Versicherung");
		entry8.setIcon(new ImageIcon("res/WohnungsInfo.png"));
		tableentries.add(entry);
		tableentries.add(entry2);
		tableentries.add(entry3);
		tableentries.add(entry4);
		tableentries.add(entry5);
		tableentries.add(entry6);
		tableentries.add(entry7);
		tableentries.add(entry8);
		table.add(entry);
		table.add(entry2);
		table.add(entry3);
		table.add(entry4);
		table.add(entry5);
		table.add(entry6);
		table.add(entry7);
		table.add(entry8);

		rows++;
		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, 20, 20);
		frame.validate();
	}

	private void loadTable() {
		HouseDAO housedao = new HouseDAO();
		List<House> houselist = housedao.findAll();
		for (House house : houselist) {
			if (!house.isSeen()) {
				addRow(house);
			}
		}
	}

	protected void addRow(House house) {

		ShiftTableEntry entry = new ShiftTableEntry(Integer.toString(rows));
		ShiftTableEntry entry2 = new ShiftTableEntry(house.getStrasse());
		ShiftTableEntry entry3 = new ShiftTableEntry(house.getOrt());
		ShiftTableEntry entry4 = new ShiftTableEntry(house.getPlz());
		ShiftTableEntry entry5 = new ShiftTableEntry(Double.toString(house
				.getFlaeche()));
		ShiftTableEntry entry6 = new ShiftTableEntry(Double.toString(house
				.getGartenflaeche()));
		ShiftButton2 entry7 = new ShiftButton2("Versicherung");
		entry7.setIcon(new ImageIcon("res/WohnungsInfo.png"));
		ShiftButton2 entry8 = new ShiftButton2("Versicherung");
		entry8.setIcon(new ImageIcon("res/WohnungsInfo.png"));

		table.add(entry);
		table.add(entry2);
		table.add(entry3);
		table.add(entry4);
		table.add(entry5);
		table.add(entry6);
		table.add(entry7);
		table.add(entry8);

		rows++;
		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, 20, 20);
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

		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, 20, 20);
		return table;
	}
}
