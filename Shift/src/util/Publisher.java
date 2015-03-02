package util;

import javax.xml.ws.Endpoint;

import webservices.impl.ExposeWSImpl;
import webservices.impl.HirerWSImpl;
import webservices.impl.OrderWSImpl;

public class Publisher {

	private static Publisher instance;
	private static String adresse;

	@SuppressWarnings("unused")
	public Publisher() {
		// TODO Port anpassen
		if (true) {
			adresse = "http://10.10.10.24:1000/ws/";
		} else {
			adresse = "http://localhost:1000/ws/test";
		}
		Endpoint.publish(adresse, new ExposeWSImpl());
		Endpoint.publish(adresse, new HirerWSImpl());
		Endpoint.publish(adresse, new OrderWSImpl());
	}

	public static Publisher getInstance() {
		if (instance == null) {
			instance = new Publisher();
		}
		return instance;
	}
}
