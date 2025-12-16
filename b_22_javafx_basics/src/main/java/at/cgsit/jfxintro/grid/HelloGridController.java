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

package at.cgsit.jfxintro.grid;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloGridController {

    @FXML private TextField txtVorname;
    @FXML private TextField txtNachname;
    @FXML private Label lblOutput;

    @FXML
    private void onSayHello() {
        String vn = txtVorname.getText();
        String nn = txtNachname.getText();

        lblOutput.setText("Hallo " + vn + " " + nn + "!");
    }
}
