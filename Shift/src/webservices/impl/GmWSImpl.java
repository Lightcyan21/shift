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
		// TODO neues Expose anlegen -> weiteres Feld in Datenbank, ob schon
		// abgerufen für Darstellung im Menu
		// TODO oder eigene Tabelle
		// TODO Rückgabewert klären. Wie sollen die IDs zurückgegeben werden,
		// wenn wir erst manuell bestätigen müssen

		return null;
	}

	@Override
	@WebMethod
	public String setHirer(int NumberOfHirers, String apartmentID) {
		// TODO MIeter in Wohnung setzen und auf DB persistieren
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

		String[] informations = new String[6];
		// return a String-Array which contains • Area • Number of rooms • Plz •
		// Location • Street • Street number
		ApartmentDAO aptdao = new ApartmentDAO();
		HouseDAO housedao = new HouseDAO();
		Apartment apt = aptdao.getApartment(apartmentID);
		String houseid = apartmentID.substring(0, apartmentID.indexOf("."));
		House house = housedao.getById(Long.parseLong(houseid, 10));
		informations[0] = apt.getWohnflaeche() + "";
		informations[1] = apt.getZimmeranzahl() + "";
		informations[2] = house.getPlz();
		informations[3] = house.getOrt();
		informations[4] = house.getStrasse();
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
