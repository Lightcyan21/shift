package webservices.impl;

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
				apt = aptdao.create();
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
		return null;
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
