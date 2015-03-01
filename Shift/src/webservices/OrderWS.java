package webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface OrderWS {

	/**
	 * You can use this webservice to check the status of a specified order. The
	 * status is expressed as a String value (s. return value)
	 * 
	 * @param orderID
	 *            is the unique identifier of the order
	 * @return String status
	 * 
	 *         Status is declarated in "Angekommen" "In Arbeit" "Erledigt +
	 *         Rechnung versendet" "Abgelehnt" "Rechnung bezahlt"
	 */
	@WebMethod
	String checkStatus(int orderID);

	/**
	 * You can use this webservice to deliver a specified order to this
	 * endpoint. The service will return "Danke. Auftrag angekommen", if
	 * everything is okay with your input.
	 * 
	 * @param name
	 *            is the description of the service
	 * @param apartmentID
	 *            id of the apartment, which has ordered the service
	 * @return a int which is the orderID
	 */
	
	@WebMethod
	int sendOrder(String name, String apartmentID);
}
