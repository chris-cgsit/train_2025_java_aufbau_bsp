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
import javafx.scene.control.TextField;

public class GridContentController {

	@FXML 
	private TextField txtName;
	
	public void setName(String name){
		txtName.setText(name);
	}

}
