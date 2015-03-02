package ui;

import ui.controller.MainWindowController;
import util.Publisher;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MainWindowController.getInstance();
		Publisher.getInstance();
	}

}
