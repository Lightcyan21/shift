package ui;

import ui.controller.MainWindowController;
import util.Publisher;
import util.TimeChange;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TimeChange.getInstance().initTime();
		MainWindowController.getInstance();
		Publisher.getInstance();

	}

}
