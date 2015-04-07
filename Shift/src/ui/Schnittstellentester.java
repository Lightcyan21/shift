package ui;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jdom2.JDOMException;

import persistence.dao.impl.BillDAO;
import webservices.impl.BuchhaltungWS;
import webservices.impl.BuchhaltungWsImplService;
import baldoapp.ProjektXMLParser;

public class Schnittstellentester {

	public static void main(String[] args) {
		//
//		 OrderDAO orderdao = new OrderDAO();
//		 Order order = orderdao.create();
//		ServiceWSImplService gsservice = new ServiceWSImplService();
//		ServiceWS gs = gsservice.getServiceWSImplPort();
//		System.out.println(gs.sendOrderToFm(Definitions.GARTENPFLEGE_STRING,
//				"1.1.1", 1, order.getId()));
		// System.out.println(gs.getState(1));
		// 0 - wasser3000-5000, 1-strom 200-300, 2-gas,1000-1500
		// System.out.println( gs.getValue(0));
		// System.out.println( gs.getValue(1));
		// System.out.println( gs.getValue(2));
		// System.out.println(gs.getUnpaidBills()+"1");

		// -> hat alles funktioniert
		System.out.println("geht los");
		BillDAO billdao = new BillDAO();
		BuchhaltungWsImplService bhservice = new BuchhaltungWsImplService();
		BuchhaltungWS bh = bhservice.getBuchhaltungWsImplPort();
		String forderungslisteXML = bh.gebeForderungsliste("GM");
		System.out.println(forderungslisteXML);
		
		Map<String, String> forderungen = null;
		Map<String, String> forderungslisteMap = null;
		try {
			forderungslisteMap = ProjektXMLParser
					.XMLStringToMap(forderungslisteXML);
		} catch (JDOMException | IOException e) {
			System.out.println("Es ist ein Fehler aufgetreten: "
					+ e.getMessage());
		} 
		System.out.println(forderungslisteMap.size());
		String entry;
		Set<Entry<String, String>> test = forderungslisteMap.entrySet();
		System.out.println(test.size());
		for (Entry<String, String> entryXML : test) {
			entry = entryXML.getValue();
			System.out.println("Entry"+entry);
		}
//			try {
//				forderungen = ProjektXMLParser.XMLStringToMap(entry);
//			} catch (JDOMException | IOException e) {
//				System.out.println(e.getMessage());
//			}
//			for (Entry<String, String> entry2 : forderungen.entrySet()) {
//				System.out.println(entry2.getValue());
//			}
//		}
		
	}
}
