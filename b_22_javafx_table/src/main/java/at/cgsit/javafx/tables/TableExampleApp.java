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

package at.cgsit.javafx.tables;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;

public class TableExampleApp extends Application {

    private final TableView<PersonModel> table = new TableView<>();
    private final ObservableList<PersonModel> data = FXCollections.observableArrayList();

    private Integer lastMaxId;
    private static final Random RANDOM = new Random();

    @Override
    public void start(Stage primaryStage) {

        // ---- Tabelle konfigurieren ----
        TableColumn<PersonModel, Number> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<PersonModel, String> colFirst = new TableColumn<>("Vorname");
        colFirst.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<PersonModel, String> colLast = new TableColumn<>("Nachname");
        colLast.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<PersonModel, Number> colAge = new TableColumn<>("Alter");
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));

        table.getColumns().addAll(colId, colFirst, colLast, colAge);
        table.setItems(data);

        // ---- Buttons ----
        Button btnLoad = new Button("Load");
        btnLoad.setOnAction(e -> loadData());

        Button btnOk = new Button("OK");
        btnOk.setOnAction(e -> showSelectedPerson());

        HBox buttonBox = new HBox(10, btnLoad, btnOk);
        buttonBox.setPadding(new Insets(10));

        // ---- Layout ----
        BorderPane root = new BorderPane();
        root.setCenter(table);
        root.setBottom(buttonBox);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("JavaFX Tabellen-Beispiel");
        primaryStage.show();
    }

    // ---- Beispiel-Daten ----
    private void loadData() {
        data.setAll(
            new PersonModel(1, "Chris", "Mayer", 35),
            new PersonModel(2, "Alex", "Huber", 29),
            new PersonModel(3, "Julia", "Schmidt", 41),
            new PersonModel(4, "Susi", "Lehner", 22)
        );

        lastMaxId = 4;
    }

    // ---- OK Button ----
    private void showSelectedPerson() {

        lastMaxId += 1;
        int age = RANDOM.nextInt(53) + 18;   // 18–71

        // beispiel für random string .. dann x characters raussuchen .. für jeden namen
        // zuerst den buchstaben bestimmen. dann x davon machen
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        PersonModel next
                = new PersonModel(lastMaxId, "NEU", "Neuer", age);

        data.add(next);

        PersonModel p = table.getSelectionModel().getSelectedItem();

        if (p == null) {
            showAlert("Bitte eine Person auswählen.");
            return;
        }

        showAlert("Gewählte Person:\n" +
                p.getFirstName() + " " + p.getLastName() +
                " (Alter: " + p.getAge() + ")");

    }

    private void showAlert(String msg) {

        Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        a.showAndWait();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
