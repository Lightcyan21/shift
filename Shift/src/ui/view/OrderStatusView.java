package ui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import ui.enums.UI_EVENT;
import util.IRow;
import util.TableModel;

import components.Definitions;
import components.ShiftButtonBack;
import components.ShiftFrame;
import components.ShiftPanel2;

public class OrderStatusView extends AbstractView {
	private ShiftFrame frame;
	private JScrollPane mainTablePane;
	private MyTableModel tableModel;
	private JTable mainTable;

	public OrderStatusView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {
		frame.getCardlayout().show(frame.getContentpanel(), "checkStatus");
		frame.setHeadline(Definitions.CHECK_STATUS);
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
		frame.setHeadline(Definitions.CHECK_STATUS);

		// Gestalten des Panels
		ShiftPanel2 content = new ShiftPanel2();
		ShiftPanel2 table = new ShiftPanel2();
		content.setLayout(new BorderLayout());
		ShiftButtonBack button = new ShiftButtonBack();

		mainTable = createMyTable();
		mainTablePane = new JScrollPane(mainTable);

		table.add(mainTablePane);

		content.add(table, BorderLayout.CENTER);
		content.add(button, BorderLayout.SOUTH);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
			}
		});

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(content, "checkStatus");
		frame.getCardlayout().show(frame.getContentpanel(), "checkStatus");
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

		private static Class[] columnClass = { Long.class, String.class, };

		private static String[] columnNames = { "Meine-ID", "Der Text", };

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