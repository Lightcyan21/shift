package util;

import java.util.Enumeration;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public abstract class TableModel extends AbstractTableModel{

	protected Vector<IRow> dataVector;
	private Class[] columnClass;
	private String[] columnNames;
	
	public TableModel(Class[] columnClass, String[] columnNames){
		this.columnClass = columnClass;
		this.columnNames = columnNames;
		dataVector = new Vector <IRow>();
	}
	
	public TableModel(Class[] columnClass, String[] columnNames, int size){
		this.columnClass = columnClass;
		this.columnNames = columnNames;
		dataVector = new Vector <IRow>(size);
	}
	
	@Override
	public int getRowCount() {
		return dataVector.size();
	}
	
	@Override
	public int getColumnCount(){
		return columnNames.length;
	}
	
	@Override
	public Class <?> getColumnClass(int columnIndex){
		return columnClass[columnIndex];
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	public void addRow(IRow data){
		dataVector.insertElementAt(data, getRowCount());
		fireTableDataChanged();
	}
	
	public IRow getRow(int row){
		return dataVector.elementAt(row);
	}
	
	public Enumeration<IRow> getRows(){
		return dataVector.elements();
	}
	
	@SuppressWarnings("unchecked")
	public Vector<IRow> copyDataVector(){
		return (Vector<IRow>)dataVector.clone();
	}
	
	public void removeRow(int row) {
		 dataVector.removeElementAt(row);
		 fireTableRowsDeleted(row, row);
	}
	
	public void removeAllRows(){
		int old = getRowCount();
		int rowCount = 0;
       if (old == rowCount) {
           return;
       }
       dataVector.setSize(rowCount);
       if (rowCount <= old) {
           fireTableRowsDeleted(rowCount, old-1);
       }
	}

	
}
