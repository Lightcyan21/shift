package components;

import java.awt.CardLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShiftFrame extends JFrame {

	private static final long serialVersionUID = 5142346220834009864L;

	public ShiftFrame() throws HeadlessException {
		super();
		// Setzen der Fenstereinstellungen
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setTitle(Definitions.TITLE);
		// Schlieﬂen der Anwendung bei Klick auf X
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			ImageIcon ii = new ImageIcon("res/Logo2.png");
			System.out.println(ii.getImage().toString());
			System.out.println(ii.getImage() == null);
			setIconImage(ii.getImage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		// setResizable(false);
		// setFocusable(true);
		// setUndecorated(true);
		// sichtbar werden lassen
		setVisible(true);

		// Layout
		CardLayout cl = new CardLayout();
		setLayout(cl);
		JPanel jpanel = new JPanel();
		jpanel.setBackground(Definitions.BG_COLOR);
		add(jpanel, "Home");

	}

//	public ShiftFrame(String arg0) throws HeadlessException {
//		super(arg0);
//		// TODO Auto-generated constructor stub
//	}

}
