package model;

public class Order {
	// Auftrag f�r GS
	private int orderID;
	private Apartment wohnID;
	private double betrag;
	private int art;

	public Order(Apartment wohnID, int art) {
		super();
		this.wohnID = wohnID;
		this.art = art;
	}
	
	public void orderPush(Order order){
		
	}
	public void orderCheck (Order order){
		
	}

}
