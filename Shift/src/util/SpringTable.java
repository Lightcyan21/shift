package util;

import components.ShiftPanel2;

public interface SpringTable {
	/**
	 * l�dt initial die Tabelle
	 * @return gibt die Tabelle zur�ck
	 */
	abstract ShiftPanel2 loadTable();

	/**
	 * wird aufgerufen wenn keine Eintr�ge vorhanden sind
	 */
	abstract void noEntries();

	/**
	 * setzt die �berschriften
	 */
	abstract void headlinesSetzen();

	/**
	 * wird verwendet um die Zeile zu l�schen
	 * 
	 * @param i
	 *            index der Zeile
	 */
	abstract void deleteThisRow(int i);

	/**
	 * wird verwendet, um Zeile hinzuzuf�gen, bitte um den anzuzeigenden Typen
	 * als �bergabeparameter �berschreiben
	 */
	abstract void addRow();

}
