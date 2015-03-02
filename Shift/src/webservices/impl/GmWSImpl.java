package webservices.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import webservices.GmWS;

@WebService(endpointInterface = "webservices.HirerWS")
public class GmWSImpl implements GmWS {

	@Override
	@WebMethod
	public ArrayList<String> exposeSend(String plz, String street,
			String streetNumber, String city, int levels, int numberFlat,
			double gardenarea, double totalArea, double[] apartmentArea,
			int[] roomNumbers, int[] lvl) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public String setHirer(int NumberOfHirers, String apartmentID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public ArrayList<HashMap<String, Double>> getUtilities() {
		System.out.println("Der Test war erfolgreich.");
		return null;
	}

	@Override
	@WebMethod
	public ArrayList<String> getInfo(String apartmentID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public String checkStatus(int orderID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public int sendOrder(String name, String apartmentID) {
		// TODO Auto-generated method stub
		return 0;
	}

}
