package webservices.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import webservices.GmWS;

@WebService(endpointInterface = "webservices.GmWS")
public class GmWSImpl implements GmWS {

	@Override
	@WebMethod
	public String[] exposeSend(String plz, String street, String streetNumber,
			String city, int levels, int numberFlat, double gardenarea,
			double totalArea, double[] apartmentArea, int[] roomNumbers,
			int[] lvl) {

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
	public String[][][] getUtilities() {

		return null;
	}

	@Override
	@WebMethod
	public String[] getInfo(String apartmentID) {

		return null;
	}

	@Override
	@WebMethod
	public String checkStatus(int orderID) {

		return null;
	}

	@Override
	@WebMethod
	public long sendOrder(String typ, String apartmentID, String mieter) {
		System.out.println("ok");
		return 0; 	
	}

}
