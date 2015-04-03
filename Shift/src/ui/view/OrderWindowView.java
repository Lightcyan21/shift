package ui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import ui.enums.UI_EVENT;
import util.IRow;
import util.TableModel;
import components.Definitions;
import components.ShiftButton;
import components.ShiftFrame;
import components.ShiftPanel2;

public class OrderWindowView extends AbstractView {

	private ShiftFrame frame;
	private JScrollPane mainTablePane;
	private MyTableModel tableModel;
	private JTable mainTable;

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

		// Gestalten des Panels
		ShiftPanel2 contentpanel = new ShiftPanel2();
		// ShiftPanel2 centerpanel = new ShiftPanel2();
		ShiftPanel2 sp2 = new ShiftPanel2();
		ShiftPanel2 northpanel = new ShiftPanel2();

		// Backbutton
		ShiftButton button = new ShiftButton("Back");
		sp2.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
			}
		});

		// northpanel
		final JTextField textfield = new JTextField(50);
		JLabel label = new JLabel("Auftrag");
		textfield.setBackground(Definitions.BG_COLOR_CONTENT);
		textfield.setOpaque(true);
		textfield.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Auftrag init...");
				fireLocalUIEvent(this, UI_EVENT.AUFTRAG_ERTEILEN.ordinal(),
						textfield.getText());
			}
		});

		ShiftButton gobutton = new ShiftButton("->");
		gobutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Auftrag init...");
				fireLocalUIEvent(this, UI_EVENT.AUFTRAG_ERTEILEN.ordinal(),
						textfield.getText());

			}
		});
		mainTable = createMyTable();
		mainTablePane = new JScrollPane(mainTable);
		northpanel.add(label);
		northpanel.add(textfield);
		northpanel.add(gobutton);

		// add
		contentpanel.setLayout(new BorderLayout());
		contentpanel.add(northpanel, BorderLayout.NORTH);
		contentpanel.add(sp2, BorderLayout.SOUTH);
		contentpanel.add(mainTablePane, BorderLayout.CENTER);

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(contentpanel, "page");
		frame.getCardlayout().show(frame.getContentpanel(), "page");
		frame.validate();
	}

	private JTable createMyTable() {
		tableModel = new MyTableModel();
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
		table.setSelectionBackground(Color.gray);
		table.setSelectionForeground(Color.WHITE);

		table.getColumnModel().getColumn(0).setPreferredWidth(25);

		table.getTableHeader().resizeAndRepaint();
		table.setDefaultRenderer(Object.class, new TableRowRenderer(tableModel));
		table.setDefaultRenderer(Long.class, new TableRowRenderer(tableModel));
		return table;
	}

	public void addZeile(long id, String text) {
		tableModel.addRow(new MyRow(id, text));
		tableModel.fireTableDataChanged();
	}

	public void deleteZeile(long id) {
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			MyRow r = (MyRow) tableModel.getRow(i);
			if (r.getId() == id)
				tableModel.removeRow(i);
		}
		tableModel.fireTableDataChanged();
	}

	class TableRowRenderer extends JLabel implements TableCellRenderer {

		private static final long serialVersionUID = 1L;
		private MyTableModel tableModel;

		public TableRowRenderer(MyTableModel tableModel) {
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

	static class MyTableModel extends TableModel {

		private static final long serialVersionUID = -5574999980921824807L;

		private static Class[] columnClass = { Long.class, String.class,
				String.class, JButton.class, };

		private static String[] columnNames = { "Auftrags ID", "Auftragsart",
				"WohnungsID", "Weiterleitung", "Bestätigung", "Rechnung" };

		public MyTableModel() {
			super(columnClass, columnNames);
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			MyRow row = (MyRow) dataVector.elementAt(rowIndex);

			switch (columnIndex) {
			case 0:
				return row.getId();
			case 1:
				return row.getText();
			}
			return null;
		}

		public void setValue(Object value, int rowIndex, int columnIndex) {
			MyRow row = (MyRow) dataVector.elementAt(rowIndex);

			switch (columnIndex) {
			case 0:
				row.setId((long) value);
			case 1:
				row.setText((String) value);

				fireTableCellUpdated(rowIndex, columnIndex);
			}
		}
	}

	class MyRow implements IRow {

		private long id;
		private String text;

		public MyRow(long id, String text) {
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