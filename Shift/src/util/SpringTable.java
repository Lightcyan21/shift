package util;

import components.ShiftPanel2;

public interface SpringTable {
	/**
	 * lädt initial die Tabelle
	 * @return gibt die Tabelle zurück
	 */
	abstract ShiftPanel2 loadTable();

	/**
	 * wird aufgerufen wenn keine Einträge vorhanden sind
	 */
	abstract void noEntries();

	/**
	 * setzt die Überschriften
	 */
	abstract void headlinesSetzen();

	/**
	 * wird verwendet um die Zeile zu löschen
	 * 
	 * @param i
	 *            index der Zeile
	 */
	abstract void deleteThisRow(int i);

	/**
	 * wird verwendet, um Zeile hinzuzufügen, bitte um den anzuzeigenden Typen
	 * als Übergabeparameter überschreiben
	 */
	abstract void addRow();

}
