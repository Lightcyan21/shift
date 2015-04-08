package components;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ShiftButtonWeiterleiten extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8146999485159432157L;

	public ShiftButtonWeiterleiten(){
		super();
		
		setPreferredSize(new Dimension (170, 57));
		//setMaximumSize(new Dimension (170, 57));
		setIcon(new ImageIcon ("res/Weiterleiten.png"));
	}
}
