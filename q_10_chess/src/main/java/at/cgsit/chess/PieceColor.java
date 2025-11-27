package at.cgsit.chess;

public enum PieceColor {
    WHITE,
    BLACK;

    public String displayName() {
        return this == WHITE ? "Weiß" : "Schwarz";
    }
}
