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

package at.cgsit.jfxintro.draganddrop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DragDropKanbanApp extends Application {

    @Override
    public void start(Stage stage) {
        VBox todoBox = new VBox(10);
        VBox doneBox = new VBox(10);

        Label todoLabel = new Label("ToDo");
        Label doneLabel = new Label("Done");
        todoLabel.setStyle("-fx-font-weight: bold;");
        doneLabel.setStyle("-fx-font-weight: bold;");

        todoBox.getChildren().add(todoLabel);
        doneBox.getChildren().add(doneLabel);

        // Beispiel-“Tasks”
        for (int i = 1; i <= 3; i++) {
            Button task = new Button("Task " + i);
            task.setStyle("-fx-min-width: 120; -fx-padding: 8;");
            enableDragForTask(task);
            todoBox.getChildren().add(task);
        }

        // Drop-Zonen vorbereiten
        enableDropForColumn(todoBox);
        enableDropForColumn(doneBox);

        todoBox.setStyle("-fx-padding: 10; -fx-border-color: gray;");
        doneBox.setStyle("-fx-padding: 10; -fx-border-color: gray;");

        HBox root = new HBox(20, todoBox, doneBox);
        root.setStyle("-fx-padding: 20;");

        stage.setScene(new Scene(root, 450, 250));
        stage.setTitle("Drag & Drop Kanban");
        stage.show();
    }

    private void enableDragForTask(Button task) {
        // Drag starten
        task.setOnDragDetected(event -> {
            Dragboard db = task.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            // Inhalt ist hier nur Dummy – wir bewegen das Node selbst
            content.putString(task.getText());
            db.setContent(content);
            event.consume();
        });

        // Nach erfolgreichem Drop: optional visueller Effekt o.Ä.
        task.setOnDragDone(event -> event.consume());
    }

    private void enableDropForColumn(VBox column) {
        column.setOnDragOver(event -> {
            if (event.getGestureSource() instanceof Button) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        column.setOnDragEntered(event -> {
            if (event.getGestureSource() instanceof Button) {
                column.setStyle("-fx-padding: 10; -fx-border-color: blue;");
            }
            event.consume();
        });

        column.setOnDragExited(event -> {
            column.setStyle("-fx-padding: 10; -fx-border-color: gray;");
            event.consume();
        });

        column.setOnDragDropped(event -> {
            if (event.getGestureSource() instanceof Button task) {
                // Task aus alter Spalte entfernen und in diese Spalte einfügen
                VBox oldParent = (VBox) task.getParent();
                oldParent.getChildren().remove(task);
                column.getChildren().add(task);
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
