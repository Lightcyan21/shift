package persistence.entity.impl;

import persistence.dao.impl.HouseDAO;
import persistence.entity.AbstractEntity;

public class Apartment extends AbstractEntity {

	private static final long serialVersionUID = 1946383270081409190L;
	private String aptID;
	private double wohnflaeche;
	private int zimmeranzahl;
	private int mieteranzahl;
	@Deprecated
	private long houseID;
	
	public Apartment(){
	}
	@Deprecated
	public House getHouse() {
		HouseDAO housedao = new HouseDAO();
		House house = housedao.getById(houseID);
		return house;
	}

	public String getAptID() {
		return aptID;
	}

	public void setAptID(String aptID) {
		this.aptID = aptID;
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
	@Deprecated
	@Override
	public Long getId() {
		// nicht in Verwendung da Apartment keine Long ID verwendet
		return null;
	}
	@Deprecated
	@Override
	public void setId(Long id) {
		// nicht in Verwendung da Apartment keine Long ID verwendet
	}
}
