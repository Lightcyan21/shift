package components;

import java.awt.Dimension;
import java.awt.Font;

import javafx.scene.paint.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ShiftButtonBack extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4624695990075710057L;

	public ShiftButtonBack(){
		setFont(new Font("Arial Unicode MS", Font.BOLD, 20));
		setText("\u25C4 Zurück");
		//setBackground(Color.GREY);
		setPreferredSize(new Dimension(300,50));
		
		
		
	
		
		
	}
	

}
