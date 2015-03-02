package webservices.impl;

import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;

import persistence.dao.impl.HouseDAO;

import webservices.ExposeWS;

@WebService(endpointInterface = "webservices.ExposeWS")
public class ExposeWSImpl implements ExposeWS {

	@SuppressWarnings("unused")
	@Override
	@WebMethod
	public ArrayList<String> exposeSend(String plz, String street,
			String streetNumber, String city, int levels, int numberFlat,
			double gardenarea, double totalArea, double[] apartmentArea,
			int[] roomNumbers, int[] lvl) {
		ArrayList<String> idlist = new ArrayList<>();
		if (true) {
			idlist.add("Es ist ein Fehler aufgetreten!");
		} else {
			HouseDAO housedao = new HouseDAO();
			housedao.create();
		}
		return idlist;
	}
	//halo

}
