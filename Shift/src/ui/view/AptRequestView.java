package ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import persistence.dao.impl.ApartmentDAO;
import persistence.entity.impl.Apartment;
import ui.enums.UI_EVENT;
import util.SpringTable;
import util.SpringUtilities;

import components.Definitions;
import components.ShiftButtonBack;
import components.ShiftButtonSearch;
import components.ShiftButtonSearchTable;
import components.ShiftFrame;
import components.ShiftLabel;
import components.ShiftPanel2;
import components.ShiftTableEntry;

public class AptRequestView extends AbstractView implements SpringTable {

	private ShiftFrame frame;
	private JTextField textfield;
	private ShiftPanel2 table;
	private int rows;
	private int cols = 5;
	private int initX = Definitions.initX;
	private int initY = Definitions.initY;
	private int xPad = Definitions.xPad;
	private int yPad = Definitions.yPad - 10;
	private HashMap<String, Apartment> entries;
	private ShiftLabel aptid = new ShiftLabel("WohnungsID");
	private ShiftLabel hirerCount = new ShiftLabel("Anzahl Mieter");
	private ShiftLabel area = new ShiftLabel("m²");
	private ShiftLabel roomCounter = new ShiftLabel("Anzahl Zimmer");
	private ShiftLabel detail = new ShiftLabel("Details");

	private SpringLayout layout;
	private JLabel noEntries;

	// private Icon details;

	public AptRequestView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {
		entries.clear();
		table.removeAll();
		loadTable();
		frame.getCardlayout().show(frame.getContentpanel(), "search");
		frame.setHeadline(Definitions.SEARCH);
		frame.validate();
		textfield.requestFocus();
		return null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

	}

	@Override
	protected void initUI() {
		// initialisieren der Variablen
		frame = ShiftFrame.getInstance();
		frame.setHeadline(Definitions.SEARCH);
		initGlobals();

		// Gestalten des Panels

		// Init der Panels
		ShiftPanel2 content = new ShiftPanel2();
		ShiftPanel2 northpanel = new ShiftPanel2();
		JScrollPane centerpanel = new JScrollPane(loadTable());
		ShiftPanel2 southpanel = new ShiftPanel2();

		content.setLayout(new BorderLayout());
		northpanel.setLayout(new FlowLayout());

		// Obere Leiste
		JLabel label = new JLabel("Wohnung");
		textfield = new JTextField(50);
		textfield.setBackground(Definitions.BG_COLOR_CONTENT);
		textfield.setOpaque(true);
		textfield.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				aptRequest();
			}
		});

		ShiftButtonSearch gobutton = new ShiftButtonSearch();
		gobutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				aptRequest();
			}
		});

		Dimension textfieldsize = new Dimension((int) Toolkit
				.getDefaultToolkit().getScreenSize().getWidth(), 25);
		textfield.setPreferredSize(textfieldsize);
		textfield.setMaximumSize(textfieldsize);

		// Untere Leiste
		ShiftButtonBack button = new ShiftButtonBack();
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
			}
		});

		// eastpanel.setBackground(Color.red);
		// westpanel.setBackground(Color.green);

		// add
		northpanel.add(label);
		northpanel.add(textfield);
		northpanel.add(gobutton);
		southpanel.add(button);

		// Borderlayout setzen
		content.add(northpanel, BorderLayout.NORTH);
		content.add(southpanel, BorderLayout.SOUTH);
		content.add(centerpanel, BorderLayout.CENTER);

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(content, "search");
		frame.getCardlayout().show(frame.getContentpanel(), "search");
		frame.validate();
		textfield.requestFocus();
	}

	protected void aptRequest() {
		table.setLayout(layout);
		String id = textfield.getText();
		table.removeAll();
		entries.clear();
		rows = 1;
		System.out.println("Rufe Wohnungsdaten ab... ID:" + id);
		textfield.setText("");
		ApartmentDAO aptdao = new ApartmentDAO();
		if (id.matches("[0-9]+")) {
			List<Apartment> aptlist = aptdao.listWhenStartsWith(id);
			if (aptlist != null) {
				headlinesSetzen();

				for (Apartment apartment : aptlist) {
					addRow(apartment);
				}
				SpringUtilities.makeCompactGrid(table, rows, cols, initX,
						initY, xPad, yPad);
				table.validate();
				frame.validate();
				frame.repaint();
			} else {
				JOptionPane.showMessageDialog(new JFrame(),
						"Haus nicht gefunden!", "Fehler",
						JOptionPane.ERROR_MESSAGE);
				noEntries();
			}
		} else {
			Apartment apt = aptdao.getApartment(id);
			if (apt != null) {
				headlinesSetzen();

				addRow(apt);
				SpringUtilities.makeCompactGrid(table, rows, cols, initX,
						initY, xPad, yPad);
				table.validate();
				frame.validate();
				frame.repaint();
			} else {
				JOptionPane.showMessageDialog(new JFrame(),
						"Apartment nicht gefunden!","Fehler",JOptionPane.ERROR_MESSAGE);
				noEntries();
			}
		}
	}

	private void addRow(Apartment apt) {

		entries.put(Integer.toString(rows), apt);
		ShiftTableEntry entry = new ShiftTableEntry(apt.getAptID());
		ShiftTableEntry entry2 = new ShiftTableEntry(Integer.toString(apt
				.getMieteranzahl()));
		ShiftTableEntry entry3 = new ShiftTableEntry(Double.toString(apt
				.getWohnflaeche()));
		ShiftTableEntry entry4 = new ShiftTableEntry(Integer.toString(apt
				.getZimmeranzahl()));
		ShiftButtonSearchTable entry5 = new ShiftButtonSearchTable();

		final Apartment apart = apt;
		entry5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Details der Wohnung " + apart.getAptID()
						+ " anzeigen...");
				fireLocalUIEvent(this, UI_EVENT.PUSH_DETAILS.ordinal(), apart);
			}
		});

		table.add(entry);
		table.add(entry2);
		table.add(entry3);
		table.add(entry4);
		table.add(entry5);

		rows++;
		System.out.println("tablecount:" + table.getComponentCount()
				+ " rows: " + rows + " cols:" + cols);
		SpringUtilities.makeCompactGrid(table, rows, cols, initX, initY, xPad,
				yPad);
	}

	@Override
	public ShiftPanel2 loadTable() {
		noEntries();
		return table;
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
		table.add(aptid);
		table.add(hirerCount);
		table.add(area);
		table.add(roomCounter);
		table.add(detail);
	}

	@Deprecated
	@Override
	public void deleteThisRow(int i) {
		// unused in dieser Klasse, da alle gelöscht werden bei einer Abfrage
	}

	@Deprecated
	@Override
	public void addRow() {
		// unused, da mit addRow(Apartment) erstetz
	}

	@Override
	public void initGlobals() {
		entries = new HashMap<String, Apartment>();
		table = new ShiftPanel2();
		layout = new SpringLayout();
		noEntries = Definitions.NO_ENTRY;
		rows = 1;
		// details = new ImageIcon("res/WohnungsInfo.png");
	}
}
