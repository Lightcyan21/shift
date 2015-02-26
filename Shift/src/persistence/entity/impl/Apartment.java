package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class Apartment extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1946383270081409190L;
	private int wohnID;
	private double wohnflaeche;
	private int zimmeranzahl;
	private int mieteranzahl;

	public int getWohnID() {
		return wohnID;
	}

	public void setWohnID(int wohnID) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	// public Apartment(int wohnID, double wohnflaeche, int zimmeranzahl,
	// int mieteranzahl) {
	// super();
	// this.wohnID = wohnID;
	// this.wohnflaeche = wohnflaeche;
	// this.zimmeranzahl = zimmeranzahl;
	// this.mieteranzahl = mieteranzahl;
	// }
	//
	// public Apartment(int wohnID, double wohnflaeche, int zimmeranzahl,
	// int mieteranzahl, int mieterID) {
	// super();
	// this.wohnID = wohnID;
	// this.wohnflaeche = wohnflaeche;
	// this.zimmeranzahl = zimmeranzahl;
	// this.mieteranzahl = mieteranzahl;
	// }
	//
	// public void addMieterAnzahl(int mieterAnzahl) {
	//
	// }

}
