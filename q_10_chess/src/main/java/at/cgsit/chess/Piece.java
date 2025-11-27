package at.cgsit.chess;

/**
 * Repräsentiert eine Schachfigur (Typ + Farbe).
 * Liefert Hilfsfunktionen für PNG-Dateiname & Debug-Buchstaben.
 */
public record Piece(PieceType type, PieceColor color) {

    public String fileName() {
        return type.fileName(color);
    }

    public char letter() {
        return type.letter(color);
    }

    public String displayName() {
        return color.displayName() + " " + type.germanName();
    }
}
