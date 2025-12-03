package at.cgsit.a_jfxintro.table;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class TableController {

    @FXML private TableView<Person> tableView;
    @FXML private TableColumn<Person, String> colFirst;
    @FXML private TableColumn<Person, String> colLast;
    @FXML private TableColumn<Person, Number> colAge;

    @FXML
    public void initialize() {
        colFirst.setCellValueFactory(
                data -> {
                    Person value = data.getValue();
                    return value.firstNameProperty();
                });
        colLast.setCellValueFactory(data -> data.getValue().lastNameProperty());
        colAge.setCellValueFactory(data -> data.getValue().ageProperty());

        tableView.getItems().addAll(
                new Person("Chris", "Gruber", 40),
                new Person("Alex", "Maier", 35),
                new Person("Sara", "Klein", 31),
                new Person("Test", "Max", 65)
        );
    }

    public void mouseClicked(MouseEvent event) {

        System.out.println("Scene X:  " + event.getSceneX());
        System.out.println("Scene Y:  " + event.getSceneY());

        System.out.println("Screen X: " + event.getScreenX());
        System.out.println("Screen Y: " + event.getScreenY());

        System.out.println("X in Node: " + event.getX());   // Relative to TableView
        System.out.println("Y in Node: " + event.getY());

        if( event.getClickCount() == 2 && event.isControlDown()) {
            Person selectedItem = tableView.getSelectionModel().getSelectedItem();
            if(selectedItem!=null) {
                System.out.println("Double clicked: " + selectedItem.toString());
            }
        }
    }
}
