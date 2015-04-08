package ui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import persistence.DBUtil;
import ui.controller.MainWindowController;
import util.Publisher;
import util.TimeChange;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel(
//					 UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException | InstantiationException
//				| IllegalAccessException | UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//		}
		TimeChange.getInstance().initTime();
		MainWindowController.getInstance();
		Publisher.getInstance();
		DBUtil.getConnection();

	}

}
