package webservices.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import webservices.OrderWS;

@WebService(endpointInterface = "webservices.OrderWS")
public class OrderWSImpl implements OrderWS {

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
