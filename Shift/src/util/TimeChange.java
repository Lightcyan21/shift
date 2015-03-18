package util;

import java.util.List;

import persistence.dao.impl.ApartmentDAO;
import persistence.entity.impl.Apartment;

public class TimeChange {

	private static TimeChange instance;

	public void month() {
		// Nebenkosten
		getUtilities();
		// Rechnungsposten addieren

		// Rechnung erstellen

	}

	private void getUtilities() {
		// TODO Nebenkostenaufschlüsselung
		ApartmentDAO aptdao = new ApartmentDAO();
		List<Apartment> allApt = aptdao.findAll();
		double zaehlerstandGas = 0;
		double ableseservicegas = 9.99;
		double ableseserviceWasser = 4.99;
		double ableseserviceStrom = 4.99;
		double zaehlerstandWasserEK = 0;
		double zaehlerstandWasserGMK = 0;
		double zaehlerstandStromEK = 0;
		double zaehlerstandStromGMK = 0;
		double gewinn = 0.2;
		double preisProEinheitGas = 0.07;
		double preisProEinheitWaser = 0.002;
		double preisProEinheitStrom = 0.30;

		String[][][] result = new String[allApt.size()][16][2];
		for (int i = 0; i < allApt.size(); i++) {
			// Ableseservice beauftragen
			// Gas GMK
			result[i][0][0] = allApt.get(i).getAptID() + "#Gas";
			result[i][0][1] = Double.toString(((preisProEinheitGas
					* zaehlerstandGas + ableseservicegas) / allApt.get(i)
					.getHouse().getFlaeche())
					* allApt.get(i).getWohnflaeche());
			// Wasser EK+GMK
			result[i][1][0] = allApt.get(i).getAptID() + "#Wasser";
			result[i][1][1] = Double
					.toString((preisProEinheitWaser * zaehlerstandWasserEK + ableseserviceWasser)

							+ (preisProEinheitWaser * zaehlerstandWasserGMK + ableseserviceWasser)
							/ allApt.get(i).getHouse().getAnzahlWohnungen());
			// Strom EK + GMK
			result[i][2][0] = allApt.get(i).getAptID() + "#Strom";
			result[i][2][1] = Double
					.toString((preisProEinheitStrom * zaehlerstandStromEK + ableseserviceStrom)
							+ (preisProEinheitStrom * zaehlerstandStromGMK + ableseserviceStrom)
							/ allApt.get(i).getHouse().getAnzahlWohnungen());
			// Auftraege GMK Rasen mähen,Gartenpflege,Fußwege, räumen,Salz
			// streuen,Fensterreinigung, Treppenreinigung
			// Rasen maehen
			result[i][3][0] = allApt.get(i).getAptID() + "#Rasen maehen";
			result[i][3][1] = "";
			// Salz streuen
			result[i][4][0] = allApt.get(i).getAptID() + "#Salz streuen";
			// Fensterreinigung
			result[i][5][0] = allApt.get(i).getAptID() + "#Fensterreinigung";
			// Treppenreinigung
			result[i][6][0] = allApt.get(i).getAptID() + "#Teppichreinigung";

			// An Verwaltung senden
		}
	}

	public static void year() {

	}

	public static TimeChange getInstance() {
		return instance;
	}

}
