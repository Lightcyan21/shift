package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class Bill extends AbstractEntity {

	private static final long serialVersionUID = -4535878538723747602L;
	private long billID;
	private String rechnungssteller;
	private String rechnungsEmpfaenger;
	private double betrag;
	private String verwendungszweck;
	private String rechnungsdatum;
	private String zahlungsdatum;

	public String getRechnungssteller() {
		return rechnungssteller;
	}

	public void setRechnungssteller(String rechnungssteller) {
		this.rechnungssteller = rechnungssteller;
	}

	public String getRechnungsEmpfaenger() {
		return rechnungsEmpfaenger;
	}

	public void setRechnungsEmpfaenger(String rechnungsEmpfaenger) {
		this.rechnungsEmpfaenger = rechnungsEmpfaenger;
	}

	public double getBetrag() {
		return betrag;
	}

	public void setBetrag(double betrag) {
		this.betrag = betrag;
	}

	@Override
	public Long getId() {
		return billID;
	}

	@Override
	public void setId(Long id) {
		billID = id;
	}

	public String getVerwendungszweck() {
		return verwendungszweck;
	}

	public void setVerwendungszweck(String verwendungszweck) {
		this.verwendungszweck = verwendungszweck;
	}

	public String getRechnungsdatum() {
		return rechnungsdatum;
	}

	public void setRechnungsdatum(String rechnungsdatum) {
		this.rechnungsdatum = rechnungsdatum;
	}

	public String getZahlungsdatum() {
		return zahlungsdatum;
	}

	public void setZahlungsdatum(String zahlungsdatum) {
		this.zahlungsdatum = zahlungsdatum;
	}
}
