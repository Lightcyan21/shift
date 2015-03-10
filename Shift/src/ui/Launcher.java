package ui;

import persistence.DBUtil;
import persistence.dao.impl.ApartmentDAO;
import persistence.entity.impl.Apartment;
import ui.controller.MainWindowController;
import util.Publisher;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MainWindowController.getInstance();
		Publisher.getInstance();
	
		
	}

}
