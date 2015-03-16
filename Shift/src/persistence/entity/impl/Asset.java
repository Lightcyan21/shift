package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class Asset extends AbstractEntity {

	private static final long serialVersionUID = -5856918652334582583L;
	private long assetID;
	private int billID;
	private double einzelpreis;
	private String bezeichnung;

	@Override
	public Long getId() {

		return assetID;
	}

	@Override
	public void setId(Long id) {
		assetID = id;
	}

	public int getBillID() {
		return billID;
	}

	public void setBillID(int billID) {
		this.billID = billID;
	}

	public double getEinzelpreis() {
		return einzelpreis;
	}

	public void setEinzelpreis(double einzelpreis) {
		this.einzelpreis = einzelpreis;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

}
