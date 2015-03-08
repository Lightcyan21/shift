package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class House extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3829466885552959540L;
	private Long houseID;
	private String plz;
	private String strasse;
	private String hausnr;
	private String ort;
	private double gartenflaeche;
	private int stockwerke;
	private int anzahlWohnungen;
	private double flaeche;

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnr() {
		return hausnr;
	}

	public void setHausnr(String hausnr) {
		this.hausnr = hausnr;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public double getGartenflaeche() {
		return gartenflaeche;
	}

	public void setGartenflaeche(double gartenflaeche) {
		this.gartenflaeche = gartenflaeche;
	}

	public int getStockwerke() {
		return stockwerke;
	}

	public void setStockwerke(int stockwerke) {
		this.stockwerke = stockwerke;
	}

	public int getAnzahlWohnungen() {
		return anzahlWohnungen;
	}

	public void setAnzahlWohnungen(int anzahlWohnungen) {
		this.anzahlWohnungen = anzahlWohnungen;
	}

	public double getFlaeche() {
		return flaeche;
	}

	public void setFlaeche(double flaeche) {
		this.flaeche = flaeche;
	}

	@Override
	public Long getId() {

		return houseID;
	}

	@Override
	public void setId(Long id) {
		houseID = id;
	}

}
