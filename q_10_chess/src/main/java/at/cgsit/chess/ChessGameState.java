package at.cgsit.chess;

import java.util.HashMap;
import java.util.Map;

/**
 * Reiner Logik-Speicher:
 *  - Welches Feld hat welche Figur?
 *  - Wird bei Drag-&-Drop aktualisiert
 *  - Grundlage für spätere Zugvalidierung
 */
public class ChessGameState {

    // "e2" -> "white_pawn.png"
    private final Map<String, String> board = new HashMap<>();

    public void setPiece(String coord, String fileName) {
        board.put(coord, fileName);
    }

    public String getPiece(String coord) {
        return board.get(coord);
    }

    /** Figur ziehen */
    public void movePiece(String from, String to) {
        String piece = board.get(from);
        if (piece == null) return;

        board.remove(from);
        board.put(to, piece);
    }

    public Map<String, String> snapshot() {
        return new HashMap<>(board);
    }
}
