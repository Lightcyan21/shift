package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class Admonition extends AbstractEntity {

	private static final long serialVersionUID = 8429005451293689378L;
	private long admonitionID;
	private String rechnungsVerwendungszweck;
	private double preis;
	private boolean seen;

	@Override
	public Long getId() {
		return admonitionID;
	}

	@Override
	public void setId(Long id) {
		admonitionID = id;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	public String getRechnungsVerwendungszweck() {
		return rechnungsVerwendungszweck;
	}

	public void setRechnungsVerwendungszweck(String rechnungsVerwendungszweck) {
		this.rechnungsVerwendungszweck = rechnungsVerwendungszweck;
	}

}
