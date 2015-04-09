package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.java.dev.jaxb.array.StringArray;
import net.java.dev.jaxb.array.StringArrayArray;
import net.java.dev.jaxb.array.StringArrayArrayArray;
import persistence.dao.impl.ApartmentDAO;
import persistence.dao.impl.BillDAO;
import persistence.dao.impl.HouseDAO;
import persistence.dao.impl.InsuranceDAO;
import persistence.dao.impl.OrderDAO;
import persistence.entity.impl.Apartment;
import persistence.entity.impl.Bill;
import persistence.entity.impl.House;
import persistence.entity.impl.Insurance;
import persistence.entity.impl.Order;
import webservices.ServiceWS;
import webservices.ServiceWSImplService;
import webservices.impl.BuchhaltungWS;
import webservices.impl.BuchhaltungWsImplService;
import webservices.impl.VerwaltungWS;
import webservices.impl.VerwaltungWSImplService;

import com.sun.xml.internal.ws.client.ClientTransportException;
import components.Definitions;

public class TimeChange {

	private static TimeChange instance;
	private Date localDate;

	@SuppressWarnings("deprecation")
	public void month(int month) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		BillDAO billdao = new BillDAO();
		int betrag = 0;

		// monatliche Auftraege generieren
		monatlicheOrdersBeauftragen(month);

		// Nebenkosten berechnen und an Verwaltung senden
		StringArrayArray invoice = getUtilities(month);
		VerwaltungWSImplService vwservice = new VerwaltungWSImplService();
		VerwaltungWS ws = vwservice.getVerwaltungWSImplPort();
		try {
			if (ws.sendNebenkosten(invoice) == 0) {
				System.out.println("Nebenkosten nicht angekommen!");
				return;
			} else {
				System.out.println("Nebenkosten angekommen!");
			}
		} catch (ClientTransportException e) {
			System.out.println("Fehler bei Uebergabe der Nebenkosten an VW: "
					+ e.getMessage());
		}
		// Rechnung erstellen
		Bill bill = billdao.create();
		bill.setRechnungsEmpfaenger("VW");
		bill.setRechnungssteller("GM");
		Date date = TimeChange.getInstance().getTime();
		bill.setRechnungsdatum(sdf.format(date).toString());
		bill.setZahlungsdatum(sdf.format(new Date(date.getYear(), date
				.getMonth() + 1, date.getDay())));
		// Definition des Verwendungszwecks
		String verwendungszweck = "GM";
		for (int i = 8; i > Long.toString(bill.getId()).length(); i--) {
			verwendungszweck += "0";
		}
		bill.setVerwendungszweck(verwendungszweck.concat(Long.toString(bill
				.getId())));

		// Berechnen des Rechnungsbetrags
		List<Bill> billlist = billdao.findAll();

		// Mapping auf Month
		for (Bill bill2 : billlist) {
			if (month == (Integer.parseInt(bill2.getRechnungsdatum().substring(
					3, 5))) - 2) {
				betrag += bill2.getBetrag();
			}
		}

		bill.setBetrag(betrag * Definitions.gewinn);

		// speichern
		billdao.persist(bill);

		// Buchhaltungsrechnung + gezahlte Aufträge + gewinn
		BuchhaltungWsImplService bhservice = new BuchhaltungWsImplService();
		BuchhaltungWS bh = bhservice.getBuchhaltungWsImplPort();
		bh.erfasseRechnung(bill.getVerwendungszweck(), "GM",
				bill.getRechnungssteller(), bill.getRechnungsEmpfaenger(),
				bill.getBetrag(), bill.getRechnungsdatum(),
				bill.getZahlungsdatum());

	}

	private void monatlicheOrdersBeauftragen(int month) {
		ApartmentDAO aptdao = new ApartmentDAO();
		OrderDAO orderdao = new OrderDAO();
		HouseDAO housedao = new HouseDAO();

		House house = null;
		Order order = null;
		String result = null;
		ServiceWSImplService gsservice = new ServiceWSImplService();
		ServiceWS gs = gsservice.getServiceWSImplPort();
		List<Apartment> list = aptdao.findAll();
		for (Apartment apartment : list) {
			String[] arr = apartment.getAptID().split("\\.");
			house = housedao.getById(Long.parseLong(arr[0]));
			String aptID = apartment.getAptID();

			if (month > 2 && month < 10) {
				// Rasen maehen
				order = orderdao.create();
				result = gs.sendOrderToFm(
						Definitions.RASEN_MAEHEN_STRING,
						aptID,
						(int) house.getGartenflaeche()
								/ house.getAnzahlWohnungen(), order.getId());
				if (result != "") {
					order.setWohnungsID(aptID);
					order.setStatusBestaetigung(true);
					order.setStatus(Definitions.ANGEKOMMEN);
					order.setJobName(Definitions.RASEN_MAEHEN_STRING);
					order.setBetrag(Definitions.rasen_maehen
							* (house.getGartenflaeche() / house
									.getAnzahlWohnungen()));
					order.setDatum(result);
					orderdao.persist(order);
				} else {
					System.out.println(Definitions.ERROR_MESSAGE);
				}

				// Gartenpflege
				order = orderdao.create();
				result = gs.sendOrderToFm(Definitions.GARTENPFLEGE_STRING,
						aptID, 1, order.getId());
				if (result != "") {
					order.setWohnungsID(aptID);
					order.setStatusBestaetigung(true);
					order.setStatus(Definitions.ANGEKOMMEN);
					order.setJobName(Definitions.GARTENPFLEGE_STRING);
					order.setBetrag(Definitions.gartenpflege);
					order.setDatum(result);
					orderdao.persist(order);
				} else {
					System.out.println(Definitions.ERROR_MESSAGE);
				}

			}

			if (month < 3 || month > 9) {
				// Fußweg raeumen
				order = orderdao.create();
				result = gs.sendOrderToFm(Definitions.FUSSWEG_RAEUMEN_STRING,
						aptID, 1, order.getId());
				if (result != "") {
					order.setWohnungsID(aptID);
					order.setJobName(Definitions.FUSSWEG_RAEUMEN_STRING);
					order.setBetrag(Definitions.fusswege_raeumen);
					order.setStatus(Definitions.ANGEKOMMEN);
					order.setStatusBestaetigung(true);
					order.setDatum(result);
					orderdao.persist(order);
				} else {
					System.out.println(Definitions.ERROR_MESSAGE);
				}

				// Salz streuen
				order = orderdao.create();
				result = gs.sendOrderToFm(Definitions.SALZ_STREUEN_STRING,
						aptID, 1, order.getId());
				if (result != "") {
					order.setWohnungsID(aptID);
					order.setJobName(Definitions.SALZ_STREUEN_STRING);
					order.setBetrag(Definitions.salz_streuen);
					order.setStatus(Definitions.ANGEKOMMEN);
					order.setStatusBestaetigung(true);
					order.setDatum(result);
					orderdao.persist(order);
				} else {
					System.out.println(Definitions.ERROR_MESSAGE);
				}

			}

			// Fensterreinigung
			order = orderdao.create();
			result = gs.sendOrderToFm(Definitions.FENSTERREINIGUNG_STRING,
					aptID, house.getStockwerke(), order.getId());
			if (result != "") {
				order.setWohnungsID(aptID);
				order.setJobName(Definitions.FENSTERREINIGUNG_STRING);
				order.setBetrag(Definitions.fensterreinigung);
				order.setStatus(Definitions.ANGEKOMMEN);
				order.setStatusBestaetigung(true);
				order.setDatum(result);
				orderdao.persist(order);

			} else {
				System.out.println(Definitions.ERROR_MESSAGE);
			}
			// Treppenreinigung
			order = orderdao.create();
			result = gs.sendOrderToFm(Definitions.TREPPENREINIGUNG_STRING,
					aptID, house.getStockwerke(), order.getId());
			if (result != "") {
				order.setWohnungsID(aptID);
				order.setJobName(Definitions.TREPPENREINIGUNG_STRING);
				order.setBetrag(Definitions.treppenreinigung);
				order.setStatus(Definitions.ANGEKOMMEN);
				order.setStatusBestaetigung(true);
				order.setDatum(result);
				orderdao.persist(order);

			} else {
				System.out.println(Definitions.ERROR_MESSAGE);
			}

		}
	}

	private StringArrayArrayArray getUtilities(int month) {
		// DAOs initialisieren
		ApartmentDAO aptdao = new ApartmentDAO();
		InsuranceDAO insudao = new InsuranceDAO();
		OrderDAO orderdao = new OrderDAO();
		HouseDAO housedao = new HouseDAO();

		List<Apartment> allApt = aptdao.findAll();
		int zaehlerstandGas = 0;
		int zaehlerstandWasserEK = 0;
		int zaehlerstandWasserGMK = 0;
		int zaehlerstandStromEK = 0;
		int zaehlerstandStromGMK = 0;
		String aptID = null;
		House house = null;
		Apartment apt = null;
		Insurance insurance = null;
		double rasen = 0;
		double salz = 0;
		double fensterreinigung = 0;
		double treppe = 0;
		double hecke = 0;
		double instandhaltung = 0;
		double schluessel = 0;
		double installation = 0;
		double reparatur = 0;

		// Ableseservice beauftragen
		ServiceWSImplService gsservice = new ServiceWSImplService();
		ServiceWS gs = gsservice.getServiceWSImplPort();
		StringArrayArray saa = new StringArrayArray();
		List<StringArray> abrechnung = saa.getItem();
		StringArray sa = null;
		List<String> wohnungsnk = null;
		String[][][] result = new String[allApt.size()][13][2];
		for (int i = 0; i < allApt.size(); i++) {
			sa = new StringArray();
			wohnungsnk = sa.getItem();

			apt = allApt.get(i);
			aptID = allApt.get(i).getAptID();
			String[] arr = aptID.split("\\.");
			house = housedao.getById(Long.parseLong(arr[0]));

			// 0 - wasser3000-5000, 1-strom 200-300, 2-gas,1000-1500
			zaehlerstandWasserEK = gs.getValue(0);
			zaehlerstandWasserGMK = gs.getValue(0);
			zaehlerstandStromEK = gs.getValue(1);
			zaehlerstandStromGMK = gs.getValue(1);
			zaehlerstandGas = gs.getValue(2);
			//
			// aufträge abrufen
			List<Order> orderlist = orderdao.getOrderByRequester(allApt.get(i)
					.getAptID());
			rasen = 0;
			salz = 0;
			fensterreinigung = 0;
			treppe = 0;
			hecke = 0;
			instandhaltung = 0;
			schluessel = 0;
			installation = 0;
			reparatur = 0;
			for (Order order : orderlist) {
				// pruefe, ob die Rechnung aus dem entsprechenden Monat ist
				if (Integer.parseInt(order.getDatum().split("\\.")[1]) == (month + 1)) {
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
					insurance = insudao.getByHouseID(house.getId());

					// hinzufuegen
					wohnungsnk.add(aptID);
					// Gas
					wohnungsnk
							.add(Double
									.toString(((Definitions.preisProEinheitGas
											* zaehlerstandGas + Definitions.ableseservicegas) / house
												.getFlaeche())
											* apt.getWohnflaeche()));
					// Wasser
					wohnungsnk
							.add(Double
									.toString((Definitions.preisProEinheitWaser
											* zaehlerstandWasserEK + Definitions.ableseserviceWasser)
											+ (Definitions.preisProEinheitWaser
													* zaehlerstandWasserGMK + Definitions.ableseserviceWasser)
											/ house.getAnzahlWohnungen()));
					// Strom
					wohnungsnk
							.add(Double
									.toString((Definitions.preisProEinheitStrom
											* zaehlerstandStromEK + Definitions.ableseserviceStrom)
											+ (Definitions.preisProEinheitStrom
													* zaehlerstandStromGMK + Definitions.ableseserviceStrom)
											/ house.getAnzahlWohnungen()));
					// rasen maehen
					wohnungsnk.add(Double.toString(rasen));
					// salz streuen
					wohnungsnk.add(Double.toString(salz));
					// fensterreinigung
					wohnungsnk.add(Double.toString(fensterreinigung));
					// treppe
					wohnungsnk.add(Double.toString(treppe));
					// hecke
					wohnungsnk.add(Double.toString(hecke));
					// instandhaltung
					wohnungsnk.add(Double.toString(instandhaltung));
					// schluessel
					wohnungsnk.add(Double.toString(schluessel));
					// installation
					wohnungsnk.add(Double.toString(installation));
					// reparatur
					wohnungsnk.add(Double.toString(reparatur));
					// verischerung
					wohnungsnk.add(Double.toString(insurance.getBetrag()
							/ house.getAnzahlWohnungen()));

					abrechnung.add(sa);

					// Gas GMK
					// result[i][0][0] = aptID + "#Gas";
					// result[i][0][1] =
					// Double.toString(((Definitions.preisProEinheitGas
					// * zaehlerstandGas + Definitions.ableseservicegas) / house
					// .getFlaeche()) * apt.getWohnflaeche());
					// // Wasser EK+GMK
					// result[i][1][0] = aptID + "#Wasser";
					// result[i][1][1] = Double
					// .toString((Definitions.preisProEinheitWaser
					// * zaehlerstandWasserEK + Definitions.ableseserviceWasser)
					//
					// + (Definitions.preisProEinheitWaser
					// * zaehlerstandWasserGMK +
					// Definitions.ableseserviceWasser)
					// / house.getAnzahlWohnungen());
					// // Strom EK + GMK
					// result[i][2][0] = aptID + "#Strom";
					// result[i][2][1] = Double
					// .toString((Definitions.preisProEinheitStrom
					// * zaehlerstandStromEK + Definitions.ableseserviceStrom)
					// + (Definitions.preisProEinheitStrom
					// * zaehlerstandStromGMK + Definitions.ableseserviceStrom)
					// / house.getAnzahlWohnungen());
					// Rasen maehen
					// result[i][3][0] = aptID + "#"
					// + Definitions.RASEN_MAEHEN_STRING;
					// result[i][3][1] = Double.toString(rasen);
					//
					// // Salz streuen
					// result[i][4][0] = aptID + "#"
					// + Definitions.SALZ_STREUEN_STRING;
					// result[i][4][1] = Double.toString(salz);
					//
					// // Fensterreinigung
					// result[i][5][0] = aptID + "#"
					// + Definitions.FENSTERREINIGUNG_STRING;
					// result[i][5][1] = Double.toString(fensterreinigung);
					//
					// // Treppenreinigung
					// result[i][6][0] = aptID + "#"
					// + Definitions.TREPPENREINIGUNG_STRING;
					// result[i][6][1] = Double.toString(treppe);
					//
					// // Hecke schneiden
					// result[i][7][0] = aptID + "#"
					// + Definitions.HECKE_SCHNEIDEN_STRING;
					// result[i][7][1] = Double.toString(hecke);
					//
					// // Instandhaltung
					// result[i][8][0] = aptID + "#"
					// + Definitions.INSTANDHALTUNG_STRING;
					// result[i][8][1] = Double.toString(instandhaltung);
					//
					// // Schlüsseldienst
					// result[i][9][0] = aptID + "#"
					// + Definitions.SCHLUESSELDIENST_STRING;
					// result[i][9][1] = Double.toString(schluessel);
					//
					// // Installation
					// result[i][10][0] = aptID + "#"
					// + Definitions.INSTALLATION_STRING;
					// result[i][10][1] = Double.toString(installation);
					//
					// // Reparatur
					// result[i][11][0] = aptID + "#"
					// + Definitions.REPARATUR_STRING;
					// result[i][11][1] = Double.toString(reparatur);
					//
					// // Versicherung
					// insurance = insudao.getByHouseID(house.getId());
					// result[i][12][0] = aptID + "#"
					// + Definitions.VERSICHERUNG_STRING;
					// result[i][12][1] = Double.toString(insurance.getBetrag()
					// / house.getAnzahlWohnungen());
				}
			}
		}
		return saa;

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
