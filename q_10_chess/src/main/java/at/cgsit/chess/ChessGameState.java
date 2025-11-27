package at.cgsit.chess;

import java.util.HashMap;
import java.util.Map;

/**
 * Hält den logischen Zustand des Schachbretts.
 * Intern als 8x8-Array von Piece, außen Koordinaten wie "e2".
 */
public class ChessGameState {

    // board[row][col]
    // row: 0 = Rang 8 (oben), 7 = Rang 1 (unten)
    // col: 0 = 'a', 7 = 'h'
    private final Piece[][] board = new Piece[8][8];

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

    public void setPiece(String coord, Piece piece) {
        int[] idx = coordToIndex(coord);
        board[idx[0]][idx[1]] = piece;
    }

    public Piece getPiece(String coord) {
        int[] idx = coordToIndex(coord);
        return board[idx[0]][idx[1]];
    }

    public void movePiece(String from, String to) {
        int[] fromIdx = coordToIndex(from);
        int[] toIdx   = coordToIndex(to);

        Piece piece = board[fromIdx[0]][fromIdx[1]];
        if (piece == null) {
            return; // nichts zu bewegen
        }

        board[fromIdx[0]][fromIdx[1]] = null;
        board[toIdx[0]][toIdx[1]] = piece;
    }

    /** Optional: Snapshot als Map für andere Zwecke */
    public Map<String, Piece> snapshot() {
        Map<String, Piece> map = new HashMap<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board[row][col];
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
                Piece piece = board[row][col];
                char symbol = piece == null ? '.' : piece.letter();
                line.append(symbol).append(' ');
            }
            System.out.println(line);
        }
        System.out.println("  a b c d e f g h");
        System.out.println("==================");
    }
}
