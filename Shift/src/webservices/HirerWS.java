package webservices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface HirerWS {

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
}
