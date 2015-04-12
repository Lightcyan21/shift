
package components;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ui.controller.MainWindowController;
import util.TimeChange;

public class ShiftFrame extends JFrame {

	private static final long serialVersionUID = 5142346220834009864L;
	private CardLayout cardlayout;
	private Dimension screensize;
	private ShiftPanel2 contentpanel;
	private static ShiftFrame instance;
	private JLabel title;
	private JLabel datum;

	public ShiftFrame() throws HeadlessException {
		super();
		screensize = Toolkit.getDefaultToolkit().getScreenSize();

		// Setzen der Fenstereinstellungen
		setSize(screensize);
		setTitle(Definitions.WINDOW_TITLE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(
				(int) Math.round(screensize.getWidth() * 0.85),
				(int) Math.round(screensize.getHeight() * 0.5)));
		setMaximumSize(screensize);
		setPreferredSize(screensize);

		// Schließen der Anwendung bei Klick auf X
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Image für Taskleiste setzen
		try {
			ImageIcon ii = new ImageIcon("res/small.png");
			setIconImage(ii.getImage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Definition aller Panel
		ShiftPanel logopanel = new ShiftPanel();
		contentpanel = new ShiftPanel2();
		ShiftPanel northpanel = new ShiftPanel();
		// ShiftPanel filler = new ShiftPanel();
		// ShiftPanel filler2 = new ShiftPanel();
		ShiftPanel datumspanel = new ShiftPanel();
		ShiftPanel eastpanel = new ShiftPanel();
		ShiftPanel westpanel = new ShiftPanel();
		ShiftPanel southpanel = new ShiftPanel();

		// Filler eigenschaften
		Dimension minSize = new Dimension(50, 15);
		Dimension prefSize = new Dimension(50, 15);
		Dimension maxSize = new Dimension(50, 15);
		Dimension datumsize = new Dimension(175, 50);

		// Datum oben rechts
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyy");
		 datum = new JLabel(
				sdf.format(TimeChange.getInstance().getTime()));
		datum.setFont(new Font("Arial Black", Font.BOLD, 15));
		datum.setForeground(Color.black);
		datumspanel.setLayout(new BoxLayout(datumspanel, BoxLayout.PAGE_AXIS));
		datumspanel.add(new Box.Filler(datumsize, datumsize, datumsize));
		datumspanel.add(datum);

		// Logo oben links
		ImageIcon logoimage = new ImageIcon("res/image2.png");
		JLabel logo = new JLabel(logoimage);
		logopanel.setLayout(new BoxLayout(logopanel, BoxLayout.PAGE_AXIS));
		logopanel.add(new Box.Filler(minSize, prefSize, maxSize));
		logo.setMinimumSize(new Dimension(68, 70));
		logopanel.add(logo);
		logopanel.add(new Box.Filler(minSize, prefSize, maxSize));
		logopanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				MainWindowController.getInstance();

			}
		});

		// Titel
		title = new JLabel(Definitions.TITLE);
		title.setForeground(Color.white);
		title.setFont(new Font("Arial Black", Font.BOLD, 50));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Layout des gesamten Fensters
		setLayout(new BorderLayout());

		// nördliches Panel
		northpanel.setLayout(new GridLayout(1, 3, 50, 50));
		northpanel.add(logopanel);
		// northpanel.add(filler);
		northpanel.add(title);
		// northpanel.add(filler2);
		northpanel.add(datumspanel);

		// inneres Layout
		cardlayout = new CardLayout();
		contentpanel.setLayout(cardlayout);

		// Filler links und rechts
		int space = Definitions.PADDING;
		Component east = Box.createRigidArea(new Dimension(space, 0));
		Component west = Box.createRigidArea(new Dimension(space, 0));
		Component south = Box.createRigidArea(new Dimension(0, space / 2));

		west.setBackground(Definitions.BG_COLOR);
		east.setBackground(Definitions.BG_COLOR);
		south.setBackground(Definitions.BG_COLOR);

		westpanel.add(west);
		eastpanel.add(east);
		southpanel.add(south);

		// Zusammenfuegen der Panels
		add(eastpanel, BorderLayout.EAST);
		add(westpanel, BorderLayout.WEST);
		add(northpanel, BorderLayout.NORTH);
		add(contentpanel, BorderLayout.CENTER);
		add(southpanel, BorderLayout.SOUTH);

		// Debug
		// logopanel.setBackground(Color.red);
		// northpanel.setBackground(Color.blue);
		// filler.setBackground(Color.black);

		// optionale Fenstereinstellungen
		// setResizable(false);
		// setFocusable(true);
		// setUndecorated(true);

		// sichtbar werden lassen
		setVisible(true);

	}

	public void setDatum() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		datum.setText(sdf.format(TimeChange.getInstance().getTime()));
		instance.validate();
	}

	public ShiftPanel2 getContentpanel() {
		return contentpanel;
	}

	public void setContentpanel(ShiftPanel2 contentpanel) {
		this.contentpanel = contentpanel;
	}

	public CardLayout getCardlayout() {
		return cardlayout;
	}

	public static ShiftFrame getInstance() {
		if (instance == null) {
			instance = new ShiftFrame();
		}
		return instance;

	}

	public void setHeadline(String title) {
		this.title.setText(title);
	}

}
