package at.cgsit.jfxintro.ahello;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HelloFXApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Meine App");

        // HBox root = new HBox();
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setPadding(new Insets(20));

        root.setBackground(new Background(
                new BackgroundFill(Color.web("#749DC6"), CornerRadii.EMPTY, Insets.EMPTY)
        ));

        // BILD . Image mit ImageView
        // TODO fix it .. image wurde nicht eingecheckt
//        Image avatarImg = new Image(getClass().getResourceAsStream("/images/logo.jpg"));
//        ImageView avatar = new ImageView(avatarImg);
//        avatar.setFitHeight(80);
//        avatar.setPreserveRatio(true);
//        root.getChildren().add(avatar);


        // label2
        Label lblTitle = new Label("JavaFX Controls Übersicht");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        ObservableList<Node> children = root.getChildren();
        children.add(lblTitle);

        Label lbl = new Label("Hello Kurs Java FX!");
        lbl.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        root.getChildren().add(lbl);

        // 1. TextField
        TextField textField = new TextField();
        textField.setPromptText("Gib etwas ein...");
        textField.setText("Initialer Wert ...");
        root.getChildren().add(textField);

        TextField textField2 = new TextField();
        textField2.setPromptText("Gib etwas ein...");
        // textField.setText("Gib etwas ein text .. ");
        root.getChildren().add(textField2);

        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            System.out.printf("Text geaendert: '%s' → '%s'%n", oldValue, newValue);
            textField2.setText(newValue.toUpperCase());
        });

        // 4. PasswordField
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Passwort");
        root.getChildren().add(passwordField);

        // 5. TextArea
        TextArea textArea = new TextArea();
        textArea.setPromptText("Mehrzeiliger Text...");
        textArea.setPrefRowCount(3);
        root.getChildren().add(textArea);

        // 6. ComboBox
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Option A", "Option B", "Option C");
        comboBox.setPromptText("Bitte wählen...");
        root.getChildren().add(comboBox);

        // 7. CheckBox
        CheckBox checkBox = new CheckBox("Aktiv?");
        root.getChildren().add(checkBox);

        // 8. Slider
        Slider slider = new Slider(0, 100, 50);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);

        Label lblUpdate = new Label();
        lblUpdate.textProperty().bind(slider.valueProperty().asString("Wert: %.0f"));

        root.getChildren().add(lblUpdate);

        /*
            slider.valueProperty().addListener((obs, oldVal, newVal) -> {
                int val = newVal.intValue();
                System.out.println("Slider = " + val);
            });
         */

        root.getChildren().add(slider);

        // 8. ProgressBar
        // 0.0 → 0 % (leer)
        // 1.0 == 100%
        ProgressBar progressBar = new ProgressBar(0.5);
        root.getChildren().add(progressBar);

        // progress bar je nach slider auch zusätzclich verändern

        progressBar.progressProperty().bind(slider.valueProperty().divide(100));

        // 10. ListView
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll("Eintrag 1", "Eintrag 2", "Eintrag 3");
        listView.setPrefHeight(90);

        root.getChildren().add(listView);

        // Button
        Button btn = new Button("Klick mich!");
        root.getChildren().add(btn);

        btn.setOnAction( actionEvent -> {
            System.out.println("Button clicked… preparing to exit");

            try {
                Thread.sleep(2000);  // 2 seconds
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            Platform.exit();
        });

        // Tooltip
        Tooltip.install(lblTitle, new Tooltip("Dies ist ein Tooltip!"));


        // Scene für den Content erzeugen ...
        Scene scene = new Scene(root); // Fenstergröße angepasst für bessere Sichtbarkeit

        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        textField2.requestFocus();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
