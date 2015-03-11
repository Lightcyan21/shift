package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class Order extends AbstractEntity {

	private static final long serialVersionUID = 7664079950603416621L;
	// Auftrag für GS
	// nicht mehr benutzen, da MySQL nicht "Order" akzeptiert
	private long orderID;
	private Apartment wohnung;
	private double betrag;
	private int art;

	public Apartment getWohnID() {
		return wohnung;
	}

	public void setWohnID(Apartment wohnID) {
		this.wohnung = wohnID;
	}

	public double getBetrag() {
		return betrag;
	}

	public void setBetrag(double betrag) {
		this.betrag = betrag;
	}

	public int getArt() {
		return art;
	}

	public void setArt(int art) {
		this.art = art;
	}

	@Override
	public Long getId() {

		return orderID;
	}

	@Override
	public void setId(Long id) {
		orderID = id;
	}

	public void orderCheck(Order order) {
		
	}

}
