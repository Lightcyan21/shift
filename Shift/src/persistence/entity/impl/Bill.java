package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class Bill extends AbstractEntity {
	
	private static final long serialVersionUID = -4535878538723747602L;
	private long billID;
	private String rechnungssteller;
	private String rechnungsEmpfaenger;
	private double betrag;
	private String verwendungszweck;

	public long getBillID() {
		return billID;
	}

	public void setBillID(long billID) {
		this.billID = billID;
	}

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
		// nicht genutzt, da RechnungsID String ist.
		return null;
	}

	@Override
	public void setId(Long id) {
		// nicht genutzt, da RechnungsID String ist.
	}

	public String getVerwendungszweck() {
		return verwendungszweck;
	}

	public void setVerwendungszweck(String verwendungszweck) {
		this.verwendungszweck = verwendungszweck;
	}
}
