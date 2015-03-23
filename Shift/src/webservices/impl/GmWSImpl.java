package webservices.impl;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;

import persistence.dao.impl.ApartmentDAO;
import persistence.dao.impl.BillDAO;
import persistence.dao.impl.HouseDAO;
import persistence.dao.impl.OrderDAO;
import persistence.entity.impl.Apartment;
import persistence.entity.impl.Bill;
import persistence.entity.impl.House;
import persistence.entity.impl.Order;
import util.TimeChange;
import webservices.GmWS;
import webservices.ServiceWS;
import webservices.ServiceWSImplService;
import baldoapp.Zeitsprung;

import components.Definitions;

@WebService(endpointInterface = "webservices.GmWS")
public class GmWSImpl implements GmWS {

	@Override
	@WebMethod
	public String[] exposeSend(String plz, String street, String streetNumber,
			String city, int levels, int numberFlat, double gardenarea,
			double totalArea, double[] apartmentArea, int[] roomNumbers,
			int[] lvl) {
		String[] result;
		System.out.println("Initialisiere Wohnungen eintragen...");
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
			System.out.println("Haus eingetragen...");
			ApartmentDAO aptdao = new ApartmentDAO();
			Apartment apt;

			for (int i = 0; i < apartmentArea.length; i++) {
				apt = aptdao.createNew(house.getId() + "." + lvl[i] + "." + i);
				apt.setWohnflaeche(apartmentArea[i]);
				apt.setZimmeranzahl(roomNumbers[i]);
				apt.setAptID(house.getId() + "." + lvl[i] + "." + i);
				aptdao.persist(apt);
				result[i] = apt.getAptID();
				System.out.println("WOhnung mit ID: " + apt.getAptID()
						+ "angelegt");
			}
			return result;

		} else {
			result = new String[1];
			result[0] = Definitions.ERROR_MESSAGE;
			System.out.println("Fehler beim Eitnragen der Wohnung.");
			return result;
		}
	}

	@Override
	@WebMethod
	public String setHirer(int NumberOfHirers, String apartmentID) {
		System.out.println("Neuer Mieter wird eingetragen...");
		ApartmentDAO aptdao = new ApartmentDAO();
		Apartment apt = aptdao.getApartment(apartmentID);
		if (apt == null) {
			System.out.println(Definitions.ERROR_MESSAGE);
			return Definitions.ERROR_MESSAGE;
		} else {
			apt.setMieteranzahl(NumberOfHirers);
			aptdao.persist(apt);
			System.out.println("Mieter erfolgreich hinzugefuegt...");
			return "Mieter erfolgreich hinzugefügt.";
		}
	}

	@Override
	@WebMethod
	public String[] getInfo(String apartmentID) {
		// TODO AptID pruefen
		// return a String-Array which contains
		System.out.println("Wohnungsinformationen abrufen...");
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
		System.out.println("Informationen erfolgreich abgerufen...");
		return informations;

	}

	@Override
	@WebMethod
	public String checkStatus(int orderID) {
		// TODO order ID prüfen
		System.out.println("Status abrufen angefragt...");
		OrderDAO orderdao = new OrderDAO();
		Order order = orderdao.getById(orderID);
		if (order == null) {
			return Definitions.ERROR_MESSAGE;
		}
		ServiceWSImplService gsservice = new ServiceWSImplService();
		ServiceWS gs = gsservice.getServiceWSImplPort();
		System.out.println("Status wird abgerufen...");
		return gs.getState(orderID);
	}

	@Override
	@WebMethod
	public long sendOrder(String typ, String apartmentID, String mieter) {
		// TODO AptID pruefen
		// TODO typen mit GS abstimmen
		System.out.println("Order erhalten...");
		OrderDAO orderdao = new OrderDAO();
		ApartmentDAO aptdao = new ApartmentDAO();
		Order order = orderdao.create();
		order.setWohnungsID(apartmentID);
		order.setJobName(typ);
		order.setMieter(mieter);
		order.setStatusWeiterleitung(false);
		order.setStatus(0);
		order.setStatusBestaetigung(false);
		orderdao.persist(order);
		System.out.println("Order gespeichert...");
		return order.getId();
	}

	@Override
	@WebMethod
	public String erfasseRechnung(String verwendungszweck, String sender,
			String rechnungsersteller, String rechnungsempfaenger,
			double betrag, String rechnungsdatum, String zahlungsdatum) {
		System.out.println("Rechnung erhalten...");
		BillDAO billdao = new BillDAO();
		Bill bill = billdao.create();
		bill.setBetrag(betrag);
		bill.setRechnungsEmpfaenger(rechnungsempfaenger);
		bill.setRechnungssteller(rechnungsersteller);
		bill.setVerwendungszweck(verwendungszweck);
		billdao.persist(bill);
		
		System.out.println("Rechnung an BH senden...");
		BuchhaltungWsImplService bhservice = new BuchhaltungWsImplService();
		BuchhaltungWS bh = bhservice.getBuchhaltungWsImplPort();
		String ergebnis = bh.erfasseRechnung(verwendungszweck, "GM",
				rechnungsersteller, rechnungsempfaenger, betrag,
				rechnungsdatum, zahlungsdatum);
		System.out.println(ergebnis);
		if (ergebnis.equals("Rechnung angekommen")) {
			return "Rechnung angekommen";
		} else {
			return Definitions.ERROR_MESSAGE;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	@WebMethod
	public int pushDate(int year, int month, int day) {
		System.out.println("Zeitsprung erhalten...");
		Date localDate = TimeChange.getInstance().getTime();
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
				System.out.println("Monatssprung...");
				TimeChange.getInstance().month(month);
				break;
			case "year":
				/*
				 * Abarbeitung der erforderlichen Anwendungsschritte bei einem
				 * Jahressprung - in der Methode
				 */
				System.out.println("Jahressprung");
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
