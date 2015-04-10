package components;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ShiftButtonSearchTable extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5531647012545168329L;

	public ShiftButtonSearchTable() {
		super();

		setMaximumSize(new Dimension(100,20));
		setIcon(new ImageIcon("res/Suchen2.png"));

	}
}
