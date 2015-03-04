package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class Bill extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4535878538723747602L;
	private String billID;
	private String rechnungssteller;
	private String rechnungsEmpfaenger;
	private double betrag;

	public String getBillID() {
		return billID;
	}

	public void setBillID(String billID) {
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

		return null;
	}

	@Override
	public void setId(Long id) {

	}

	// public Bill(String billID, String rechnungssteller,
	// String rechnungsEmpfaenger, double betrag) {
	// super();
	// this.billID = billID;
	// this.rechnungssteller = rechnungssteller;
	// this.rechnungsEmpfaenger = rechnungsEmpfaenger;
	// this.betrag = betrag;
	// }
	//
	// public Bill() {
	// }

	// public void pushBill() {
	//
	// }
	//
	// public void storeBill() {
	//
	// }
	//
	// public void getStoredBill(String billID) {
	//
	// }
}
