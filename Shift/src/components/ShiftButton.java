package components;

import java.awt.Font;

import javax.swing.JButton;

public class ShiftButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8086832785823587028L;

	public ShiftButton(String text) {
		super(text);
		setFont(new Font("Arial Bold", Font.BOLD, 20));

	}

}
