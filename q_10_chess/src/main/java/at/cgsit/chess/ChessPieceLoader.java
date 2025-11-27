package at.cgsit.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;

import java.util.Map;

/**
 * Lädt PNG/SVG Figuren aus dem Resource-Verzeichnis
 * und setzt sie an die entsprechenden Schachfelder (GUI).
 */
public class ChessPieceLoader {

    private final ChessBoardBuilder board;
    private final ChessGameState state;

    public static final int PIECE_SIZE = 48;

    public ChessPieceLoader(ChessBoardBuilder board, ChessGameState state) {
        this.board = board;
        this.state = state;
    }

    /** PNG laden */
    private Image load(String fileName) {
        return new Image(getClass().getResourceAsStream("/chess/" + fileName));
    }

    /** Figur auf Brett & in GameState setzen */
    private void place(String coord, String fileName) {
        Image image = load(fileName);
        ImageView view = new ImageView(image);
        view.setFitWidth(PIECE_SIZE);
        view.setFitHeight(PIECE_SIZE);

        Label target = board.getSquare(coord);
        target.setGraphic(view);

        state.setPiece(coord, fileName);
    }

    /** Anfangsstellung aller Figuren */
    public void loadInitialPosition() {
        String[] whitePieces = {
                "a1:white_rook.png", "b1:white_knight.png", "c1:white_bishop.png",
                "d1:white_queen.png", "e1:white_king.png", "f1:white_bishop.png",
                "g1:white_knight.png", "h1:white_rook.png"
        };
        String[] blackPieces = {
                "a8:black_rook.png", "b8:black_knight.png", "c8:black_bishop.png",
                "d8:black_queen.png", "e8:black_king.png", "f8:black_bishop.png",
                "g8:black_knight.png", "h8:black_rook.png"
        };

        // Bauern setzen
        for (char f = 'a'; f <= 'h'; f++) {
            place(f + "2", "white_pawn.png");
            place(f + "7", "black_pawn.png");
        }

        // Hauptfiguren
        for (String def : whitePieces) {
            String[] arr = def.split(":");
            place(arr[0], arr[1]);
        }
        for (String def : blackPieces) {
            String[] arr = def.split(":");
            place(arr[0], arr[1]);
        }
    }
}
