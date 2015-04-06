package components;

import java.awt.Color;

public class Definitions {
	public static final String WINDOW_TITLE = "SHIFT - GEBÄUDEMANAGEMENT";
	public static final String TITLE = "Shift";

	public static final Color BG_COLOR = new Color(255, 161, 83);
	public static final Color BG_COLOR_CONTENT = new Color(255, 204, 161);
	// static final int TOP_PADDING = 50;
	// static final int SIDE_PADDING = 50;
	public static final int PADDING = 100;
	public static final String ORDERS = "<html><body><center>Aufträge</html></body>";
	public static final String CHECK_STATUS = "<html><body><center>Status<br>abfragen</html></body>";
	public static final String SEARCH = "<html><body><center>Wohnungs-<br>informationen</html></body>";
	public static final String INFO = "<html><body><center>Infos von<br>Buchhaltung</html></body>";
	public static final String HOUSES = "<html><body><center>Gebäude</html></body>";
	public static final double hecke_schneiden = 34.99;
	public static final double rasen_maehen = 0.99; // pro qm
	public static final double gartenpflege = 99.99; // pro Monat
	public static final double fusswege_raeumen = 39.99; // pro Monat
	public static final double salz_streuen = 19.99; // pro Monat
	public static final double gas_ablesen = 9.99; // pro Ablesung
	public static final double wasser_ablesen = 1.99; // pro Ablesung
	public static final double strom_ablesen = 1.99; // pro ablesung
	public static final double reparatur = 29.99; // Stundenlohn +
													// Materialkosten ex.
	public static final double instandhaltung = 9.99; // pro WE + Materialkosten
	public static final double installation = 9.99; // pro WE + Materialkosten
	public static final double fensterreinigung = 19.99; // pro Stockwerk
	public static final double treppenreinigung = 19.99; // pro Stockwerk
	public static final double schluesseldienst = 49.99; // pro Einsatz
	public static final String GARTENPFLEGE_STRING = "Gartenpflege";
	public static final String RASEN_MAEHEN_STRING = "Rasen maehen";
	public static final String FUSSWEG_RAEUMEN_STRING = "Fussweg raeumen";
	public static final String SALZ_STREUEN_STRING = "Salz streuen";
	public static final String FENSTERREINIGUNG_STRING = "Fensterreinigung";
	public static final String TREPPENREINIGUNG_STRING = "Treppenreinigung";
	public static final String HECKE_SCHNEIDEN_STRING = "Hecke schneiden";
	public static final String INSTANDHALTUNG_STRING = "Instandhaltung";
	public static final String SCHLUESSELDIENST_STRING = "Schluesseldienst";
	public static final String INSTALLATION_STRING = "Installation";
	public static final String REPARATUR_STRING = "Reparatur";
	public static final double ableseservicegas = 9.99;
	public static final double ableseserviceWasser = 4.99;
	public static final double ableseserviceStrom = 4.99;
	public static final double preisProEinheitGas = 0.07;
	public static final double preisProEinheitWaser = 0.002;
	public static final double preisProEinheitStrom = 0.30;
	public static final String ERROR_MESSAGE = "Fehler aufgetreten, bitte Eingabe ueberpruefen";
	public static final double gewinn = 0.2;
	public static final Color HEADLINE_COLOR = new Color(255, 255, 255);
	public static final long iban = 683050050005L;
	/*
	 * "Pruefung" - 0 "Angekommen" - 1 "In Arbeit" - 2
	 * "Erledigt + Rechnung versendet" - 3 "Abgelehnt" - 4 "Rechnung bezahlt" -
	 * 5
	 */
	public static final int PRUEFUNG = 0;
	public static final int ANGEKOMMEN = 1;
	public static final int IN_ARBEIT = 2;
	public static final int ERLEDIGT = 3;
	public static final int ABGELEHNT = 4;
	public static final int RECHNUNG_BEZAHLT = 5;

}
