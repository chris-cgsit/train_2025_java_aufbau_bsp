package at.cgsit.jfxintro.draganddrop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DragDropLabelsApp extends Application {

    @Override
    public void start(Stage stage) {

        Label labelA = new Label("A: Zieh mich");
        Label labelB = new Label("B: Oder mich");

        styleNormal(labelA);
        styleNormal(labelB);

        // Beide Labels als Drag-Quelle und Drop-Ziel aktivieren
        enableDragAndDrop(labelA);
        enableDragAndDrop(labelB);

        HBox root = new HBox(20, labelA, labelB);
        root.setStyle("-fx-padding: 40;");

        stage.setScene(new Scene(root, 400, 150));
        stage.setTitle("Drag & Drop Swap Labels");
        stage.show();
    }

    private void enableDragAndDrop(Label label) {

        label.setOnMouseClicked( event -> {
            System.out.println("clicked");
        });

        // Drag starten (Quelle)
        label.setOnDragDetected(event -> {
            Dragboard db = label.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString(label.getText());
            db.setContent(content);
            event.consume();
        });

        // Drag über Ziel (Drop erlauben)
        label.setOnDragOver(event -> {
            if (event.getGestureSource() != label &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        // Visueller Effekt
        label.setOnDragEntered(event -> {
            if (event.getGestureSource() != label &&
                    event.getDragboard().hasString()) {
                styleHighlight(label);
            }
            event.consume();
        });

        label.setOnDragExited(event -> {
            styleNormal(label);
            event.consume();
        });

        // Drop: Texte austauschen
        label.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString() && event.getGestureSource() instanceof Label sourceLabel) {
                Label targetLabel = (Label) event.getSource();

                // Swap der Texte
                String temp = targetLabel.getText();
                targetLabel.setText(db.getString());
                sourceLabel.setText(temp);

                success = true;
            }

            event.setDropCompleted(success);
            event.consume();
        });

        // DragDone brauchen wir hier nicht für das Löschen,
        // weil wir ja einen Swap machen.
        label.setOnDragDone(event -> event.consume());
    }

    private void styleNormal(Label label) {
        label.setStyle("-fx-padding: 20; -fx-border-color: black;");
    }

    private void styleHighlight(Label label) {
        label.setStyle("-fx-padding: 20; -fx-border-color: green;");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
