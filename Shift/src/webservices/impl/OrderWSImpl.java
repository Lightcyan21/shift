package webservices.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import webservices.OrderWS;

@WebService(endpointInterface = "webservices.ExposeWS")
public class OrderWSImpl implements OrderWS {

	

	@Override
	@WebMethod
	public
	String checkStatus(int orderID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod
	public
	int sendOrder(String name, String apartmentID) {
		// TODO Auto-generated method stub
		return 0;
	}

}
