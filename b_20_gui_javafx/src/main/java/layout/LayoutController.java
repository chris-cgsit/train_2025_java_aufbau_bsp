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

package layout;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;

public class LayoutController {
	
	// dieses Attribut wird mit dem Controller initialisiert
	// der via fx:include eines separaten FXML Files verfügbar ist.
	// Das fx:include muss die fx:id="grid" haben
	@FXML
	private GridContentController gridController;

	@FXML
	private TabPane tabsContent;

	@FXML
	public void initialize() {
		if (tabsContent != null) {
			System.out.println("init");
			tabsContent.getSelectionModel().select(0);
		}
		
		// auf den untergeordneten Controller zugreifen
		gridController.setName("Michaela");
	}

}
