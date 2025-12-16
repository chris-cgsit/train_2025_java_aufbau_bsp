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

/**
 * Repraesentiert eine Schachfigur (Typ + Farbe).
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
