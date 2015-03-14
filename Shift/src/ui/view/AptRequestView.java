package ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

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
	private ShiftPanel2 tablerow = new ShiftPanel2();

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
		northpanel.setLayout(new FlowLayout());
		content.setLayout(new BorderLayout());

		// KOmponenten
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
		Dimension size = frame.getContentpanel().getSize();
		System.out.println(size);
		Dimension textfieldsize = new Dimension((int) Toolkit
				.getDefaultToolkit().getScreenSize().getWidth(), 25);

		System.out.println(textfieldsize);
		// textfield.setSize(textfieldsize);
		textfield.setPreferredSize(textfieldsize);
		textfield.setMaximumSize(textfieldsize);

		ShiftButton button = new ShiftButton("Back");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
			}
		});

		// add
		northpanel.add(label);
		northpanel.add(textfield);
		northpanel.add(gobutton);
		content.add(northpanel, BorderLayout.NORTH);
		content.add(button, BorderLayout.SOUTH);
		ShiftPanel2 centerpanel = new ShiftPanel2();
		centerpanel.add(getTableheader(),BorderLayout.NORTH);
		centerpanel.add(tablerow, BorderLayout.CENTER);
		content.add(centerpanel, BorderLayout.CENTER);

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(content, "search");
		frame.getCardlayout().show(frame.getContentpanel(), "search");
		frame.validate();

	}

	private ShiftPanel2 getTableheader() {
		ShiftPanel2 tableheader = new ShiftPanel2();
		tableheader.setLayout(new GridLayout(1, 5));
		JLabel header1 = new JLabel("Wohnungs ID");
		JLabel header2 = new JLabel("Anzahl Mieter");
		JLabel header3 = new JLabel("m²");
		JLabel header4 = new JLabel("Anzahl Zimmer");

		ShiftPanel2 header1panel = new ShiftPanel2();
		ShiftPanel2 header2panel = new ShiftPanel2();
		ShiftPanel2 header3panel = new ShiftPanel2();
		ShiftPanel2 header4panel = new ShiftPanel2();
		ShiftPanel2 header5panel = new ShiftPanel2();

		header1panel.add(header1);
		header2panel.add(header2);
		header3panel.add(header3);
		header4panel.add(header4);

		tableheader.add(header1panel);
		tableheader.add(header2panel);
		tableheader.add(header3panel);
		tableheader.add(header4panel);
		tableheader.add(header5panel);
		return tableheader;
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

		tablerow.setLayout(new GridLayout(1, 5));
		JLabel rowdata1 = new JLabel("1.1.3");
		JLabel rowdata2 = new JLabel("2");
		JLabel rowdata3 = new JLabel("100");
		JLabel rowdata4 = new JLabel("5");

		ShiftPanel2 rowdata1panel = new ShiftPanel2();
		ShiftPanel2 rowdata2panel = new ShiftPanel2();
		ShiftPanel2 rowdata3panel = new ShiftPanel2();
		ShiftPanel2 rowdata4panel = new ShiftPanel2();
		ShiftPanel2 rowdata5panel = new ShiftPanel2();

		rowdata1panel.add(rowdata1);
		rowdata1panel.add(rowdata2);
		rowdata1panel.add(rowdata3);
		rowdata1panel.add(rowdata4);

		tablerow.add(rowdata1panel);
		tablerow.add(rowdata2panel);
		tablerow.add(rowdata3panel);
		tablerow.add(rowdata4panel);
		tablerow.add(rowdata5panel);
		frame.validate();

	}

}
