package ui.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import mvc.model.IModel;
import mvc.view.abstrct.AbstractView;
import ui.enums.UI_EVENT;

import components.Definitions;
import components.ShiftButtonBack;
import components.ShiftButtonSearch;
import components.ShiftFrame;
import components.ShiftPanel2;

public class OrderStatusView extends AbstractView {
	private ShiftFrame frame;
	private JTextField textfield;

	public OrderStatusView(IModel model) {
		super(model);
		initUI();
	}

	@Override
	public Object getMainSurface() {
		frame.getCardlayout().show(frame.getContentpanel(), "checkStatus");
		frame.setHeadline(Definitions.CHECK_STATUS);
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
		frame.setHeadline(Definitions.CHECK_STATUS);

		// Gestalten des Panels
		ShiftPanel2 content = new ShiftPanel2();
		ShiftPanel2 northpanel = new ShiftPanel2();
		ShiftPanel2 southpanel = new ShiftPanel2();
		ShiftPanel2 centerpanel = new ShiftPanel2();

		content.setLayout(new BorderLayout());
		ShiftButtonBack button = new ShiftButtonBack();

		// Obere Leiste
		JLabel label = new JLabel("Auftrag");
		textfield = new JTextField(50);
		textfield.setBackground(Definitions.BG_COLOR_CONTENT);
		textfield.setOpaque(true);
		textfield.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.SEARCH.ordinal(),
						textfield.getText());
				textfield.setText("");

			}
		});

		ShiftButtonSearch gobutton = new ShiftButtonSearch();
		gobutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.SEARCH.ordinal(),
						textfield.getText());
				textfield.setText("");
			}
		});

		Dimension textfieldsize = new Dimension((int) Toolkit
				.getDefaultToolkit().getScreenSize().getWidth(), 25);
		textfield.setPreferredSize(textfieldsize);
		textfield.setMaximumSize(textfieldsize);

		southpanel.add(button);
		northpanel.add(label);
		northpanel.add(textfield);
		northpanel.add(gobutton);
		content.add(northpanel, BorderLayout.NORTH);
		content.add(centerpanel, BorderLayout.CENTER);
		content.add(southpanel, BorderLayout.SOUTH);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fireLocalUIEvent(this, UI_EVENT.PUSH_BACK_BUTTON.ordinal());
			}
		});

		// Layout hinzufuegen und Karte zeigen
		frame.getContentpanel().add(content, "checkStatus");
		frame.getCardlayout().show(frame.getContentpanel(), "checkStatus");
		frame.validate();
		textfield.requestFocus();

	}

	public void incorrectInput() {
		JOptionPane.showMessageDialog(new JFrame(), "Auftrag nicht gefunden");
		textfield.requestFocus();
	}

	public void showStatus(Long id, String state) {
		JOptionPane.showMessageDialog(new JFrame(), "Auftrag mit der ID: " + id
				+ " hat den Status: " + state);

	}

	public void focus() {
		textfield.requestFocus();
	}

	public void incorrectInputNumber() {
		JOptionPane.showMessageDialog(new JFrame(),
				"Bitte geben Sie eine Zahl ein.", "Warnung",
				JOptionPane.WARNING_MESSAGE);
		textfield.requestFocus();
	}
}