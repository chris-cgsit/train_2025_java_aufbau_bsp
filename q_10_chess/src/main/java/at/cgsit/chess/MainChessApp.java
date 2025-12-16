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

package at.cgsit.chess;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;
import java.util.TreeMap;

/**
 * Hauptklasse der Anwendung.
 * Startet JavaFX, baut das Brett und setzt alles zusammen.
 */
public class MainChessApp extends Application {

    @Override
    public void start(Stage stage) {
        // Game State – hält die aktuellen Figurenpositionen
        ChessGameState gameState = new ChessGameState();

        // Erzeuge Brett (nur GUI, keine Figuren)
        ChessBoardBuilder boardBuilder = new ChessBoardBuilder();
        GridPane board = boardBuilder.buildBoard();

        // Figuren laden & aufsetzen
        ChessPieceLoader pieceLoader = new ChessPieceLoader(boardBuilder, gameState);
        pieceLoader.loadInitialPosition();

        // Drag & Drop aktivieren
        DragDropController dragController = new DragDropController(boardBuilder, gameState, pieceLoader);
        dragController.enableDragAndDropOnAllSquares();

      // Debug-Button unterhalb des Boards
      Button debugButton = new Button("Board Debug (Matrix)");
      debugButton.setOnAction(event -> gameState.debugPrintBoardMatrix());

      // Vertikales Layout: oben Board, darunter Button
      VBox root = new VBox(10, board, debugButton);
      root.setPadding(new Insets(10));


        // Szene anzeigen
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/chess/chess.css").toExternalForm());

        stage.setTitle("JavaFX Chess – CGS Edition");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
