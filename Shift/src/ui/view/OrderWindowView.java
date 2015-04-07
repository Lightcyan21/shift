package ui.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import persistence.dao.impl.OrderDAO;
import persistence.entity.impl.Order;
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

public class OrderWindowView extends AbstractView implements SpringTable {

	private ShiftFrame frame;
	private ShiftPanel2 table;
	private int rows;
	private int cols = 7;
	private int initX = 10;
	private int initY = 0;
	private int xPad = 30;
	private int yPad = 0;
	private HashMap<String, Order> entries;
	private SpringLayout layout;
	private JLabel noEntries;
	private ShiftLabel nr = new ShiftLabel("Nr.");
	private ShiftLabel auftragsid = new ShiftLabel("AuftragsID");
	private ShiftLabel auftragstyp = new ShiftLabel("Auftragsart");
	private ShiftLabel wohnungsid = new ShiftLabel("WohnungsID");
	private ShiftLabel weiterleitung = new ShiftLabel("Weiterleitung");
	private ShiftLabel bestaetigung = new ShiftLabel("Bestätigung");
	private ShiftLabel rechnung = new ShiftLabel("Rechnung");

	public OrderWindowView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {
		frame.getCardlayout().show(frame.getContentpanel(), "page");
		frame.setHeadline(Definitions.ORDERS);
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
		frame.setHeadline(Definitions.ORDERS);
		initGlobals();
		// Gestalten des Panels
		ShiftPanel2 contentpanel = new ShiftPanel2();
		ShiftPanel2 backbutton = new ShiftPanel2();
		// ShiftPanel2 northpanel = new ShiftPanel2();
		JScrollPane centerpanel = new JScrollPane(loadTable());
		centerpanel.setOpaque(true);
		contentpanel.setOpaque(true);

		// Backbutton
		ShiftButtonBack button = new ShiftButtonBack();
		backbutton.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
			}
		});

		// // northpanel
		// final JTextField textfield = new JTextField(50);
		// JLabel label = new JLabel("Auftrag");
		// textfield.setBackground(Definitions.BG_COLOR_CONTENT);
		// textfield.setOpaque(true);
		// textfield.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// System.out.println("Auftrag init...");
		// fireLocalUIEvent(this, UI_EVENT.AUFTRAG_ERTEILEN.ordinal(),
		// textfield.getText());
		// }
		// });
		//
		// ShiftButtonSearch gobutton = new ShiftButtonSearch();
		// gobutton.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// System.out.println("Auftrag init...");
		// fireLocalUIEvent(this, UI_EVENT.AUFTRAG_ERTEILEN.ordinal(),
		// textfield.getText());
		//
		// }
		// });
		// northpanel.add(label);
		// northpanel.add(textfield);
		// northpanel.add(gobutton);

		// add
		contentpanel.setLayout(new BorderLayout());
		// contentpanel.add(northpanel, BorderLayout.NORTH);
		contentpanel.add(backbutton, BorderLayout.SOUTH);
		contentpanel.add(centerpanel, BorderLayout.CENTER);

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(contentpanel, "page");
		frame.getCardlayout().show(frame.getContentpanel(), "page");
		frame.validate();
	}

	@Override
	public ShiftPanel2 loadTable() {
		OrderDAO orderdao = new OrderDAO();
		List<Order> orders = orderdao.getIfStatusNotSeen();
		System.out.println("Lade Gebäude");
		if (orders != null) {
			table.setLayout(layout);
			headlinesSetzen();

			for (Order order : orders) {
				addRow(order);
			}
		} else {
			noEntries();

		}
		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, xPad,
				yPad);
		frame.validate();
		return table;
	}

	private void addRow(Order order) {
		int row = rows;

		entries.put(Integer.toString(rows), order);
		ShiftTableEntry entry = new ShiftTableEntry(Integer.toString(rows));
		ShiftTableEntry entry2 = new ShiftTableEntry(Long.toString(order
				.getId()));
		ShiftTableEntry entry3 = new ShiftTableEntry(order.getJobName());
		ShiftTableEntry entry4 = new ShiftTableEntry(order.getWohnungsID());
		ShiftButton2 entry5 = new ShiftButton2("");
		ShiftTableEntry entry6 = null;
		ShiftTableEntry entry7 = null;

		entry5.setIcon(new ImageIcon("res/WohnungsInfo.png"));
		entry5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireLocalUIEvent(this, UI_EVENT.WEITERLEITEN.ordinal());
			}
		});

		// ShiftTableEntry entry5 = new ShiftTableEntry(Double.toString(Math
		// .round(house.getFlaeche() * 100d) / 100d));
		// ShiftTableEntry entry6 = new ShiftTableEntry(Double.toString(Math
		// .round(house.getGartenflaeche() * 100d) / 100d));
		// ShiftTableEntry entry65 = new ShiftTableEntry(
		// Double.toString(Math.round((house.getFlaeche() / house
		// .getAnzahlWohnungen()) * 100d) / 100d));
		// ShiftButton2 entry7 = new ShiftButton2("");
		// entry7.setIcon(new ImageIcon("res/WohnungsInfo.png"));
		// ShiftButton2 entry8 = new ShiftButton2("");
		// entry8.setIcon(new ImageIcon("res/WohnungsInfo.png"));
		//
		// entry7.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// System.out.println("Versicherung angelegt");
		// fireLocalUIEvent(this, UI_EVENT.PUSH_INSURANCE.ordinal(), area);
		// HouseDAO housedao = new HouseDAO();
		// House house = housedao.getById(id);
		// deleteThisRow(rowdel);
		// house.setSeen(true);
		// housedao.persist(house);
		// }
		// });
		//
		// entry8.addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(ActionEvent e) {
		// System.out.println("Keine Versicherung beauftragt");
		// System.out.println("Lösche Reihe: " + rowdel);
		// HouseDAO housedao = new HouseDAO();
		// House house = housedao.getById(id);
		// deleteThisRow(rowdel);
		// house.setSeen(true);
		// housedao.persist(house);
		// }
		// });

		table.add(entry);
		table.add(entry2);
		table.add(entry3);
		table.add(entry4);
		table.add(entry5);
		table.add(entry6);
		table.add(entry7);
		rows++;
	}

	@Override
	public void noEntries() {

	}

	@Override
	public void headlinesSetzen() {
		table.add(nr);
		table.add(auftragsid);
		table.add(auftragstyp);
		table.add(wohnungsid);
		table.add(weiterleitung);
		table.add(bestaetigung);
		table.add(rechnung);
	}

	@Override
	public void deleteThisRow(int i) {

	}

	@Override
	public void addRow() {

	}

	@Override
	public void initGlobals() {
		entries = new HashMap<String, Order>();
		table = new ShiftPanel2();
		layout = new SpringLayout();
		noEntries = Definitions.NO_ENTRY;
		rows = 1;

	}

}