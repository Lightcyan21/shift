package ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import persistence.dao.impl.HouseDAO;
import persistence.entity.impl.House;
import ui.enums.UI_EVENT;
import util.SpringTable;
import util.SpringUtilities;
import components.Definitions;
import components.ShiftButton2;
import components.ShiftButtonBack;
import components.ShiftFrame;
import components.ShiftLabel;
import components.ShiftPanel2;
import components.ShiftTableEntry;

//github.com/Lightcyan21/shift.git

public class ExposeView extends AbstractView implements SpringTable {

	private ShiftFrame frame;
	private ShiftPanel2 table;
	private int rows;
	private int cols = 11;
	private int initX = Definitions.initX;
	private int initY = Definitions.initY;
	private int xPad = Definitions.xPad;
	private int yPad = Definitions.yPad;
	private HashMap<String, House> entries;
	private ShiftLabel nr = new ShiftLabel("Nr.");
	private ShiftLabel houseid = new ShiftLabel("HausID");
	private ShiftLabel street = new ShiftLabel("Straße");
	private ShiftLabel hausnr = new ShiftLabel("Hausnr.");
	private ShiftLabel ort = new ShiftLabel("Ort");
	private ShiftLabel plz = new ShiftLabel("PLZ");
	private ShiftLabel area = new ShiftLabel(
			"<html><body>Grundstücks-<br>fläche</body></html>");
	private ShiftLabel garden = new ShiftLabel(
			"<html><body>Garten-<br>fläche</body></html>");
	private ShiftLabel wohn = new ShiftLabel("Wohnfläche");
	private ShiftLabel versicherung = new ShiftLabel("Versicherung");
	private ShiftLabel noversicherung = new ShiftLabel("keine Versicherung");
	private SpringLayout layout;
	private JLabel noEntries;
	private Icon versicherungsicon;
	private Icon noversicherungsicon;

	public ExposeView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {
		entries.clear();
		table.removeAll();
		rows = 1;
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
		JScrollPane centerpanel = new JScrollPane(loadTable());
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
	}

	public void initGlobals() {
		entries = new HashMap<String, House>();
		table = new ShiftPanel2();
		layout = new SpringLayout();
		noEntries = Definitions.NO_ENTRY;
		rows = 1;
		versicherungsicon = new ImageIcon("res/Versicherung.png");
		noversicherungsicon = new ImageIcon("res/keineVersicherung.png");

	}

	public void deleteThisRow(int i) {
		table.removeAll();
		House deleted = entries.remove(Integer.toString(i));
		if (deleted == null) {
			System.out.println("Null: Error");
		} else {
			System.out.println("Haus in der " + deleted.getStrasse()
					+ " gelöscht");
		}

		rows = 1;
		// HashMap<String, House> housescopy = (HashMap<String, House>) entries
		// .clone();
		List<House> toadd = new ArrayList<House>();
		for (House house : entries.values()) {
			toadd.add(house);
		}
		if (toadd.size() == 0) {
			noEntries();
			return;
		}
		entries.clear();

		// headlines setzen
		headlinesSetzen();
		for (House house : toadd) {
			addRow(house);
		}

		System.out.println("tablecount:" + table.getComponentCount()
				+ " rows: " + rows + " cols:" + cols);
		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, xPad,
				yPad);
		table.validate();
		frame.repaint();
	}

	/**
	 * setzt die Überschriften
	 */
	public void headlinesSetzen() {
		table.add(nr);
		table.add(houseid);
		table.add(plz);
		table.add(ort);
		table.add(street);
		table.add(hausnr);
		table.add(area);
		table.add(garden);
		table.add(wohn);
		table.add(versicherung);
		table.add(noversicherung);
	}

	/**
	 * lädt initial die Tabelle
	 */
	public ShiftPanel2 loadTable() {
		HouseDAO housedao = new HouseDAO();
		List<House> houses = housedao.getIfStatusNotSeen();
		System.out.println("Lade Gebäude");
		if (houses != null) {
			table.setLayout(layout);
			headlinesSetzen();

			for (House house : houses) {
				addRow(house);
			}
			SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, xPad,
					yPad);
		} else {	
			noEntries();

		}
		table.validate();
		frame.repaint();
		frame.validate();
		return table;
	}

	/**
	 * wird aufgerufen wenn keine Einträge vorhanden sind
	 */
	public void noEntries() {

		// verhindern dass noEntries mehrfach hinzugefügt wird
		for (Component comp : table.getComponents()) {
			if (comp.equals(noEntries)) {
				return;
			}
		}

		table.setLayout(new FlowLayout());
		table.add(noEntries);
		frame.repaint();
		frame.validate();
		table.validate();

	}

	/**
	 * fügt eine neue Zeile zur Tabelle hinzu
	 * 
	 * @param house
	 *            neues anzuzueigendes Element
	 */
	protected void addRow(House house) {

		int row = rows;

		entries.put(Integer.toString(rows), house);
		ShiftTableEntry entry = new ShiftTableEntry(Integer.toString(rows));
		ShiftTableEntry entry1 = new ShiftTableEntry(Long.toString(house.getId()));
		ShiftTableEntry entry2 = new ShiftTableEntry(house.getPlz());
		ShiftTableEntry entry3 = new ShiftTableEntry(house.getOrt());
		ShiftTableEntry entry4 = new ShiftTableEntry(house.getStrasse());
		ShiftTableEntry entry5 = new ShiftTableEntry(house.getHausnr());
		ShiftTableEntry entry6 = new ShiftTableEntry(Double.toString(Math
				.round(house.getFlaeche() * 100d) / 100d));
		ShiftTableEntry entry7 = new ShiftTableEntry(Double.toString(Math
				.round(house.getGartenflaeche() * 100d) / 100d));
		ShiftTableEntry entry65 = new ShiftTableEntry(
				Double.toString(Math.round((house.getFlaeche() / house
						.getAnzahlWohnungen()) * 100d) / 100d));
		ShiftButton2 entry8 = new ShiftButton2("");
		entry8.setIcon(versicherungsicon);
		ShiftButton2 entry9 = new ShiftButton2("");
		entry9.setIcon(noversicherungsicon);
		final double area = house.getFlaeche();
		final int rowdel = row;
		final Long id = house.getId();

		entry8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Versicherung angelegt");
				List<Object> data = new ArrayList<Object>();
				data.add(area);
				data.add(rowdel);
				data.add(id);
				fireLocalUIEvent(this, UI_EVENT.PUSH_INSURANCE.ordinal(), data);
				
				
			}
		});

		entry9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Keine Versicherung beauftragt");
				System.out.println("Lösche Reihe: " + rowdel);
				HouseDAO housedao = new HouseDAO();
				House house = housedao.getById(id);
				deleteThisRow(rowdel);
				house.setSeen(true);
				housedao.persist(house);
			}
		});

		table.add(entry);
		table.add(entry1);
		table.add(entry2);
		table.add(entry3);
		table.add(entry4);
		table.add(entry5);
		table.add(entry6);
		table.add(entry65);
		table.add(entry7);
		table.add(entry8);
		table.add(entry9);

		rows++;
		System.out.println("tablecount:" + table.getComponentCount()
				+ " rows: " + rows + " cols:" + cols);
		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, xPad,
				yPad);
	}

	@Override
	public void addRow() {
		// unused
	}
}
