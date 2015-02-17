package components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShiftFrame extends JFrame {

	private static final long serialVersionUID = 5142346220834009864L;

	public ShiftFrame() throws HeadlessException {
		super();
		// Setzen der Fenstereisntellungen
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		//setBackground(Color.ORANGE);
		setLayout(new BorderLayout());
		JPanel jpanel = new JPanel();
		jpanel.setBackground(Color.ORANGE);
		add(jpanel,BorderLayout.CENTER);
		//Schlieﬂen der Anwendung bei Klick auf X
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		//setFocusable(true);
		//setUndecorated(true);
		//sichtbar werden lassen
		setVisible(true);
	}

	public ShiftFrame(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
