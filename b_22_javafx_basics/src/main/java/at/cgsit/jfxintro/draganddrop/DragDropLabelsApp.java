package at.cgsit.jfxintro.draganddrop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DragDropLabelsApp extends Application {

    @Override
    public void start(Stage stage) {

        Label source = new Label("Zieh mich →");
        Label target = new Label("Drop hier");

        source.setStyle("-fx-padding: 20; -fx-border-color: blue;");
        target.setStyle("-fx-padding: 20; -fx-border-color: blue;");

        // setze event für DRAG start (Quelle)
        source.setOnDragDetected(event -> {
            // source ist hier mein label
            // drag ist ein spezielles Clipboard (ableitung) für drag and drop
            Dragboard db = source.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString(source.getText());

            // es geht/ginge auch image ein source image
            ImageView imgView = (ImageView) source.getGraphic();
            if (imgView == null) {
              // kein image vorhanden
            }

          db.setContent(content);

          // Marks this Event as consumed. This stops its further propagation.
          event.consume();
        });

        // Drag über Ziel (damit Drop erlaubt ist)
        target.setOnDragOver(event -> {
            if (event.getGestureSource() != target &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        // Optional: visueller Effekt beim Betreten/Verlassen
        target.setOnDragEntered(event -> {
            if (event.getGestureSource() != target &&
                    event.getDragboard().hasString()) {
                target.setStyle("-fx-padding: 20; -fx-border-color: green;");
            }
            event.consume();
        });

        target.setOnDragExited(event -> {
            target.setStyle("-fx-padding: 20; -fx-border-color: black;");
            event.consume();
        });

        // Drop ausführen
        target.setOnDragDropped((DragEvent event) -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                target.setText(db.getString());
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        // Quelle “aufräumen” nach erfolgreichem Move
        source.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                source.setText(""); // oder z.B. "verschoben"
            }
            event.consume();
        });

        HBox root = new HBox(20, source, target);
        root.setStyle("-fx-padding: 40;");

        stage.setScene(new Scene(root, 400, 150));
        stage.setTitle("Drag & Drop Labels");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
