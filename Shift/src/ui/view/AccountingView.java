package ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;

import org.jdom2.JDOMException;

import persistence.entity.impl.Bill;
import ui.enums.UI_EVENT;
import util.SpringTable;
import util.SpringUtilities;
import webservices.impl.BuchhaltungWS;
import webservices.impl.BuchhaltungWsImplService;
import baldoapp.ProjektXMLParser;

import components.Definitions;
import components.ShiftButton2;
import components.ShiftButtonBack;
import components.ShiftFrame;
import components.ShiftLabel;
import components.ShiftPanel2;
import components.ShiftTableEntry;

public class AccountingView extends AbstractView implements SpringTable {

	private ShiftPanel2 table;
	private int rows;
	private int cols = 7;
	private int initX = 10;
	private int initY = 0;
	private int xPad = 30;
	private int yPad = 20;
	private HashMap<String, Bill> entries;
	private Map<String, String> forderungslisteMap;
	private ShiftLabel nr = new ShiftLabel("Nr.");

	private SpringLayout layout;
	private JLabel noEntries;
	private Icon mahnungsicon;
	private Icon nomahnungsicon;
	private ShiftFrame frame;
	private ShiftLabel rechnungID = new ShiftLabel("RechnungsID");
	private ShiftLabel verwendungszweck = new ShiftLabel("Verwendungszweck");
	private ShiftLabel name = new ShiftLabel("Schuldner");
	private ShiftLabel betrag = new ShiftLabel("Betrag");
	private ShiftLabel mahnung = new ShiftLabel("Mahnung");
	private ShiftLabel nomahnung = new ShiftLabel("Keine Mahnung");

	public AccountingView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {
		entries.clear();
		table.removeAll();
		rows = 1;
		loadTable();
		frame.getCardlayout().show(frame.getContentpanel(), "accounting");
		frame.setHeadline(Definitions.INFO);
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
		frame.setHeadline(Definitions.INFO);
		initGlobals();

		// Gestalten des Panels
		ShiftPanel2 content = new ShiftPanel2();
		ShiftButtonBack button = new ShiftButtonBack();
		JScrollPane centerpanel = new JScrollPane(loadTable());
		ShiftPanel2 southpanel = new ShiftPanel2();

		southpanel.add(button);

		content.setLayout(new BorderLayout());
		content.add(centerpanel, BorderLayout.CENTER);
		content.add(southpanel, BorderLayout.SOUTH);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
			}
		});
		centerpanel.setOpaque(true);
		content.setOpaque(true);
		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(content, "accounting");
		frame.getCardlayout().show(frame.getContentpanel(), "accounting");
		frame.validate();

	}

	@Override
	public ShiftPanel2 loadTable() {
		System.out.println("Lade Mahnungen von Buchhaltung...");
		Bill bill = new Bill();
		BuchhaltungWsImplService bhservice = new BuchhaltungWsImplService();
		BuchhaltungWS bh = bhservice.getBuchhaltungWsImplPort();
		String forderungslisteXML = bh.gebeForderungsliste("GM");
		Map<String, String> forderungen = null;
		try {
			forderungslisteMap = ProjektXMLParser
					.XMLStringToMap(forderungslisteXML);
		} catch (JDOMException | IOException e) {
			System.out.println("Es ist ein Fehler aufgetreten: "
					+ e.getMessage());
		}
		if (forderungslisteMap.size() != 0) {
			table.setLayout(layout);
			headlinesSetzen();

			Set<Entry<String, String>> rechnungen = forderungslisteMap
					.entrySet();

			for (Entry<String, String> entryXML : rechnungen) {
				// "rechnungsersteller", Rechnungsersteller (wird immer nur eure
				// Firma sein)/ "rechnungsempfaenger", Rechnungsempfänger/
				// "betrag",
				// Betrag/ "zahlungsdatum", Zahlungsdatum ("dd.MM.yyyy")/
				// "verwendungszweck", Verwendungszweck/ "rechnungsdatum",
				// Rechnungsdatum ("dd.MM.yyyy")/ "sender", Sender der Rechnung
				// (wird immer nur eure Firma sein)
				try {
					forderungen = ProjektXMLParser.XMLStringToMap(entryXML
							.getValue());
				} catch (JDOMException | IOException e) {
					System.out.println(e.getMessage());
				}
				bill.setRechnungssteller(forderungen.get("rechnungsersteller"));
				bill.setRechnungsEmpfaenger(forderungen
						.get("rechnungsempfaenger"));
				bill.setBetrag(Double.parseDouble(forderungen.get("betrag")));
				bill.setZahlungsdatum(forderungen.get("zahlungsdatum"));
				bill.setVerwendungszweck(forderungen.get("verwendungszweck"));
				bill.setRechnungsdatum(forderungen.get("rechnungsdatum"));
				addRow(bill);
				SpringUtilities.makeCompactGrid(table, rows, cols, initX,
						initY, xPad, yPad);
			}
		} else {
			noEntries();
		}

		frame.validate();
		table.validate();
		frame.repaint();
		return table;
	}

	private void addRow(Bill bill) {
		int row = rows;

		entries.put(Integer.toString(rows), bill);
		ShiftTableEntry entry = new ShiftTableEntry(Integer.toString(rows));
		ShiftTableEntry entry2 = new ShiftTableEntry(
				Long.toString(bill.getId()));
		ShiftTableEntry entry25 = new ShiftTableEntry(
				bill.getVerwendungszweck());
		ShiftTableEntry entry3 = new ShiftTableEntry(
				bill.getRechnungsEmpfaenger());
		ShiftTableEntry entry4 = new ShiftTableEntry(Double.toString(bill
				.getBetrag()));
		ShiftButton2 entry5 = new ShiftButton2("");
		entry5.setIcon(mahnungsicon);
		ShiftButton2 entry6 = new ShiftButton2("");
		entry6.setIcon(nomahnungsicon);
		final int rowdel = row;
		final String id = bill.getVerwendungszweck();

		entry5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Mahnung beauftragt...");
				fireLocalUIEvent(this, UI_EVENT.PUSH_MAHNUNG.ordinal(), id
						+ "#" + rowdel);
			}
		});

		entry6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Keine Mahnung beauftragt...");
				System.out.println("Lösche Reihe: " + rowdel);
				deleteThisRow(rowdel);
			}
		});

		table.add(entry);
		table.add(entry2);
		table.add(entry25);
		table.add(entry3);
		table.add(entry4);
		table.add(entry5);
		table.add(entry6);

		rows++;
		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, xPad,
				yPad);
	}

	@Override
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

	@Override
	public void headlinesSetzen() {
		table.add(nr);
		table.add(rechnungID);
		table.add(verwendungszweck);
		table.add(name);
		table.add(betrag);
		table.add(mahnung);
		table.add(nomahnung);
	}

	@Override
	public void deleteThisRow(int i) {
		table.removeAll();
		Bill deleted = entries.remove(Integer.toString(i));
		if (deleted == null) {
			System.out.println("Null: Error");
		} else {
			System.out.println("Rechnung: " + deleted.getVerwendungszweck()
					+ " aus der Ansicht entfernt");
		}

		rows = 1;
		List<Bill> toadd = new ArrayList<Bill>();
		for (Bill bill : entries.values()) {
			toadd.add(bill);
		}
		if (toadd.size() == 0) {
			noEntries();
			return;
		}
		entries.clear();

		// headlines setzen
		headlinesSetzen();
		for (Bill bill : toadd) {
			addRow(bill);
		}

		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, xPad,
				yPad);
		table.validate();
		frame.repaint();
	}

	@Deprecated
	@Override
	public void addRow() {
		// unused
	}

	@Override
	public void initGlobals() {
		entries = new HashMap<String, Bill>();
		table = new ShiftPanel2();
		layout = new SpringLayout();
		noEntries = Definitions.NO_ENTRY;
		rows = 1;
		mahnungsicon = new ImageIcon("res/WohnungsInfo.png");
		nomahnungsicon = new ImageIcon("res/WohnungsInfo.png");
	}

}
