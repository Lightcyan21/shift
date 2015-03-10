package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class Apartment extends AbstractEntity {

	private static final long serialVersionUID = 1946383270081409190L;
	private String wohnID;
	private double wohnflaeche;
	private int zimmeranzahl;
	private int mieteranzahl;

	public String getWohnID() {
		return wohnID;
	}

	public void setWohnID(String wohnID) {
		this.wohnID = wohnID;
	}

	public double getWohnflaeche() {
		return wohnflaeche;
	}

	public void setWohnflaeche(double wohnflaeche) {
		this.wohnflaeche = wohnflaeche;
	}

	public int getZimmeranzahl() {
		return zimmeranzahl;
	}

	public void setZimmeranzahl(int zimmeranzahl) {
		this.zimmeranzahl = zimmeranzahl;
	}

	public int getMieteranzahl() {
		return mieteranzahl;
	}

	public void setMieteranzahl(int mieteranzahl) {
		this.mieteranzahl = mieteranzahl;
	}

	@Override
	public Long getId() {
		// nicht in Verwendung da Apartment keine Long ID verwendet
		return null;
	}

	@Override
	public void setId(Long id) {
		// nicht in Verwendung da Apartment keine Long ID verwendet
	}
}
