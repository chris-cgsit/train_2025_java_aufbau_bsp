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

package at.cgsit.a_jfxintro.person;

import at.cgsit.a_jfxintro.person.repository.InMemoryPersonRepository;
import at.cgsit.a_jfxintro.person.repository.PersonRepository;
import at.cgsit.a_jfxintro.person.service.PersonService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PersonController {

  @FXML
  private TextField txtVorname;
  @FXML
  private TextField txtNachname;
  @FXML
  private TextField txtEmail;
  @FXML
  private DatePicker dpGeburt;
  @FXML
  private Label lblStatus;
  @FXML
  private Button btnEdit;
  @FXML
  private Button btnSave;

  private PersonViewModel vm;
  private boolean editMode = false;

  @FXML
  public void initialize() {

    PersonRepository repo = new InMemoryPersonRepository();
    PersonService service = new PersonService(repo);

    vm = new PersonViewModel(service);

    txtVorname.textProperty().bindBidirectional(vm.vornameProperty());
    txtNachname.textProperty().bindBidirectional(vm.nachnameProperty());
    txtEmail.textProperty().bindBidirectional(vm.emailProperty());
    dpGeburt.valueProperty().bindBidirectional(vm.geburtsdatumProperty());

    lblStatus.textProperty().bind(vm.statusMessageProperty());

    vm.load(1L); // Demo-Daten

    // Start: read-only mode
    setEditMode(false);

  }

  private void setEditMode(boolean enabled) {
    this.editMode = enabled;

    txtVorname.setEditable(enabled);
    txtNachname.setEditable(enabled);
    txtEmail.setEditable(enabled);

    // DatePicker hat editable + disable
    dpGeburt.setDisable(!enabled);
    dpGeburt.getEditor().setEditable(enabled);

    btnSave.setDisable(!enabled);

    if (enabled) {
      // Edit-Mode: normales Aussehen
      String normalStyle = "";
      txtVorname.setStyle(normalStyle);
      txtNachname.setStyle(normalStyle);
      txtEmail.setStyle(normalStyle);
      dpGeburt.getEditor().setStyle(normalStyle);

      btnEdit.setText("Cancel");
      vm.setStatusMessage("Edit-Modus aktiviert.");
    } else {
      // Read-Only-Mode: leicht grauer Hintergrund, Text etwas blasser
      String readonlyStyle = "-fx-background-color: #f3f3f3; -fx-text-fill: #666666;";

      txtVorname.setStyle(readonlyStyle);
      txtNachname.setStyle(readonlyStyle);
      txtEmail.setStyle(readonlyStyle);
      dpGeburt.getEditor().setStyle(readonlyStyle);

      btnEdit.setText("Edit");
      vm.setStatusMessage("Ansicht im Read-Only Modus.");
    }
  }

  @FXML
  private void onSave() {
    System.out.println("Save clicked");
    vm.save();
  }

  @FXML
  private void onEdit() {
    // oder mit Parameter:
    System.out.println("Edit clicked");
    setEditMode(true);
  }

}
