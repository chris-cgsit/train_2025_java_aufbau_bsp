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

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CardPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel cardLayoutPanel;
	public CardPanel() {
		setLayout(new BorderLayout());

		// Navigation: Buttons im Süden
		JPanel navigateButtonPanel = new JPanel();

		String[] cardsButtons = { "First", "Previous", "Next", "Last", };

		for (int i = 0; i < cardsButtons.length; i++) {
			JButton btn = new JButton(cardsButtons[i]);
			navigateButtonPanel.add(btn);
			btn.addActionListener(this);
		}

		add("South", navigateButtonPanel);

		// Panel mit CardLayout: im Center
		cardLayoutPanel = new JPanel();
		cardLayoutPanel.setLayout(new CardLayout());

		String cards[] = { "First", "Second", "Third", "Fourth", "Fifth" };
		for (int i = 0; i < cards.length; i++) {
			JButton btn = new JButton(cards[i]);
			cardLayoutPanel.add(btn, cards[i]);
		}
		add(cardLayoutPanel, "Center");
	}
	public void actionPerformed(ActionEvent e) {
		CardLayout currentLayout = (CardLayout) cardLayoutPanel.getLayout();
		String s = e.getActionCommand();
		if (s.equals("First")) {
			currentLayout.first(cardLayoutPanel);
		} else if (s.equals("Last")) {
			currentLayout.last(cardLayoutPanel);
		} else if (s.equals("Next")) {
			currentLayout.next(cardLayoutPanel);
		} else if (s.equals("Previous")) {
			currentLayout.previous(cardLayoutPanel);
		}
		// so würde man eine Component per Name auswählen
		// currentLayout.show(cardLayoutPanel, "Third");
	}

}
