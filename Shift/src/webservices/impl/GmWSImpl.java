package webservices.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import webservices.GmWS;

@WebService(endpointInterface = "webservices.GmWS")
public class GmWSImpl implements GmWS {

	@Override
	@WebMethod
	public String[] exposeSend(String plz, String street,
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
	public String[][][] getUtilities() {
	
		return null;
	}

	@Override
	@WebMethod
	public String[] getInfo(String apartmentID) {
//		String[][][] array = new String[1][2][2];
//		array[0][0][0] = "test";
//		array[0][1][0] = "Alex is blöd";
//		array[0][1][1] = "Jonas ist cool";
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
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
