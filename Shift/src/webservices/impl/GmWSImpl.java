package webservices.impl;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import persistence.dao.impl.ApartmentDAO;
import persistence.dao.impl.HouseDAO;
import persistence.entity.impl.Apartment;
import persistence.entity.impl.House;
import webservices.GmWS;

@WebService(endpointInterface = "webservices.GmWS")
public class GmWSImpl implements GmWS {

	@Override
	@WebMethod
	public String[] exposeSend(String plz, String street, String streetNumber,
			String city, int levels, int numberFlat, double gardenarea,
			double totalArea, double[] apartmentArea, int[] roomNumbers,
			int[] lvl) {
		String[] result;

		if (apartmentArea.length == roomNumbers.length
				&& roomNumbers.length == lvl.length) {
			result = new String[apartmentArea.length];
			HouseDAO housedao = new HouseDAO();
			House house = housedao.create();
			house.setPlz(plz);
			house.setStrasse(street);
			house.setHausnr(streetNumber);
			house.setOrt(city);
			house.setStockwerke(levels);
			house.setAnzahlWohnungen(numberFlat);
			house.setGartenflaeche(gardenarea);
			house.setFlaeche(totalArea);
			housedao.persist(house);
			ApartmentDAO aptdao = new ApartmentDAO();
			Apartment apt;

			for (int i = 0; i < apartmentArea.length; i++) {
				apt = aptdao.createNew(house.getId() + "." + lvl[i] + "." + i);
				apt.setWohnflaeche(apartmentArea[i]);
				apt.setZimmeranzahl(roomNumbers[i]);
				apt.setAptID(house.getId() + "." + lvl[i] + "." + i);
				aptdao.persist(apt);
				result[i] = apt.getAptID();
			}
			return result;

		} else {
			result = new String[1];
			result[0] = "Fehler aufgetreten, bitte Eingabe ueberpruefen";
			return result;
		}
	}

	@Override
	@WebMethod
	public String setHirer(int NumberOfHirers, String apartmentID) {
		ApartmentDAO aptdao = new ApartmentDAO();
		Apartment apt = aptdao.getApartment(apartmentID);
		apt.setMieteranzahl(NumberOfHirers);
		aptdao.persist(apt);
		return "Mieter erfolgreich hinzugefügt.";
	}

	@Override
	@WebMethod
	public String[][][] getUtilities() {
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
			result[i][0][0] = allApt.get(i).getAptID()+"#Gas#Gemeinkosten";
			result[i][0][1] = Double.toString(((preisProEinheitGas
					* zaehlerstandGas + ableseservicegas)
					* gewinn / allApt.get(i).getHouse().getFlaeche())
					* allApt.get(i).getWohnflaeche());
			// Wasser EK+GMK
			result[i][1][0] = allApt.get(i).getAptID()+"#Wasser#Einzelkosten";
			result[i][1][1] = Double.toString((preisProEinheitWaser
					* zaehlerstandWasserEK + ableseserviceWasser)
					* gewinn);
			result[i][2][0] = allApt.get(i).getAptID()+"#Wasser#Gemeinkosten";
			result[i][2][1] = Double.toString(((preisProEinheitWaser
					* zaehlerstandWasserGMK + ableseserviceWasser) * gewinn)
					/ allApt.get(i).getHouse().getAnzahlWohnungen());
			// Strom EK + GMK
			result[i][3][0] = allApt.get(i).getAptID()+"#Strom#Gemeinkosten";
			result[i][3][1] = Double.toString((preisProEinheitStrom
					* zaehlerstandStromEK + ableseserviceStrom)
					* gewinn);
			result[i][4][0] = allApt.get(i).getAptID()+"#Strom#Einzelkosten";
			result[i][4][1] = Double.toString(((preisProEinheitStrom
					* zaehlerstandStromGMK + ableseserviceStrom) * gewinn)
					/ allApt.get(i).getHouse().getAnzahlWohnungen());
			// Auftraege GMK Rasen mähen,Gartenpflege,Fußwege, räumen,Salz
			// streuen,Fensterreinigung, Treppenreinigung
			//Rasen maehen
			result[i][5][0] = allApt.get(i).getAptID()+"#Rasen maehen#Gemeinkosten";
			result[i][5][1] = "";
			// Salz streuen
			result[i][6][0] = allApt.get(i).getAptID()+"#Salz streuen#Gemeinkosten";
			// Fensterreinigung
			result[i][7][0] = allApt.get(i).getAptID()+"#Fensterreinigung#Gemeinkosten";
			// Treppenreinigung
			result[i][8][0] = allApt.get(i).getAptID()+"#Teppichreinigung#Gemeinkosten";
			

		}

		return result;
	}

	@Override
	@WebMethod
	public String[] getInfo(String apartmentID) {
		// return a String-Array which contains
		String[] informations = new String[6];

		ApartmentDAO aptdao = new ApartmentDAO();
		HouseDAO housedao = new HouseDAO();
		Apartment apt = aptdao.getApartment(apartmentID);
		String houseid = apartmentID.substring(0, apartmentID.indexOf("."));
		House house = housedao.getById(Long.parseLong(houseid, 10));

		// Area
		informations[0] = apt.getWohnflaeche() + "";
		// Number of rooms
		informations[1] = apt.getZimmeranzahl() + "";
		// Plz
		informations[2] = house.getPlz();
		// Location
		informations[3] = house.getOrt();
		// Street
		informations[4] = house.getStrasse();
		// Street number
		informations[5] = house.getHausnr();
		return informations;

	}

	@Override
	@WebMethod
	public String checkStatus(int orderID) {
		// TODO Status von GS abrufen
		return null;
	}

	@Override
	@WebMethod
	public long sendOrder(String typ, String apartmentID, String mieter) {
		// TODO ggf. Auftrag parsen und an GS senden und ID zurückgeben
		return 0;
	}

}
