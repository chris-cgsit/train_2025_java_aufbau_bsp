package at.cgsit.chess;

import java.util.HashMap;
import java.util.Map;

/**
 * Hält den logischen Zustand des Schachbretts.
 * Intern als 8x8-Array, außen weiterhin mit "e2"-Koordinaten.
 */
public class ChessGameState {

    // board[row][col]
    // row: 0 = Rang 8 (oben), 7 = Rang 1 (unten)
    // col: 0 = 'a', 7 = 'h'
    private final String[][] board = new String[8][8];  // z.B. "white_pawn.png"

    // ---- Koordinaten-Helfer ----

    private int[] coordToIndex(String coord) {
        if (coord == null || coord.length() != 2) {
            throw new IllegalArgumentException("Ungültige Koordinate: " + coord);
        }

        char fileChar = coord.charAt(0);   // 'a'..'h'
        char rankChar = coord.charAt(1);   // '1'..'8'

        if (fileChar < 'a' || fileChar > 'h') {
            throw new IllegalArgumentException("Ungültige File (a-h): " + coord);
        }
        if (rankChar < '1' || rankChar > '8') {
            throw new IllegalArgumentException("Ungültige Rank (1-8): " + coord);
        }

        // 'a'..'h' liegen im Unicode direkt hintereinander
        // durch Subtraktion bekommen wir einen offset von a also einen Zero-Based-Index.
        // a hat sonst z.b. 97 aber filechar - 97
        // wäre 'a' - 'a' → 97 - 97 = 0
        int col = fileChar - 'a';          // 'a'->0 ... 'h'->7
        int rank = rankChar - '0';         // '1'->1 ... '8'->8
        int row = 8 - rank;                // 8->0 ... 1->7

        return new int[]{row, col};
    }

    private String indexToCoord(int row, int col) {
        char file = (char) ('a' + col);    // 0->'a' ... 7->'h'
        int rank = 8 - row;                // 0->8 ... 7->1
        return "" + file + rank;
    }

    // ---- Öffentliches API ----

    /** Figur auf ein Feld setzen (z.B. "e2") */
    public void setPiece(String coord, String pieceId) {
        int[] idx = coordToIndex(coord);
        board[idx[0]][idx[1]] = pieceId;
    }

    /** Figur an einem Feld abfragen */
    public String getPiece(String coord) {
        int[] idx = coordToIndex(coord);
        return board[idx[0]][idx[1]];
    }

    /** Figur von einem Feld auf ein anderes bewegen */
    public void movePiece(String from, String to) {
        int[] fromIdx = coordToIndex(from);
        int[] toIdx   = coordToIndex(to);

        String piece = board[fromIdx[0]][fromIdx[1]];
        if (piece == null) {
            return; // nichts zu bewegen
        }

        board[fromIdx[0]][fromIdx[1]] = null;
        board[toIdx[0]][toIdx[1]] = piece;
    }

    /** Snapshot als Map, falls man das noch wo braucht */
    public Map<String, String> snapshot() {
        Map<String, String> map = new HashMap<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                String piece = board[row][col];
                if (piece != null) {
                    String coord = indexToCoord(row, col);
                    map.put(coord, piece);
                }
            }
        }
        return map;
    }

    /**
     * Debug-Ausgabe als 8x8-Matrix.
     * .  = leer
     * Weiß: P,R,N,B,Q,K
     * Schwarz: p,r,n,b,q,k
     */
    public void debugPrintBoardMatrix() {
        System.out.println("=== BOARD MATRIX ===");
        for (int row = 0; row < 8; row++) {
            int rank = 8 - row;
            StringBuilder line = new StringBuilder();
            line.append(rank).append(" ");
            for (int col = 0; col < 8; col++) {
                String pieceId = board[row][col];
                char symbol = pieceId == null ? '.' : pieceToChar(pieceId);
                line.append(symbol).append(' ');
            }
            System.out.println(line);
        }
        System.out.println("  a b c d e f g h");
        System.out.println("==================");
    }

    /** Wandelt Dateinamen (z.B. "white_knight.png") in ein Zeichen um. */
    private char pieceToChar(String pieceId) {
        String lower = pieceId.toLowerCase();
        boolean isWhite = lower.contains("white");

        char c;
        if (lower.contains("pawn"))        c = 'p';
        else if (lower.contains("rook"))   c = 'r';
        else if (lower.contains("knight")) c = 'n';
        else if (lower.contains("bishop")) c = 'b';
        else if (lower.contains("queen"))  c = 'q';
        else if (lower.contains("king"))   c = 'k';
        else                               c = '?';

        return isWhite ? Character.toUpperCase(c) : c;
    }
}
