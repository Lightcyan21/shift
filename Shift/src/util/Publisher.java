package util;

import javax.xml.ws.Endpoint;

import webservices.impl.GmWSImpl;

public class Publisher {

	private static Publisher instance;
	private static String adresse;

	@SuppressWarnings("unused")
	public Publisher() {
		// TODO Port anpassen
		if (true) {
			adresse = "http://10.10.10.24:1000/ws";
		} else {
			adresse = "http://localhost:1000/ws/test";
		}
//		Endpoint.publish(adresse + "Expose", new ExposeWSImpl());
//		Endpoint.publish(adresse + "Hirer", new HirerWSImpl());
//		Endpoint.publish(adresse + "Order", new OrderWSImpl());
		
		Endpoint.publish(adresse, new GmWSImpl());
	}

	public static Publisher getInstance() {
		if (instance == null) {
			instance = new Publisher();
		}
		return instance;
	}
}
