package webservices.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import webservices.HirerWS;

@WebService(endpointInterface = "webservices.HirerWS")
public class HirerWSImpl implements HirerWS {

	@Override
	@WebMethod
	public String setHirer(int NumberOfHirers, String apartmentID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public ArrayList<HashMap<String, Double>> getUtilities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public ArrayList<String> getInfo(String apartmentID) {
		// TODO Auto-generated method stub
		return null;
	}

}
