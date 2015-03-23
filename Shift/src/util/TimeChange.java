package util;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import persistence.dao.impl.ApartmentDAO;
import persistence.dao.impl.HouseDAO;
import persistence.dao.impl.OrderDAO;
import persistence.entity.impl.Apartment;
import persistence.entity.impl.House;
import persistence.entity.impl.Order;
import ui.controller.OrderWindowController;
import ui.model.OrderWindowModel;
import ui.view.OrderWindowView;
import webservices.ServiceWS;
import webservices.ServiceWSImplService;
import webservices.impl.VerwaltungWS;
import webservices.impl.VerwaltungWSImplService;
import components.Definitions;

public class TimeChange {

	private static TimeChange instance;
	private Date localDate;

	public void month(int month) {

		// monatliche Auftraege generieren
		monatlicheOrdersBeauftragen(month);

		// Nebenkosten berechnen und an Verwaltung senden
		String[][][] invoice = getUtilities(month);
		VerwaltungWSImplService vwservice = new VerwaltungWSImplService();
		VerwaltungWS ws = vwservice.getVerwaltungWSImplPort();
		ws.getInvoice(0, 0);

		// Rechnungsposten addieren

		// Rechnung erstellen
		// Buchhaltungsrechnung + gezahlte Aufträge + gewinn
		for (int i = 0; i < invoice.length; i++) {

		}
		// an Verwaltung senden

	}

	private void monatlicheOrdersBeauftragen(int month) {
		ApartmentDAO aptdao = new ApartmentDAO();
		HouseDAO housedao = new HouseDAO();
		OrderDAO orderdao = new OrderDAO();
		House house = null;
		Order order = null;
		ServiceWSImplService gsservice = new ServiceWSImplService();
		ServiceWS gs = gsservice.getServiceWSImplPort();
		List<Apartment> list = aptdao.findAll();
		for (Apartment apartment : list) {
			house = apartment.getHouse();
			String aptID = apartment.getAptID();

			// Rasen maehen
			order = orderdao.create();
			// gs.sendOrderToFm(Definitions.RASEN_MAEHEN_STRING, aptID,
			// house.getGartenflaeche(), order.getId());
			order.setWohnungsID(aptID);
			order.setJobName(Definitions.RASEN_MAEHEN_STRING);
			order.setBetrag(Definitions.rasen_maehen * house.getGartenflaeche());
			orderdao.persist(order);

			// Gartenpflege
			order = orderdao.create();
			if (month > 3 && month < 9) {
				// gs.sendOrderToFm(Definitions.GARTENPFLEGE_STRING, aptID, 1,
				// order.getId());
			}
			order.setWohnungsID(aptID);
			order.setJobName(Definitions.GARTENPFLEGE_STRING);
			order.setBetrag(Definitions.gartenpflege);
			orderdao.persist(order);

			// Fußweg raeumen
			order = orderdao.create();
			if (month < 3 || month > 9) {
				// gs.sendOrderToFm(Definitions.FUSSWEG_RAEUMEN_STRING, aptID,
				// 1,
				// order.getId());
			}
			order.setWohnungsID(aptID);
			order.setJobName(Definitions.FUSSWEG_RAEUMEN_STRING);
			order.setBetrag(Definitions.fusswege_raeumen);
			orderdao.persist(order);

			// Salz streuen
			order = orderdao.create();
			if (month < 3 || month > 9) {
				// gs.sendOrderToFm(Definitions.SALZ_STREUEN_STRING, aptID, 1,
				// order.getId());
			}
			order.setWohnungsID(aptID);
			order.setJobName(Definitions.SALZ_STREUEN_STRING);
			order.setBetrag(Definitions.salz_streuen);
			orderdao.persist(order);

			// Fensterreinigung
			order = orderdao.create();
			// gs.sendOrderToFm(Definitions.FENSTERREINIGUNG_STRING, aptID,
			// house.getStockwerke(), order.getId());
			order.setWohnungsID(aptID);
			order.setJobName(Definitions.FENSTERREINIGUNG_STRING);
			order.setBetrag(Definitions.fensterreinigung);
			orderdao.persist(order);

		}
	}

	private String[][][] getUtilities(int month) {
		boolean sommer = false;
		if (1 < month && month < 11) {
			sommer = true;
		}
		ApartmentDAO aptdao = new ApartmentDAO();
		List<Apartment> allApt = aptdao.findAll();
		double zaehlerstandGas = 0;
		double zaehlerstandWasserEK = 0;
		double zaehlerstandWasserGMK = 0;
		double zaehlerstandStromEK = 0;
		double zaehlerstandStromGMK = 0;
		OrderDAO orderdao = new OrderDAO();
		orderdao.getById(10);

		String[][][] result = new String[allApt.size()][12][2];
		for (int i = 0; i < allApt.size(); i++) {
			// Ableseservice beauftragen
			// Gas GMK
			result[i][0][0] = allApt.get(i).getAptID() + "#Gas";
			result[i][0][1] = Double.toString(((Definitions.preisProEinheitGas
					* zaehlerstandGas + Definitions.ableseservicegas) / allApt
					.get(i).getHouse().getFlaeche())
					* allApt.get(i).getWohnflaeche());
			// Wasser EK+GMK
			result[i][1][0] = allApt.get(i).getAptID() + "#Wasser";
			result[i][1][1] = Double
					.toString((Definitions.preisProEinheitWaser
							* zaehlerstandWasserEK + Definitions.ableseserviceWasser)

							+ (Definitions.preisProEinheitWaser
									* zaehlerstandWasserGMK + Definitions.ableseserviceWasser)
							/ allApt.get(i).getHouse().getAnzahlWohnungen());
			// Strom EK + GMK
			result[i][2][0] = allApt.get(i).getAptID() + "#Strom";
			result[i][2][1] = Double
					.toString((Definitions.preisProEinheitStrom
							* zaehlerstandStromEK + Definitions.ableseserviceStrom)
							+ (Definitions.preisProEinheitStrom
									* zaehlerstandStromGMK + Definitions.ableseserviceStrom)
							/ allApt.get(i).getHouse().getAnzahlWohnungen());
			// Auftraege GMK Rasen mähen,Gartenpflege,Fußwege, räumen,Salz
			// streuen,Fensterreinigung, Treppenreinigung
			// Rasen maehen
			result[i][3][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.RASEN_MAEHEN_STRING;
			if (sommer) {
				result[i][3][1] = "";
			} else {
				result[i][3][1] = "0.00";
			}
			// Salz streuen
			result[i][4][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.SALZ_STREUEN_STRING;
			result[i][4][1] = "";
			// Fensterreinigung
			result[i][5][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.FENSTERREINIGUNG_STRING;
			result[i][5][1] = "";
			// Treppenreinigung
			result[i][6][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.TREPPENREINIGUNG_STRING;
			result[i][6][1] = "";
			// Hecke schneiden
			result[i][7][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.HECKE_SCHNEIDEN_STRING;
			result[i][7][1] = "";
			// Instandhaltung
			result[i][8][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.INSTANDHALTUNG_STRING;
			result[i][8][1] = "";
			// Schlüsseldienst
			result[i][9][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.SCHLUESSELDIENST_STRING;
			result[i][9][1] = "";
			// Installation
			result[i][10][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.INSTALLATION_STRING;
			result[i][10][1] = "";
			// Reparatur
			result[i][11][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.REPARATUR_STRING;
			result[i][11][1] = "";

		}
		return result;
	}

	public void year() {
	}

	public static TimeChange getInstance() {
		if (instance == null) {
			instance = new TimeChange();
		} 
		return instance;
	}

	public Date getTime() {
		return localDate;
	}
	public void setTime(Date localDate) {
		this.localDate = localDate;
	}

	public  void initTime() {
		 localDate = new Date(0);
	}

}
