package ui.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import persistence.dao.impl.HouseDAO;
import persistence.entity.impl.House;
import ui.enums.UI_EVENT;
import util.SpringUtilities;

import components.Definitions;
import components.ShiftButton2;
import components.ShiftButtonBack;
import components.ShiftFrame;
import components.ShiftLabel;
import components.ShiftPanel2;
import components.ShiftTableEntry;

//github.com/Lightcyan21/shift.git

public class ExposeView extends AbstractView {

	private ShiftFrame frame;
	private ShiftPanel2 table;
	private int rows;
	private int cols = 9;
	private int initX = 10;
	private int initY = 0;
	private int xPad = 30;
	private int yPad = 0;
	private HashMap<String, House> entries;
	private ShiftLabel nr = new ShiftLabel("Nr.");
	private ShiftLabel street = new ShiftLabel("Straße");
	private ShiftLabel ort = new ShiftLabel("Ort");
	private ShiftLabel plz = new ShiftLabel("PLZ");
	private ShiftLabel area = new ShiftLabel(
			"<html><body>Grundstücks-<br>fläche</body></html>");
	private ShiftLabel garden = new ShiftLabel(
			"<html><body>Garten-<br>fläche</body></html>");
	private ShiftLabel wohn = new ShiftLabel("Wohnfläche");
	private ShiftLabel versicherung = new ShiftLabel("Versicherung");
	private ShiftLabel noversicherung = new ShiftLabel("keine Versicherung");

	public ExposeView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {
		entries.clear();
		rows = 1;
		headlinesSetzen();
		loadTable();
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

		initGlobals();

		// Gestalten des Panels
		ShiftPanel2 content = new ShiftPanel2();
		ShiftButtonBack button = new ShiftButtonBack();
		JScrollPane centerpanel = new JScrollPane(erzeugeTabelle());
		centerpanel.setOpaque(true);
		ShiftPanel2 south = new ShiftPanel2();

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
				// addRow(new House());
			}
		});

		content.setOpaque(true);
		content.setLayout(new BorderLayout());
		south.add(button);
		content.add(south, BorderLayout.SOUTH);
		content.add(centerpanel, BorderLayout.CENTER);

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(content, "expose");
		frame.getCardlayout().show(frame.getContentpanel(), "expose");
		frame.validate();

		// Tabelle laden
		loadTable();

	}

	private void initGlobals() {
		entries = new HashMap<String, House>();
	}

	@SuppressWarnings("unchecked")
	protected void deleteThisRow(int i) {

		table.removeAll();
		entries.remove(Integer.toString(i));

		// headlines setzen
		headlinesSetzen();
		rows = 1;
		HashMap<String, House> housescopy = (HashMap<String, House>) entries
				.clone();

		for (House house : housescopy.values()) {
			addRow(house);
		}

		System.out.println("tablecount:" + table.getComponentCount()
				+ " rows: " + rows + " cols:" + cols);
		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, xPad,
				yPad);
		table.validate();
		frame.repaint();
	}

	private void headlinesSetzen() {
		table.add(nr);
		table.add(street);
		table.add(ort);
		table.add(plz);
		table.add(area);
		table.add(garden);
		table.add(wohn);
		table.add(versicherung);
		table.add(noversicherung);
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

		int row = rows;

		entries.put(Integer.toString(rows), house);
		ShiftTableEntry entry = new ShiftTableEntry(Integer.toString(rows));
		ShiftTableEntry entry2 = new ShiftTableEntry(house.getStrasse());
		ShiftTableEntry entry3 = new ShiftTableEntry(house.getOrt());
		ShiftTableEntry entry4 = new ShiftTableEntry(house.getPlz());
		ShiftTableEntry entry5 = new ShiftTableEntry(Double.toString(house
				.getFlaeche()));
		ShiftTableEntry entry6 = new ShiftTableEntry(Double.toString(house
				.getGartenflaeche()));
		ShiftTableEntry entry65 = new ShiftTableEntry(Double.toString(house
				.getFlaeche() / house.getAnzahlWohnungen()));
		ShiftButton2 entry7 = new ShiftButton2("");
		entry7.setIcon(new ImageIcon("res/WohnungsInfo.png"));
		ShiftButton2 entry8 = new ShiftButton2("");
		entry8.setIcon(new ImageIcon("res/WohnungsInfo.png"));
		entry7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Versicherung angelegt");
				fireLocalUIEvent(this, UI_EVENT.PUSH_INSURANCE.ordinal(),
						house.getFlaeche());
				deleteThisRow(row);
			}
		});

		entry8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Keine Versicherung beauftragt");
				System.out.println("Lösche Reihe: " + row);
				deleteThisRow(row);
			}
		});

		table.add(entry);
		table.add(entry2);
		table.add(entry3);
		table.add(entry4);
		table.add(entry5);
		table.add(entry6);
		table.add(entry65);
		table.add(entry7);
		table.add(entry8);

		rows++;
		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, xPad,
				yPad);
		frame.validate();
	}

	private ShiftPanel2 erzeugeTabelle() {
		table = new ShiftPanel2();
		rows = 1;
		table.setLayout(new SpringLayout());

		headlinesSetzen();

		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, xPad,
				yPad);
		return table;
	}
}
