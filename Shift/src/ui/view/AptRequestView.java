package ui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import ui.enums.UI_EVENT;

import components.Definitions;
import components.ShiftButton;
import components.ShiftFrame;
import components.ShiftPanel2;

public class AptRequestView extends AbstractView {

	private ShiftFrame frame;
	private JTextField textfield;
	private ShiftPanel2 table;

	public AptRequestView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {

		frame.getCardlayout().show(frame.getContentpanel(), "search");
		frame.setHeadline(Definitions.SEARCH);
		frame.validate();
		return null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {

	}

	@Override
	protected void initUI() {
		// initialisieren der Variablen
		frame = ShiftFrame.getInstance();
		frame.setHeadline(Definitions.SEARCH);

		// Gestalten des Panels

		// Init der Panels
		ShiftPanel2 content = new ShiftPanel2();
		ShiftPanel2 northpanel = new ShiftPanel2();
		ShiftPanel2 centerpanel = new ShiftPanel2();
		table = new ShiftPanel2();

		table.setLayout(new GridLayout(1, 5, 150, 150));
		northpanel.setLayout(new FlowLayout());
		content.setLayout(new BorderLayout());

		// Obere Leiste
		JLabel label = new JLabel("Wohnung");
		textfield = new JTextField(50);
		textfield.setBackground(Definitions.BG_COLOR_CONTENT);
		textfield.setOpaque(true);
		textfield.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				aptRequest();
			}
		});

		ShiftButton gobutton = new ShiftButton("►");
		gobutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				aptRequest();
			}
		});

		Dimension textfieldsize = new Dimension((int) Toolkit
				.getDefaultToolkit().getScreenSize().getWidth(), 25);
		textfield.setPreferredSize(textfieldsize);
		textfield.setMaximumSize(textfieldsize);

		// Untere Leiste
		ShiftButton button = new ShiftButton("Back");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
			}
		});

		// Tabelle in der Mitte
		// WohnungsID, Anzahl Mieter m² Anzahl Zimmer
		table.add(new JLabel("WohnungsID"));
		table.add(new JLabel("Anzahl Mieter"));
		table.add(new JLabel("m²"));
		table.add(new JLabel("Anzahl Zimmer"));
		table.add(new Box.Filler(new Dimension(50,50), new Dimension(50,50), new Dimension(50,50)));
//		table.setBackground(Color.RED);

		// add
		northpanel.add(label);
		northpanel.add(textfield);
		northpanel.add(gobutton);
		centerpanel.add(table);

		// Borderlayout setzen
		content.add(northpanel, BorderLayout.NORTH);
		content.add(button, BorderLayout.SOUTH);
		content.add(centerpanel, BorderLayout.CENTER);

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(content, "search");
		frame.getCardlayout().show(frame.getContentpanel(), "search");
		frame.validate();

	}

	protected void aptRequest() {
		String id = textfield.getText();
		System.out.println(id);
		textfield.setText("");
		// ApartmentDAO aptdao = new ApartmentDAO();
		// Apartment apt = aptdao.getApartment(id);
		// apt.getMieteranzahl();
		// apt.getWohnflaeche();
		// apt.getZimmeranzahl();

	}

}
