/*
 *  Copyright © 2025 CGS IT Solutions GmbH.
 *  All rights reserved.
 *
 *  This source code is proprietary intellectual property
 *  of CGS IT Solutions GmbH and is provided solely for use
 *  within licensed training programs.
 *
 *   Any copying, redistribution, modification, or use
 *   beyond the permitted scope is strictly prohibited.
 */

package layouts;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FlowPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JLabel lblInfo;

	public FlowPanel(String... buttons) {

		setLayout(new FlowLayout());
		for (int i = 0; i < buttons.length; i++) {
			JButton btn = new JButton(buttons[i]);
			btn.addActionListener(this);
			add(btn);
		}

		add(lblInfo = new JLabel("Platz für Infos", JLabel.CENTER));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setInfotext("Es wurde folgender Button geklickt: " + ((JButton) e.getSource()).getText());

	}

	public void setInfotext(String info) {
		lblInfo.setText(info);
		validate();
	}

}
