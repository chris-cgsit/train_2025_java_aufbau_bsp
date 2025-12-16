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

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

import java.util.Map;

/**
 * Kümmert sich um komplette Drag-&-Drop Funktion:
 * - Ziehen der Figur
 * - Hervorheben des Ziel-Feldes
 * - Ablegen der Figur
 * - Update des GameState
 */
public class DragDropController {

    private final ChessBoardBuilder board;
    private final ChessGameState state;
    private final ChessPieceLoader loader;

    // gerade gezogene Figur
    private Image draggingImage;
    private String fromCoord;

    public DragDropController(ChessBoardBuilder board,
                              ChessGameState state,
                              ChessPieceLoader loader) {
        this.board = board;
        this.state = state;
        this.loader = loader;
    }

    /** Aktiviert Drag-&-Drop auf allen 64 Feldern */
    public void enableDragAndDropOnAllSquares() {
        for (Map.Entry<String, Label> entry : board.getAllSquares().entrySet()) {
            enableForSingleSquare(entry.getKey(), entry.getValue());
        }
    }

    private void enableForSingleSquare(String coord, Label square) {

        // Drag starten
        square.setOnDragDetected(event -> {
            ImageView imgView = (ImageView) square.getGraphic();
            if (imgView == null) return; // kein Stück am Feld

            draggingImage = imgView.getImage();
            fromCoord = coord;

            Dragboard db = square.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(draggingImage);
            db.setContent(content);

            square.setGraphic(null); // Figur "abheben"
            event.consume();
        });

        // Hervorheben möglicher Ziel-Felder
        square.setOnDragOver(event -> {
            if (event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
                square.getStyleClass().add("drag-target");
            }
            event.consume();
        });

        // Highlight entfernen
        square.setOnDragExited(event ->
                square.getStyleClass().remove("drag-target"));

        // Drop ausführen
        square.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (!db.hasImage()) return;

            // Figur anzeigen
            ImageView view = new ImageView(draggingImage);
            view.setFitWidth(ChessPieceLoader.PIECE_SIZE);
            view.setFitHeight(ChessPieceLoader.PIECE_SIZE);
            square.setGraphic(view);

            // GameState aktualisieren
            state.movePiece(fromCoord, coord);

            event.setDropCompleted(true);
            event.consume();
        });
    }
}
