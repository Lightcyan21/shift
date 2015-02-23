package model;

public class Bill {
	private String billID;
	private String rechnungssteller;
	private String rechnungsEmpfaenger;
	private double betrag;

	public Bill(String billID, String rechnungssteller,
			String rechnungsEmpfaenger, double betrag) {
		super();
		this.billID = billID;
		this.rechnungssteller = rechnungssteller;
		this.rechnungsEmpfaenger = rechnungsEmpfaenger;
		this.betrag = betrag;
	}

	public Bill() {
	}

	public void pushBill() {

	}

	public void storeBill() {

	}

	public void getStoredBill(String billID) {

	}
}
