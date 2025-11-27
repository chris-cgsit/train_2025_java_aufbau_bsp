package at.cgsit.chess;

public enum PieceType {
    KING   ("king",   "K", "König"),
    QUEEN  ("queen",  "Q", "Dame"),
    ROOK   ("rook",   "R", "Turm"),
    BISHOP ("bishop", "B", "Läufer"),
    KNIGHT ("knight", "N", "Springer"),
    PAWN   ("pawn",   "P", "Bauer");

    private final String baseName;
    private final String letter;
    private final String germanName;

    PieceType(String baseName, String letter, String germanName) {
        this.baseName = baseName;
        this.letter = letter;
        this.germanName = germanName;
    }

    /** Dateiname inkl. Farbe, z.B. white_queen.png */
    public String fileName(PieceColor color) {
        return (color == PieceColor.WHITE ? "white_" : "black_") + baseName + ".png";
    }

    /** Buchstabe für Debug/Notation, Farbe in Groß/Klein abgebildet */
    public char letter(PieceColor color) {
        return color == PieceColor.WHITE
                ? letter.charAt(0)
                : Character.toLowerCase(letter.charAt(0));
    }

    public String germanName() {
        return germanName;
    }
}
