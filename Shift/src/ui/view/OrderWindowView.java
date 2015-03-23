package ui.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import javafx.scene.layout.Border;
import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import ui.enums.UI_EVENT;
import components.Definitions;
import components.ShiftButton;
import components.ShiftFrame;
import components.ShiftPanel2;

public class OrderWindowView extends AbstractView {

	private ShiftFrame frame;

	public OrderWindowView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {
		frame.getCardlayout().show(frame.getContentpanel(), "page");
		frame.setHeadline(Definitions.ORDERS);
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
		frame.setHeadline(Definitions.ORDERS);

		// Gestalten des Panels
		ShiftPanel2 contentpanel = new ShiftPanel2();
		ShiftPanel2 centerpanel = new ShiftPanel2();
		ShiftPanel2 sp2 = new ShiftPanel2();
		ShiftPanel2 northpanel = new ShiftPanel2();

		// Backbutton
		ShiftButton button = new ShiftButton("Back");
		sp2.add(button);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
			}
		});

		// northpanel
		final JTextField textfield = new JTextField(50);
		JLabel label = new JLabel("Auftrag");
		textfield.setBackground(Definitions.BG_COLOR_CONTENT);
		textfield.setOpaque(true);
		textfield.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Auftrag init...");
				fireLocalUIEvent(this, UI_EVENT.AUFTRAG_ERTEILEN.ordinal(), textfield.getText());
			}
		});

		ShiftButton gobutton = new ShiftButton("->");
		gobutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Auftrag init...");
				fireLocalUIEvent(this, UI_EVENT.AUFTRAG_ERTEILEN.ordinal(), textfield.getText());

			}
		});
		northpanel.add(label);
		northpanel.add(textfield);
		northpanel.add(gobutton);
		
		// add
		contentpanel.setLayout(new BorderLayout());
		contentpanel.add(northpanel, BorderLayout.NORTH);
		contentpanel.add(sp2, BorderLayout.SOUTH);
		contentpanel.add(centerpanel, BorderLayout.CENTER);

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(contentpanel, "page");
		frame.getCardlayout().show(frame.getContentpanel(), "page");
		frame.validate();
	}
}
