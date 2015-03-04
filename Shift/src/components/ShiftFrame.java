package components;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShiftFrame extends JFrame {

	private static final long serialVersionUID = 5142346220834009864L;
	private CardLayout layout;

	public ShiftFrame() throws HeadlessException {
		super();

		// Setzen der Fenstereinstellungen
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setTitle(Definitions.TITLE);
		// Schlieﬂen der Anwendung bei Klick auf X
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			ImageIcon ii = new ImageIcon("res/Logo2.png");
			setIconImage(ii.getImage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// innere Panels

		ShiftPanel bgpanel = new ShiftPanel();
		ShiftPanel logopanel = new ShiftPanel();
		ShiftPanel filler = new ShiftPanel();
		ShiftPanel contentpanel = new ShiftPanel();

		ImageIcon logoimage = new ImageIcon("res/Logo.png");

		JLabel logo = new JLabel(logoimage);

		// Layout
		setLayout(new GridLayout());

		//
		// GridBagLayout gbl = new GridBagLayout();
		// GridBagConstraints c = new GridBagConstraints();
		// c.gridx = 0;
		// c.gridy = 0;
		//
		// bgpanel.setLayout(new BorderLayout());
		// add(bgpanel, BorderLayout.CENTER);
		// logopanel.add(logo);
		// bgpanel.add(logopanel, BorderLayout.NORTH);
		// bgpanel.add(filler, BorderLayout.CENTER);

		add(logo);
		add(filler);
		add(contentpanel);
		add(logopanel);
		add(bgpanel);

		// setResizable(false);
		// setFocusable(true);
		// setUndecorated(true);
		// sichtbar werden lassen
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setVisible(true);
		pack();

	}

	public CardLayout getLayout() {
		return layout;
	}

	public void setLayout(CardLayout layout) {
		this.layout = layout;
	}

}
