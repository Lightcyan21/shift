package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


import javax.swing.JButton;

public class ShiftButton2 extends JButton {
	
		private static final long serialVersionUID = -8086832785823587028L;

		public ShiftButton2(String text) {
			super(text);
			setFont(new Font("Arial Black", Font.BOLD, 20));
			setOpaque(false);
			setBackground(new Color (0,0,0,0));
			setPreferredSize(new Dimension(233, 73));
			setBorder (null);
			}
		
		
}
