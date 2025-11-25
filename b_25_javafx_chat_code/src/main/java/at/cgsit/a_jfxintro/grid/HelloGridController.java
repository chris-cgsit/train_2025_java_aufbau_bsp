package at.cgsit.a_jfxintro.grid;

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
