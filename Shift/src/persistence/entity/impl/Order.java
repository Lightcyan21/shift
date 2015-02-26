package persistence.entity.impl;

import persistence.entity.AbstractEntity;

public class Order extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7664079950603416621L;
	// Auftrag für GS
	private int orderID;
	private Apartment wohnID;
	private double betrag;
	private int art;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Apartment getWohnID() {
		return wohnID;
	}

	public void setWohnID(Apartment wohnID) {
		this.wohnID = wohnID;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	// public Order(Apartment wohnID, int art) {
	// super();
	// this.wohnID = wohnID;
	// this.art = art;
	// }
	//
	// public void orderPush(Order order){
	//
	// }
	// public void orderCheck (Order order){
	//
	// }

}
