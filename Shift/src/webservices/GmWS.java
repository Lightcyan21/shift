package webservices;

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
	 * forbidden. The service will return an String array, with all the ids of
	 * the apartments, if everything is okay with your message input. Else it
	 * will return an error message in the first array slot, with the text:
	 * "Fehler aufgetreten, bitte Eingabe ueberpruefen"
	 * 
	 * @param plz
	 *            Postleitzahl 5stelliger String
	 * @param street
	 *            Straße, vollständig ausgeschrieben
	 * @param streetNumber
	 *            Hausnummer
	 * @param city
	 *            Stadt e.g. "Hamburg", "Berlin"
	 * @param levels
	 *            Anzahl der Etagen
	 * @param numberFlat
	 *            Anzahl der Wohnungen
	 * @param gardenarea
	 *            Nutzfläche des Gartens des Gebaeudes in m²
	 * @param totalArea
	 *            gesamte Wohnflaeche des Gebaeudes in m²
	 * @param apartmentArea
	 *            Array mit den Wohnflaechen der einzelnen WOhnung
	 * @param roomNumbers
	 *            Array mit Anzahl der Zimmer in den Wohnungen
	 * @param lvl
	 *            Array mit Stockwerk der jeweiligen Wohnung
	 * @return a StringArray with ids of the apartments or an error message in
	 *         the first array slot, with the text:
	 *         "Fehler aufgetreten, bitte Eingabe ueberpruefen"
	 */
	@WebMethod
	String[] exposeSend(String plz, String street, String streetNumber,
			String city, int levels, int numberFlat, double gardenarea,
			double totalArea, double[] apartmentArea, int[] roomNumbers,
			int[] lvl);

	/**
	 * You can use this webservice to add a new Hirer to an object. If the
	 * number of hirers is 0, then it means the apartment is empty. apartmentID
	 * is always a String with following structure: [houseID].[Level].[AptID]
	 * 
	 * @param NumberOfHirers
	 *            is the number of different hirers in the apartment
	 * @param apartmentID
	 *            is the unique id of the apartment
	 * 
	 * @return a String “Mieter erfolgreich hinzugefügt”, if the process was
	 *         successfull, else there will be an errormessage
	 *         "Fehler aufgetreten, bitte Eingabe ueberpruefen"
	 */
	@WebMethod
	public String setHirer(int NumberOfHirers, String apartmentID);

	/**
	 * You can use this webservice to get the all information about the
	 * apartment. apartmentID is always a String with following structure:
	 * [houseID].[Level].[AptID]. If the id is wrong, there will be an
	 * errormessage "Fehler aufgetreten, bitte Eingabe ueberpruefen"
	 * 
	 * @param apartmentID
	 *            unique identifier of the apartment
	 * 
	 * @return a String-Array which contains the following informations in
	 *         exactly this order in the array: Area, Number of rooms, Plz,
	 *         Location, Street, Street number. If there is no apt with this ID
	 *         it will return an error message:
	 *         "Fehler aufgetreten, bitte Eingabe ueberpruefen"
	 */
	@WebMethod
	String[] getInfo(String apartmentID);

	/**
	 * You can use this webservice to check the status of a specified order. The
	 * status is expressed as a String value.
	 * 
	 * @param orderID
	 *            is the unique identifier of the order
	 * @return String status Status is declarated in "Angekommen" "In Arbeit"
	 *         "Erledigt + Rechnung versendet" "Abgelehnt" "Rechnung bezahlt"
	 * @see sendOrder
	 */
	@WebMethod
	String checkStatus(int orderID);

	/**
	 * You can use this webservice to deliver a specified order. The service
	 * will return the id of the order as a long value, if everything is okay
	 * with your input.if there isnt any Apartment with this ID or a service
	 * with this name, it will return 0.
	 * 
	 * @param typ
	 *            is the explizit description of the service, possible Strings
	 *            are: "Installation", "Treppenreinigung",
	 *            "Hecke schneiden","Schluesseldienst", "Reparatur",
	 *            "Instandhaltung"
	 * @param apartmentID
	 *            id of the apartment, which has ordered the service
	 * @param mieter
	 *            full name of the hirer
	 * @return a long which is the orderID
	 * @see checkStatus
	 */
	@WebMethod
	long sendOrder(String typ, String apartmentID, String mieter);

	/**
	 * you can use this Webservice to send 'Shift Gebaeudemanagement' a Bill.
	 * 
	 * @param verwendungszweck
	 *            welche die Rechnung eindeutig identifziert (nach Vorgabe der
	 *            Bank)
	 * @param sender
	 *            der Absender der Rechnung mit dem offiziellen Kürzel
	 * @param rechnungsersteller
	 *            mit dem offiziellen kürzel
	 * @param rechnungsempfaenger
	 *            mit dem offiziellen Kürzel
	 * @param betrag
	 *            mit dem Betrag der Rechung
	 * @param rechnungsdatum
	 *            als String mit dem Format "DD.MM.YYYY"
	 * @param zahlungsdatum
	 *            als String mit dem Format "DD.MM.YYYY"
	 * @return String "Rechnung angekommen", if the bill successfull transfered
	 *         to us. We will send it to "Buchhaltung" else it will return
	 *         "Fehler aufgetreten, bitte Eingabe ueberpruefen" and do nothing.
	 */
	@WebMethod
	String erfasseRechnung(String verwendungszweck, String sender,
			String rechnungsersteller, String rechnungsempfaenger,
			double betrag, String rechnungsdatum, String zahlungsdatum);

	/**
	 * you can use this webservice to send us a time update
	 * 
	 * @param year
	 *            the current year.
	 * @param month
	 *            the current month as an int (0 = January) (11 = december)
	 * @param day
	 *            the current day
	 * @return a returncode, 100 means everything is fine, 401 if the time jump
	 *         cant be calculated, 402 if the class 'zeitsprung' does not return
	 *         an Integer or String
	 */
	@WebMethod
	int pushDate(int year, int month, int day);

}
