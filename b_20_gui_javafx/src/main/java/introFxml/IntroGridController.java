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

package introFxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class IntroGridController {
    // Attribute für die Controls
    @FXML
    private TextField txtName;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    @FXML
    private ListView<String> lvMessages;

    @FXML
    private void initialize(){

        // auf Änderungen im Textfeld reagieren
        txtName.textProperty().addListener((o, oldVal, newVal) ->{
            // wenn das Textfeld jetzt leer ist, den Button disablen
            // sonst den Button enablen
            btnOk.setDisable(newVal == null || newVal.isBlank());
        });

        // anfangs den Button disabeln
        btnOk.setDisable(true);


        addEntry("App startup finished!");
    }

    @FXML
    private void onClickButton(ActionEvent ae) {

        String userData = ((Node) ae.getSource()).getUserData().toString();
        System.out.println("Userdata: " + userData);
        switch (userData) {
            case "OK" -> addEntry("Hallo, " + txtName.getText() + "!");
            case "Cancel" -> addEntry("Abbrechen ...");
        }
    }

    private void addEntry(String msg) {
        lvMessages.getItems().add(
                DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()) + ": " + msg);
    }
}
