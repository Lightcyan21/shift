package ui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import persistence.dao.impl.ApartmentDAO;
import persistence.entity.impl.Apartment;
import ui.enums.UI_EVENT;
import util.IRow;
import util.TableModel;

import components.Definitions;
import components.ShiftButton;
import components.ShiftFrame;
import components.ShiftPanel2;

public class AptRequestView extends AbstractView {

	private ShiftFrame frame;
	private JTextField textfield;
	private JScrollPane mainTablePane;
	private AptRequestTableModel tableModel;
	private JTable mainTable;

	public AptRequestView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {

		frame.getCardlayout().show(frame.getContentpanel(), "search");
		frame.setHeadline(Definitions.SEARCH);
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
		frame.setHeadline(Definitions.SEARCH);

		// Gestalten des Panels

		// Init der Panels
		ShiftPanel2 content = new ShiftPanel2();
		ShiftPanel2 northpanel = new ShiftPanel2();
		ShiftPanel2 centerpanel = new ShiftPanel2();
		ShiftPanel2 buttonpanel = new ShiftPanel2();
		ShiftPanel2 eastpanel = new ShiftPanel2();
		ShiftPanel2 westpanel = new ShiftPanel2();

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

		ShiftButton gobutton = new ShiftButton("►");
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
		ShiftButton button = new ShiftButton("Back");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
			}
		});

		// Tabelle in der Mitte
		// WohnungsID, Anzahl Mieter m² Anzahl Zimmer
		mainTable = createAptRequestTable();
		mainTablePane = new JScrollPane(mainTable);

		eastpanel.setBackground(Color.red);
		westpanel.setBackground(Color.green);

		// add
		northpanel.add(label);
		northpanel.add(textfield);
		northpanel.add(gobutton);
		buttonpanel.add(button);
		centerpanel.add(mainTablePane);

		// Borderlayout setzen
		content.add(eastpanel, BorderLayout.EAST);
		content.add(westpanel, BorderLayout.WEST);
		content.add(northpanel, BorderLayout.NORTH);
		content.add(buttonpanel, BorderLayout.SOUTH);
		content.add(centerpanel, BorderLayout.CENTER);

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(content, "search");
		frame.getCardlayout().show(frame.getContentpanel(), "search");
		frame.validate();

	}

	private JTable createAptRequestTable() {
		tableModel = new AptRequestTableModel();
		JTable table = new JTable(tableModel);
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setDoubleBuffered(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setFillsViewportHeight(true);
		table.setDragEnabled(false);
		table.setGridColor(Color.BLACK);
		table.setSelectionBackground(Definitions.BG_COLOR);
		table.setSelectionForeground(Definitions.BG_COLOR_CONTENT);

		table.getColumnModel().getColumn(0).setPreferredWidth(25);

		table.getTableHeader().resizeAndRepaint();
		table.setDefaultRenderer(Object.class, new TableRowRenderer(tableModel));
		table.setDefaultRenderer(Long.class, new TableRowRenderer(tableModel));
		return table;
	}

	protected void aptRequest() {
		String id = textfield.getText();
		System.out.println(id);
		textfield.setText("");
		ApartmentDAO aptdao = new ApartmentDAO();
		Apartment apt = aptdao.getApartment(id);
		apt.getMieteranzahl();
		apt.getWohnflaeche();
		apt.getZimmeranzahl();

	}

	public void addZeile(long id, String text) {
		tableModel.addRow(new AptRequestRow(id, text));
		tableModel.fireTableDataChanged();
	}

	public void deleteZeile(long id) {
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			AptRequestRow r = (AptRequestRow) tableModel.getRow(i);
			if (r.getId() == id)
				tableModel.removeRow(i);
		}
		tableModel.fireTableDataChanged();
	}

	class TableRowRenderer extends JLabel implements TableCellRenderer {

		private static final long serialVersionUID = 1L;
		private AptRequestTableModel tableModel;

		public TableRowRenderer(AptRequestTableModel tableModel) {
			this.tableModel = tableModel;
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int col) {

			setForeground(Color.BLACK);
			setBackground(Color.white);
			if (value != null)
				setText(value.toString());
			if (hasFocus || isSelected) {
				Font font = new Font(this.getFont().getFamily(), Font.BOLD,
						this.getFont().getSize());
				this.setFont(font);
				setBackground(Color.darkGray);
				setForeground(Color.white);
			} else {
				Font font = new Font(this.getFont().getFamily(), Font.PLAIN,
						this.getFont().getSize());
				this.setFont(font);
			}

			return this;
		}
	}

	static class AptRequestTableModel extends TableModel {

		private static final long serialVersionUID = -5574999980921824807L;

		private static Class[] columnClass = { Long.class, String.class, };

		private static String[] columnNames = { "Meine-ID", "Der Text", };

		public AptRequestTableModel() {
			super(columnClass, columnNames);
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			AptRequestRow row = (AptRequestRow) dataVector.elementAt(rowIndex);

			switch (columnIndex) {
			case 0:
				return row.getId();
			case 1:
				return row.getText();
			}
			return null;
		}

		public void setValue(Object value, int rowIndex, int columnIndex) {
			AptRequestRow row = (AptRequestRow) dataVector.elementAt(rowIndex);

			switch (columnIndex) {
			case 0:
				row.setId((long) value);
			case 1:
				row.setText((String) value);

				fireTableCellUpdated(rowIndex, columnIndex);
			}
		}
	}

	class AptRequestRow implements IRow {

		private long id;
		private String text;

		public AptRequestRow(long id, String text) {
			this.id = id;
			this.text = text;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		@Override
		public IRow copyRow() {
			return null;
		}

		@Override
		public Vector<Object> toVector() {
			return null;
		}
	}
}
