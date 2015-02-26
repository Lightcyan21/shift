package webservices;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ExposeWS {

	/**
	 * You can use this webservice to deliver a specified message to this
	 * endpoint. It has to be passed all parameters in which a null parameter is
	 * forbidden. The service will return "Danke. Nachricht angekommen", if
	 * everything is okay with your message input.
	 * 
	 * @param plz
	 *            Postleitzahl -> String
	 * @param street
	 *            Straﬂe
	 * @param streetNumber
	 *            Hausnummer
	 * @param city
	 *            Stadt
	 * @param levels
	 *            Anzahl der Etagen
	 * @param numerFlat
	 *            Anzahl der Wohnungen
	 * @param gardenarea
	 *            Nutzfl‰che des Gartens in m≤
	 * @param totalArea
	 *            gesamte Wohnflaeche
	 * @param apartmentArea
	 *            Array mit den Wohnflaechen der einzelnen WOhnungen
	 * @param roomNumbers
	 *            Arry mit Anzahl der Zimmer in den R‰umen
	 * @param lvl
	 *            Stockwerk der Wohnung
	 * @return a ArrayList<String> with ids of the apartments oder mit einer Fehlermeldung im 1. Element
	 */
	@WebMethod
	ArrayList<String> exposeSend(String plz, String street, String streetNumber,
			String city, int levels, int numberFlat, double gardenarea,
			double totalArea, double[] apartmentArea, int[] roomNumbers,
			int[] lvl);
}
