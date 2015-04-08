package ui.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import persistence.dao.impl.HouseDAO;
import persistence.dao.impl.OrderDAO;
import persistence.entity.impl.House;
import persistence.entity.impl.Order;
import ui.enums.UI_EVENT;
import util.SpringTable;
import util.SpringUtilities;
import components.Definitions;
import components.ShiftButton2;
import components.ShiftButtonBack;
import components.ShiftButtonBestaetigung;
import components.ShiftButtonWeiterleiten;
import components.ShiftFrame;
import components.ShiftLabel;
import components.ShiftPanel2;
import components.ShiftTableEntry;

public class OrderWindowView extends AbstractView implements SpringTable {

	private ShiftFrame frame;
	private ShiftPanel2 table;
	private int rows;
	private int cols = 8;
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
	private ShiftLabel löschen = new ShiftLabel("Löschen");

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
		ShiftButtonWeiterleiten entry5 = new ShiftButtonWeiterleiten();
		ShiftButtonBestaetigung entry6 = new ShiftButtonBestaetigung();
		ShiftButtonBestaetigung entry7 = new ShiftButtonBestaetigung();
		ShiftButtonBestaetigung entry8 = new ShiftButtonBestaetigung();

		final Order ord = order;
		final int rowdel = row;
		final Long id = ord.getId();

		//entry5.setIcon(new ImageIcon("res/Weiterleiten.png"));
		entry5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireLocalUIEvent(this, UI_EVENT.AUFTRAG_WEITERLEITEN.ordinal(),
						ord);
			}
		});

		final String res6;
//true= Bestaetigung gesendet, else nicht
		if (ord.isStatusWeiterleitung() == true) {
			res6 = "res/Bestaetigung.png";
		} else {
			res6 = "res/keineBestaetigung.png";
		}

		entry6.setIcon(new ImageIcon(res6));
		
		String res7;
		
		if (ord.isStatusRechnung() == true) {
			res7 = "res/Rechnung.png";
		}else {
			res7 = "res/keineRechnung.png";
		}

		entry7.setIcon(new ImageIcon(res7));

		entry8.setIcon(new ImageIcon("res/Loeschen2.png"));
		entry8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireLocalUIEvent(this, UI_EVENT.REMOVE_ORDER.ordinal());
				OrderDAO orderdao = new OrderDAO();
				Order order = orderdao.getById(id);
				deleteThisRow(rowdel);
				order.setSeen(true);
				JOptionPane.showMessageDialog(frame, "Auftrag gelöscht");
				orderdao.persist(order);
			}
		});

		table.add(entry);
		table.add(entry2);
		table.add(entry3);
		table.add(entry4);
		table.add(entry5);
		table.add(entry6);
		table.add(entry7);
		table.add(entry8);
		rows++;

		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, xPad,
				yPad);
		frame.validate();

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
		table.add(löschen);
	}

	@Override
	public void deleteThisRow(int i) {
		table.removeAll();
		Order deleted = entries.remove(Integer.toString(i));
		if (deleted == null) {
			System.out.println("Null: Error");
		} else {
			System.out.println("Order mit der ID " + deleted.getId()
					+ " gelöscht");
		}

		rows = 1;
		// HashMap<String, House> housescopy = (HashMap<String, House>) entries
		// .clone();
		List<Order> toadd = new ArrayList<Order>();
		for (Order order : entries.values()) {
			toadd.add(order);
		}
		if (toadd.size() == 0) {
			noEntries();
			return;
		}
		entries.clear();

		// headlines setzen
		headlinesSetzen();
		for (Order order : toadd) {
			addRow(order);
		}

		System.out.println("tablecount:" + table.getComponentCount()
				+ " rows: " + rows + " cols:" + cols);
		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, xPad,
				yPad);
		table.validate();
		frame.repaint();
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