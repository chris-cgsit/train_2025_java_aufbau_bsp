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

package at.cgsit.jfxintro.elements;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HelloFXAllControlsDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX – Alle wichtigen Controls Demo");

        VBox root = new VBox(15);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));

        // Titel
        Label lblTitle = new Label("JavaFX Controls Übersicht");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // 1. TextField
        TextField textField = new TextField();
        textField.setPromptText("Gib etwas ein...");

        // 2. PasswordField
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Passwort");

        // 3. TextArea
        TextArea textArea = new TextArea();
        textArea.setPromptText("Mehrzeiliger Text...");
        textArea.setPrefRowCount(3);

        // 4. Button
        Button btn = new Button("Klick mich!");

        // 5. ComboBox
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Option A", "Option B", "Option C");
        comboBox.setPromptText("Bitte wählen...");

        // 6. CheckBox
        CheckBox checkBox = new CheckBox("Aktiv?");

        // 7. Slider
        Slider slider = new Slider(0, 100, 50);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);

        // 8. ProgressBar
        ProgressBar progressBar = new ProgressBar(0.5);

        // 9. DatePicker
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Datum wählen");

        // 10. RadioButtons + ToggleGroup
        ToggleGroup group = new ToggleGroup();
        RadioButton r1 = new RadioButton("Option 1");
        RadioButton r2 = new RadioButton("Option 2");
        RadioButton r3 = new RadioButton("Option 3");

        r1.setToggleGroup(group);
        r2.setToggleGroup(group);
        r3.setToggleGroup(group);

        HBox radioBox = new HBox(15, r1, r2, r3);
        radioBox.setAlignment(Pos.CENTER);

        // 11. ListView
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll("Eintrag 1", "Eintrag 2", "Eintrag 3");
        listView.setPrefHeight(90);

        // 12. TableView
        TableView<Person> table = new TableView<>();
        TableColumn<Person, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(p -> p.getValue().nameProperty());

        TableColumn<Person, String> colCity = new TableColumn<>("Stadt");
        colCity.setCellValueFactory(p -> p.getValue().cityProperty());

        table.getColumns().addAll(colName, colCity);
        table.getItems().addAll(
                new Person("Anna", "Wien"),
                new Person("Markus", "Graz"),
                new Person("Lena", "Salzburg")
        );
        table.setPrefHeight(120);

        // Tooltip
        Tooltip.install(lblTitle, new Tooltip("Dies ist ein Tooltip!"));

        // Alles hinzufügen
        root.getChildren().addAll(
                lblTitle,
                textField,
                passwordField,
                textArea,
                btn,
                comboBox,
                checkBox,
                slider,
                progressBar,
                datePicker,
                radioBox,
                listView,
                table
        );

        Scene scene = new Scene(root, 500, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Kleine Klasse für TableView-Demo
    public static class Person {
        private final javafx.beans.property.SimpleStringProperty name;
        private final javafx.beans.property.SimpleStringProperty city;

        public Person(String name, String city) {
            this.name = new javafx.beans.property.SimpleStringProperty(name);
            this.city = new javafx.beans.property.SimpleStringProperty(city);
        }
        public javafx.beans.property.SimpleStringProperty nameProperty() { return name; }
        public javafx.beans.property.SimpleStringProperty cityProperty() { return city; }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
