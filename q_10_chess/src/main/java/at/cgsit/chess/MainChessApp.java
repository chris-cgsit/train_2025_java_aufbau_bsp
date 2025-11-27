package at.cgsit.chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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

        // Szene anzeigen
        Scene scene = new Scene(board);
        scene.getStylesheets().add(getClass().getResource("/chess/chess.css").toExternalForm());

        stage.setTitle("JavaFX Chess – CGS Edition");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
