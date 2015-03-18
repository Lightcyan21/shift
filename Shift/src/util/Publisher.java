package util;

import javax.xml.ws.Endpoint;

import webservices.impl.GmWSImpl;

public class Publisher {

	private static Publisher instance;
	private static String adresse;

	@SuppressWarnings("unused")
	public Publisher() {
		// Switch für lokales Arbeiten und Remotearbeiten
		if (true) {
			adresse = "http://10.10.10.24:1000/ws";
		} else {
			adresse = "http://localhost:1000/ws/test";
		}
		// Webservices in eine einzelne Klasse zusammengefasst, die alten sind
		// zum Testen noch vorhanden werden aber nicht mehr genutzt!
		// Endpoint.publish(adresse + "Expose", new ExposeWSImpl());
		// Endpoint.publish(adresse, new HirerWSImpl());
		// Endpoint.publish(adresse + "Order", new OrderWSImpl());

		Endpoint.publish(adresse, new GmWSImpl());
		System.out.println("Webservices gestartet...");
	}

	public static Publisher getInstance() {
		if (instance == null) {
			instance = new Publisher();
		}
		return instance;
	}
}
