package ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import persistence.dao.impl.OrderDAO;
import persistence.entity.impl.Order;
import ui.enums.UI_EVENT;
import util.SpringTable;
import util.SpringUtilities;

import components.Definitions;
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
	private ImageIcon bestaetigungsicon = new ImageIcon("res/Bestaetigung.png");

	public OrderWindowView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {
		entries.clear();
		table.removeAll();
		rows = 1;
		loadTable();
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
		System.out.println("Lade Aufträge");
		if (orders != null) {
			table.setLayout(layout);
			headlinesSetzen();

			for (Order order : orders) {
				addRow(order);
			}
			SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY,
					xPad, yPad);
		} else {
			noEntries();

		}
		table.validate();
		frame.repaint();
		frame.validate();
		return table;
	}

	private void addRow(Order order) {
		int row = rows;
		String res6 = null;
		String res7 = null;

		entries.put(Integer.toString(rows), order);
		ShiftTableEntry entry = new ShiftTableEntry(Integer.toString(rows));
		ShiftTableEntry entry2 = new ShiftTableEntry(Long.toString(order
				.getId()));
		ShiftTableEntry entry3 = new ShiftTableEntry(order.getJobName());
		ShiftTableEntry entry4 = new ShiftTableEntry(order.getWohnungsID());
		final ShiftLabel entry5 = new ShiftLabel("");
		ShiftLabel entry6 = new ShiftLabel("");
		ShiftLabel entry7 = new ShiftLabel("");
		ShiftButtonBestaetigung entry8 = new ShiftButtonBestaetigung();

		final Order ord = order;
		final int rowdel = row;
		final List<Object> objectlist = new ArrayList<Object>();
		objectlist.add(ord);
		objectlist.add(entry5);
		objectlist.add(entry6);

		entry5.setIcon(new ImageIcon("res/Weiterleiten.png"));
		entry5.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getSource().equals(entry5))
				fireLocalUIEvent(this, UI_EVENT.AUFTRAG_WEITERLEITEN.ordinal(),
						objectlist);

			}
		});

		// true= Bestaetigung gesendet, else nicht
		if (ord.isStatusBestaetigung() == true) {
			res6 = "res/Bestaetigung.png";
		} else {
			res6 = "res/keineBestaetigung.png";
		}

		entry6.setIcon(new ImageIcon(res6));

		if (ord.isStatusRechnung() == true) {
			res7 = "res/Rechnung.png";
		} else {
			res7 = "res/keineRechnung.png";
		}

		entry7.setIcon(new ImageIcon(res7));

		entry8.setIcon(new ImageIcon("res/Loeschen2.png"));
		entry8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fireLocalUIEvent(this, UI_EVENT.REMOVE_ORDER.ordinal());
				OrderDAO orderdao = new OrderDAO();
				Order order = new Order();
				order = ord;
				deleteThisRow(rowdel);
				order.setSeen(true);
				JOptionPane.showMessageDialog(frame, "Auftrag in der Reihe "
						+ rowdel + " gelöscht.");
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
		table.validate();
		frame.validate();
		frame.repaint();

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
			// deleted.setSeen(true);
			// OrderDAO ordDao = new OrderDAO();
			// ordDao.persist(deleted);
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

	public void setzeBestaetigung(ShiftLabel entry5, ShiftLabel entry6) {
		Component[] comps = table.getComponents();
		for (Component component : comps) {
			if (component instanceof ShiftLabel) {
				if (component.equals(entry6)) {
					((ShiftLabel) component).setIcon(bestaetigungsicon);
				}else if (component.equals(entry5)) {
					((ShiftLabel) component).setVisible(false);
				}
			}
		}
	}
}