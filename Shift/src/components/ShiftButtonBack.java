package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class ShiftButtonBack extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4624695990075710057L;

	public ShiftButtonBack(){
		super();
		setFont(new Font("Arial Unicode MS", Font.BOLD, 15));
		setText("\u25C4 Zurück");
		
		setPreferredSize(new Dimension(125,30));
//		setHorizontalAlignment(SwingConstants.C );
		
		setBackground(Color.LIGHT_GRAY);
		setForeground(Color.DARK_GRAY);
		
		
			
		
	
			
		
	}
	

}
