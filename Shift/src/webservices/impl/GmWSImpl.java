package webservices.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import webservices.GmWS;

@WebService(endpointInterface = "webservices.GmWS")
public class GmWSImpl implements GmWS {

	@Override
	@WebMethod
	public ArrayList<String> exposeSend(String plz, String street,
			String streetNumber, String city, int levels, int numberFlat,
			double gardenarea, double totalArea, double[] apartmentArea,
			int[] roomNumbers, int[] lvl) {

		return null;
	}

	@Override
	@WebMethod
	public String setHirer(int NumberOfHirers, String apartmentID) {
		System.out.println("läuft");
		return "Test war erfolgreich";
	}

	@Override
	@WebMethod
	public ArrayList<HashMap<String, Double>> getUtilities() {
		System.out.println("Der Test war erfolgreich.");
		HashMap<String, Double> hm = new HashMap<String, Double>();
		hm.put("test", (double) 2);
		ArrayList<HashMap<String, Double>> al = new ArrayList<>();
		al.add(hm);
		return al;
	}

	@Override
	@WebMethod
	public ArrayList<String> getInfo(String apartmentID) {

		return null;
	}

	@Override
	@WebMethod
	public String checkStatus(int orderID) {

		return null;
	}

	@Override
	@WebMethod
	public int sendOrder(String name, String apartmentID) {

		return 0;
	}

}
