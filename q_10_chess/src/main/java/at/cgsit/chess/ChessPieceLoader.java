package at.cgsit.chess;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Lädt Figuren-Bilder und platziert Piece-Objekte + Images aufs Brett.
 */
public class ChessPieceLoader {

    private final ChessBoardBuilder board;
    private final ChessGameState state;
    public static final int PIECE_SIZE = 48;

    public ChessPieceLoader(ChessBoardBuilder board, ChessGameState state) {
        this.board = board;
        this.state = state;
    }

    private Image load(String fileName) {
        return new Image(getClass().getResourceAsStream("/chess/" + fileName));
    }

    private void place(String coord, PieceType type, PieceColor color) {
        Piece piece = new Piece(type, color);

        Image img = load(piece.fileName());
        ImageView view = new ImageView(img);
        view.setFitWidth(PIECE_SIZE);
        view.setFitHeight(PIECE_SIZE);

        Label field = board.getSquare(coord);
        field.setGraphic(view);

        state.setPiece(coord, piece);
    }

    public void loadInitialPosition() {
        // Weiße Bauern
        for (char f = 'a'; f <= 'h'; f++) {
            place(f + "2", PieceType.PAWN, PieceColor.WHITE);
        }
        // Schwarze Bauern
        for (char f = 'a'; f <= 'h'; f++) {
            place(f + "7", PieceType.PAWN, PieceColor.BLACK);
        }

        // Weiß: Hauptfiguren
        place("a1", PieceType.ROOK,   PieceColor.WHITE);
        place("b1", PieceType.KNIGHT, PieceColor.WHITE);
        place("c1", PieceType.BISHOP, PieceColor.WHITE);
        place("d1", PieceType.QUEEN,  PieceColor.WHITE);
        place("e1", PieceType.KING,   PieceColor.WHITE);
        place("f1", PieceType.BISHOP, PieceColor.WHITE);
        place("g1", PieceType.KNIGHT, PieceColor.WHITE);
        place("h1", PieceType.ROOK,   PieceColor.WHITE);

        // Schwarz: Hauptfiguren
        place("a8", PieceType.ROOK,   PieceColor.BLACK);
        place("b8", PieceType.KNIGHT, PieceColor.BLACK);
        place("c8", PieceType.BISHOP, PieceColor.BLACK);
        place("d8", PieceType.QUEEN,  PieceColor.BLACK);
        place("e8", PieceType.KING,   PieceColor.BLACK);
        place("f8", PieceType.BISHOP, PieceColor.BLACK);
        place("g8", PieceType.KNIGHT, PieceColor.BLACK);
        place("h8", PieceType.ROOK,   PieceColor.BLACK);
    }
}
