package webservices.impl;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import components.Definitions;

import persistence.dao.impl.ApartmentDAO;
import persistence.dao.impl.BillDAO;
import persistence.dao.impl.HouseDAO;
import persistence.dao.impl.OrderDAO;
import persistence.entity.impl.Apartment;
import persistence.entity.impl.House;
import persistence.entity.impl.Order;
import util.TimeChange;
import webservices.GmWS;
import webservices.ServiceWS;
import webservices.ServiceWSImplService;
import baldoapp.Zeitsprung;

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
			result[0] = Definitions.ERROR_MESSAGE;
			return result;
		}
	}

	@Override
	@WebMethod
	public String setHirer(int NumberOfHirers, String apartmentID) {
		ApartmentDAO aptdao = new ApartmentDAO();
		Apartment apt = aptdao.getApartment(apartmentID);
		if (apt == null) {
			return Definitions.ERROR_MESSAGE;
		} else {
			apt.setMieteranzahl(NumberOfHirers);
			aptdao.persist(apt);
			return "Mieter erfolgreich hinzugefügt.";
		}
	}

	@Override
	@WebMethod
	public String[] getInfo(String apartmentID) {
		// TODO AptID pruefen
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
		//TODO  order ID prüfen
		OrderDAO orderdao = new OrderDAO();
		Order order = orderdao.getById(orderID);
		if (order == null) {
			return Definitions.ERROR_MESSAGE;
		}
		ServiceWSImplService gsservice = new ServiceWSImplService();
		ServiceWS gs = gsservice.getServiceWSImplPort();
		return gs.getState(orderID);
	}

	@Override
	@WebMethod
	public long sendOrder(String typ, String apartmentID, String mieter) {
		// TODO AptID pruefen
		// TODO typen mit GS abstimmen
		OrderDAO orderdao = new OrderDAO();
		ApartmentDAO aptdao = new ApartmentDAO();
		Apartment apt = aptdao.getApartment(apartmentID);
		House house = apt.getHouse();
		int size = 0;
		switch (typ) {
		case "Treppenreinigung":
			size = house.getStockwerke();
			break;
		case "Instandhaltung":
			size = house.getAnzahlWohnungen();
			break;
		case "Schlüsseldienst":
			size = 1;
			break;
		case "Installationen":
			size = house.getAnzahlWohnungen();
			break;
		case "Reparaturen":
			size = 1;
			break;
		case "Hecke schneiden":
			size = 1;
			break;
		default:
			return 0;
		}

		Order order = orderdao.create();
		order.setWohnungsID(apartmentID);
		order.setJobName(typ);
		order.setMieter(mieter);
		orderdao.persist(order);
		ServiceWSImplService gsservice = new ServiceWSImplService();
		ServiceWS gs = gsservice.getServiceWSImplPort();

		if (gs.sendOrderToFm(typ, Integer.parseInt(apartmentID), size, 0) != "Auftrag wurde angenommen.") {
			return 0;
		} else {
			return order.getId();
		}

	}

	@Override
	@WebMethod
	public String erfasseRechnung(String verwendungszweck, String sender,
			String rechnungsersteller, String rechnungsempfaenger,
			double betrag, String rechnungsdatum, String zahlungsdatum) {
		SchnittstellenimplService bhservice = new SchnittstellenimplService();
		SchnittstelleBH bh = bhservice.getSchnittstellenimplPort();
		String ergebnis = bh.erfasseRechnung(verwendungszweck, "GM",
				rechnungsersteller, rechnungsempfaenger, betrag, rechnungsdatum, zahlungsdatum);
		System.out.println(ergebnis);
		if (ergebnis == "Rechnung angekommen"){
			return "Rechnung angekommen";
		}
		else {
			return Definitions.ERROR_MESSAGE;
		}
	}

	@Override
	@WebMethod
	public int pushDate(int year, int month, int day) {
		Date localDate = null;
		Zeitsprung zeitsprung = new Zeitsprung(localDate);
		System.out.println("localDate - zu Beginn der Methode: "
				+ localDate.getDate() + "." + (localDate.getMonth() + 1) + "."
				+ localDate.getYear());

		Date sprungDate = new Date(year, month, day);
		String sprungArt = null;
		int returncode = 100;
		Object o = zeitsprung.bestimmeVorgehen(sprungDate);
		if (o instanceof Integer) {
			returncode = (Integer) o;

		} else if (o instanceof String) {
			sprungArt = (String) o;
			switch (sprungArt) {
			case "day":
				break;
			case "halfMonth":
				break;
			case "month":
				/*
				 * Abarbeitung der erforderlichen Anwendungsschritte bei einem
				 * Monatssprung - in der Methode
				 */
				TimeChange.getInstance().month(month);
				break;
			case "year":
				/*
				 * Abarbeitung der erforderlichen Anwendungsschritte bei einem
				 * Jahressprung - in der Methode
				 */
				TimeChange.getInstance().year();
				break;

			default:
				/* Defaulthandling */
				returncode = 401;
			}

		} else {
			// Object o ist weder vom Typ Integer noch von String
			returncode = 402;
		}

		System.out.println("Object: " + o.toString());
		System.out.println("sprungArt: " + sprungArt);
		System.out.println("returncode: " + returncode);
		System.out.println("aktuelles localDate - nach der Methode: "
				+ localDate.getDate() + "." + (localDate.getMonth() + 1) + "."
				+ localDate.getYear());
		return returncode;
	}

}
