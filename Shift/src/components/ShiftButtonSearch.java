package components;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ShiftButtonSearch extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public ShiftButtonSearch(){
		super();
		
		setPreferredSize(new Dimension (125, 33));
		setIcon(new ImageIcon ("res/Suchen.png"));
		
	}

}
