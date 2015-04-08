package components;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ShiftButtonWohnInfo extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7641989212745317339L;

	public ShiftButtonWohnInfo (){
		
		setPreferredSize(new Dimension (170, 57));
		setIcon(new ImageIcon ("res/GebInfo.png"));
		
	}
	

}
