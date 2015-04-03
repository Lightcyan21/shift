package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import persistence.dao.impl.ApartmentDAO;
import persistence.dao.impl.BillDAO;
import persistence.dao.impl.OrderDAO;
import persistence.entity.impl.Apartment;
import persistence.entity.impl.Bill;
import persistence.entity.impl.House;
import persistence.entity.impl.Order;
import webservices.ServiceWS;
import webservices.ServiceWSImplService;
import webservices.impl.BuchhaltungWS;
import webservices.impl.BuchhaltungWsImplService;
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

		BillDAO billdao = new BillDAO();

		// Rechnung erstellen
		Bill bill = billdao.create();
		bill.setRechnungsEmpfaenger("VW");
		bill.setRechnungssteller("GM");

		// Definition des Verwendungszwecks
		String verwendungszweck = "GM";
		for (int i = 8; i > Long.toString(bill.getBillID()).length(); i--) {
			verwendungszweck += "0";
		}
		bill.setVerwendungszweck(verwendungszweck.concat(Long.toString(bill
				.getBillID())));

		// Berechnen des Rechnungsbetrags
		List<Bill> billlist = billdao.findAll();
		Bill billbh = billlist.get(billlist.size() - 2);
		Bill billgs = billlist.get(billlist.size() - 1);
		bill.setBetrag((billbh.getBetrag() + billgs.getBetrag())
				* Definitions.gewinn);

		// speichern
		billdao.persist(bill);

		// Buchhaltungsrechnung + gezahlte Aufträge + gewinn
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		BuchhaltungWsImplService bhservice = new BuchhaltungWsImplService();
		BuchhaltungWS bh = bhservice.getBuchhaltungWsImplPort();
		bh.erfasseRechnung(bill.getVerwendungszweck(), "GM", bill
				.getRechnungssteller(), bill.getRechnungsEmpfaenger(), bill
				.getBetrag(), sdf.format(TimeChange.getInstance().getTime())
				.toString(), sdf.format(TimeChange.getInstance().getTime())
				.toString());
	}

	private void monatlicheOrdersBeauftragen(int month) {
		ApartmentDAO aptdao = new ApartmentDAO();
		OrderDAO orderdao = new OrderDAO();
		House house = null;
		Order order = null;
		ServiceWSImplService gsservice = new ServiceWSImplService();
		ServiceWS gs = gsservice.getServiceWSImplPort();
		List<Apartment> list = aptdao.findAll();
		for (Apartment apartment : list) {
			house = apartment.getHouse();
			String aptID = apartment.getAptID();

			if (month > 2 && month < 10) {
				// Rasen maehen
				order = orderdao.create();
				gs.sendOrderToFm(
						Definitions.RASEN_MAEHEN_STRING,
						aptID,
						(int) house.getGartenflaeche()
								/ house.getAnzahlWohnungen(), order.getId());
				order.setWohnungsID(aptID);
				order.setJobName(Definitions.RASEN_MAEHEN_STRING);
				order.setBetrag(Definitions.rasen_maehen
						* (house.getGartenflaeche() / house
								.getAnzahlWohnungen()));
				orderdao.persist(order);

				// Gartenpflege
				order = orderdao.create();
				gs.sendOrderToFm(Definitions.GARTENPFLEGE_STRING, aptID, 1,
						order.getId());
				order.setWohnungsID(aptID);
				order.setJobName(Definitions.GARTENPFLEGE_STRING);
				order.setBetrag(Definitions.gartenpflege);
				orderdao.persist(order);
			}

			if (month < 3 || month > 9) {
				// Fußweg raeumen
				order = orderdao.create();
				gs.sendOrderToFm(Definitions.FUSSWEG_RAEUMEN_STRING, aptID, 1,
						order.getId());
				order.setWohnungsID(aptID);
				order.setJobName(Definitions.FUSSWEG_RAEUMEN_STRING);
				order.setBetrag(Definitions.fusswege_raeumen);
				orderdao.persist(order);

				// Salz streuen
				order = orderdao.create();
				gs.sendOrderToFm(Definitions.SALZ_STREUEN_STRING, aptID, 1,
						order.getId());
				order.setWohnungsID(aptID);
				order.setJobName(Definitions.SALZ_STREUEN_STRING);
				order.setBetrag(Definitions.salz_streuen);
				orderdao.persist(order);
			}

			// Fensterreinigung
			order = orderdao.create();
			gs.sendOrderToFm(Definitions.FENSTERREINIGUNG_STRING, aptID,
					house.getStockwerke(), order.getId());
			order.setWohnungsID(aptID);
			order.setJobName(Definitions.FENSTERREINIGUNG_STRING);
			order.setBetrag(Definitions.fensterreinigung);
			orderdao.persist(order);

		}
	}

	private String[][][] getUtilities(int month) {
		ApartmentDAO aptdao = new ApartmentDAO();
		List<Apartment> allApt = aptdao.findAll();
		int zaehlerstandGas = 0;
		int zaehlerstandWasserEK = 0;
		int zaehlerstandWasserGMK = 0;
		int zaehlerstandStromEK = 0;
		int zaehlerstandStromGMK = 0;
		OrderDAO orderdao = new OrderDAO();
		orderdao.getById(10);

		String[][][] result = new String[allApt.size()][12][2];
		for (int i = 0; i < allApt.size(); i++) {
			// Ableseservice beauftragen
			ServiceWSImplService gsservice = new ServiceWSImplService();
			ServiceWS gs = gsservice.getServiceWSImplPort();
			// 0 - wasser3000-5000, 1-strom 200-300, 2-gas,1000-1500

			zaehlerstandWasserEK = gs.getValue(0);
			zaehlerstandWasserGMK = gs.getValue(0);
			zaehlerstandStromEK = gs.getValue(1);
			zaehlerstandStromGMK = gs.getValue(1);
			zaehlerstandGas = gs.getValue(2);

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
			// aufträge abrufen
			List<Order> orderlist = orderdao.getOrderByRequester(allApt.get(i)
					.getAptID());
			double rasen = 0;
			double salz = 0;
			double fensterreinigung = 0;
			double treppe = 0;
			double hecke = 0;
			double instandhaltung = 0;
			double schluessel = 0;
			double installation = 0;
			double reparatur = 0;
			for (Order order : orderlist) {
				String jobname = order.getJobName();
				switch (jobname) {
				case Definitions.RASEN_MAEHEN_STRING:
					rasen = rasen + order.getBetrag();
					break;
				case Definitions.SALZ_STREUEN_STRING:
					salz = salz + order.getBetrag();
					break;
				case Definitions.FENSTERREINIGUNG_STRING:
					fensterreinigung = fensterreinigung + order.getBetrag();
					break;
				case Definitions.TREPPENREINIGUNG_STRING:
					treppe = treppe + order.getBetrag();
					break;
				case Definitions.HECKE_SCHNEIDEN_STRING:
					hecke = hecke + order.getBetrag();
					break;
				case Definitions.INSTANDHALTUNG_STRING:
					instandhaltung = instandhaltung + order.getBetrag();
					break;
				case Definitions.SCHLUESSELDIENST_STRING:
					schluessel = schluessel + order.getBetrag();
					break;
				case Definitions.INSTALLATION_STRING:
					installation = installation + order.getBetrag();
					break;
				case Definitions.REPARATUR_STRING:
					reparatur = reparatur + order.getBetrag();
					break;
				default:
					break;
				}
			}
			// Rasen maehen
			result[i][3][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.RASEN_MAEHEN_STRING;
			result[i][3][1] = Double.toString(rasen);

			// Salz streuen
			result[i][4][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.SALZ_STREUEN_STRING;
			result[i][4][1] = Double.toString(salz);

			// Fensterreinigung
			result[i][5][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.FENSTERREINIGUNG_STRING;
			result[i][5][1] = Double.toString(fensterreinigung);

			// Treppenreinigung
			result[i][6][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.TREPPENREINIGUNG_STRING;
			result[i][6][1] = Double.toString(treppe);

			// Hecke schneiden
			result[i][7][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.HECKE_SCHNEIDEN_STRING;
			result[i][7][1] = Double.toString(hecke);

			// Instandhaltung
			result[i][8][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.INSTANDHALTUNG_STRING;
			result[i][8][1] = Double.toString(instandhaltung);

			// Schlüsseldienst
			result[i][9][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.SCHLUESSELDIENST_STRING;
			result[i][9][1] = Double.toString(schluessel);

			// Installation
			result[i][10][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.INSTALLATION_STRING;
			result[i][10][1] = Double.toString(installation);

			// Reparatur
			result[i][11][0] = allApt.get(i).getAptID() + "#"
					+ Definitions.REPARATUR_STRING;
			result[i][11][1] = Double.toString(reparatur);

		}
		return result;
	}

	@SuppressWarnings("deprecation")
	public void year() {
		int month = localDate.getMonth();

		for (int i = 0; i < 12; i++) {
			if (month < 11) {
				month++;
			} else {
				month = 0;
			}
			month(month);
		}
		setTime(localDate);
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

	public void initTime() {
		localDate = new Date(0);
	}

}
