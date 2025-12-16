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

/*
 * Programm zur Demonstration der verschiedenen Layouts
 */


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class LayoutsFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel currentPanel;
	private final JPanel flowPanel, borderPanel, gridPanel, cardPanel, gridBagPanel, boxPanelX,
			boxPanelY, groupPanel, springPanel;

	public static void main(String[] args) {

		new LayoutsFrame();
	}

	public LayoutsFrame() {
		super("Layouts (Swing)");
		this.setLayout(new BorderLayout());
		JPanel layoutButtonPanel = new JPanel();
	
		String[] layouts = { "Flow", "Border", "Grid", "GridBag", "Card", "Box X", "Box Y", "Group", "Spring" };

		for (int i = 0; i < layouts.length; i++) {
			JButton btn = new JButton(layouts[i]);
			layoutButtonPanel.add(btn);
			btn.addActionListener(this);
		}
		
		layoutButtonPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.add(layoutButtonPanel, BorderLayout.NORTH);

		flowPanel = new FlowPanel("One", "Two", "Three", "Four", "Five");
		borderPanel = new BorderPanel();
		cardPanel = new CardPanel();
		gridPanel = new GridPanel();
		gridBagPanel = new GridBagPanel();
		boxPanelX = new BoxPanel(BoxLayout.X_AXIS);
		boxPanelY = new BoxPanel(BoxLayout.Y_AXIS);
		groupPanel = new GroupPanel();
		springPanel = new SpringPanel();

		currentPanel = flowPanel;
		//currentPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		add("Center", currentPanel);

		this.setVisible(true);
		
		pack();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	void switchPanels(JPanel newPanel, boolean setNavigateButtons) {
		// aktuelles Panel entfernen
		remove(currentPanel);
		// Neues Panel einfügen
		currentPanel = newPanel;
		add("Center", currentPanel);
		// Größe usw. neu berechnen und anzeigen
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		String s = ae.getActionCommand();

		if (s.equals("Border"))
			switchPanels(borderPanel, false);
		else if (s.equals("Card"))
			switchPanels(cardPanel, true);
		else if (s.equals("Flow"))
			switchPanels(flowPanel, false);
		else if (s.equals("Grid"))
			switchPanels(gridPanel, false);
		else if (s.equals("GridBag"))
			switchPanels(gridBagPanel, false);
		else if (s.equals("Box X"))
			switchPanels(boxPanelX, false);
		else if (s.equals("Box Y"))
			switchPanels(boxPanelY, false);
		else if (s.equals("Group"))
			switchPanels(groupPanel, false);
		else if (s.equals("Spring"))
			switchPanels(springPanel, false);

	}

}
