package webservices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


@WebService
@SOAPBinding(style = Style.RPC)
public interface GmWS {

	/**
	 * You can use this webservice to deliver a specified message to this
	 * endpoint. It has to be passed all parameters in which a null parameter is
	 * forbidden. The service will return "Danke. Nachricht angekommen", if
	 * everything is okay with your message input.
	 * 
	 * @param plz
	 *            Postleitzahl -> String
	 * @param street
	 *            Straße
	 * @param streetNumber
	 *            Hausnummer
	 * @param city
	 *            Stadt
	 * @param levels
	 *            Anzahl der Etagen
	 * @param numerFlat
	 *            Anzahl der Wohnungen
	 * @param gardenarea
	 *            Nutzfläche des Gartens in m²
	 * @param totalArea
	 *            gesamte Wohnflaeche
	 * @param apartmentArea
	 *            Array mit den Wohnflaechen der einzelnen WOhnungen
	 * @param roomNumbers
	 *            Arry mit Anzahl der Zimmer in den Räumen
	 * @param lvl
	 *            Stockwerk der Wohnung
	 * @return a ArrayList<String> with ids of the apartments oder mit einer Fehlermeldung im 1. Element
	 */
	@WebMethod
	ArrayList<String> exposeSend(String plz, String street, String streetNumber,
			String city, int levels, int numberFlat, double gardenarea,
			double totalArea, double[] apartmentArea, int[] roomNumbers,
			int[] lvl);
	/**
	 * You can use this webservice to add a new Hirer to an object. If the
	 * number of hirers is 0, then it means the apartment is empty.
	 * 
	 * @param NumberOfHirers
	 *            is the number of different hirers in the apartment
	 * @param apartmentID
	 *            is the unique id of the apartment
	 * 
	 * @return a String “Mieter erfolgreich hinzugefügt”, if the process was
	 *         successfull, else there will be an errormessage
	 */

	@WebMethod
	String setHirer(int NumberOfHirers, String apartmentID);

	/**
	 * You can use this webservice to get the utilities of every hirer. As
	 * result u get a List of HashMaps. Those Lists contains multiple type of
	 * the costs and their values.
	 * 
	 * 
	 * @return a List which contains multiple hashmaps, which contains at first
	 *         the id of the apartment(double=0.0) and the type of costs and
	 *         their values
	 */

	@WebMethod
	ArrayList<HashMap<String, Double>> getUtilities();

	/**
	 * You can use this webservice to get the all information about the
	 * apartment.
	 * 
	 * @param apartmentID
	 *            unique identifier of the apartment
	 * 
	 * @return a String-ArrayList which contains • Area • Number of rooms • Plz
	 *         • Location • Street • Street number
	 * 
	 * 
	 */
	@WebMethod
	ArrayList<String> getInfo(String apartmentID);
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
