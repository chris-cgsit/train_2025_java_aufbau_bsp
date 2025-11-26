package at.cgsit.a_jfxintro.hello;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HelloFXApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Meine JavaFX Anwendung");

        // Content des Scene Graph erzeugen
        // HBox (Horizontal Box) ist ein spezialisierter Layout-Container in JavaFX,
        // der seine untergeordneten Elemente (Nodes, wie Labels, Buttons oder andere Layouts)
        // in einer einzigen horizontalen Reihe anordnet.
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        Label lbl = new Label("Hello Java FX!");
        lbl.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        root.getChildren().add(lbl);

        // ComboBox Beispiel
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Option A", "Option B", "Option C");
        comboBox.setPromptText("Bitte wählen...");

        // CheckBox Beispiel
        CheckBox checkBox = new CheckBox("Aktiv?");
        checkBox.setSelected(false);


        // Scene für den Content erzeugen ...
        Scene scene = new Scene(root, 300, 150); // Fenstergröße angepasst für bessere Sichtbarkeit

        // ... und anzeigen
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
