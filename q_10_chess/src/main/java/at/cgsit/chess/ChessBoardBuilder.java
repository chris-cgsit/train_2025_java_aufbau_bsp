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

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;

/**
 * Erzeugt das Schachbrett (10x10 GridPane):
 *  - 8x8 Spielfläche
 *  - Randbeschriftungen (A-H, 1-8)
 * Bietet Zugriff auf einzelne Felder über algebraische Koordinaten.
 */
public class ChessBoardBuilder {

    public static final int SIZE = 64;

    private final Map<String, Label> squares = new HashMap<>();

    public GridPane buildBoard() {
        GridPane grid = new GridPane();

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {

                Label label = new Label();
                label.setMinSize(SIZE, SIZE);
                label.setAlignment(Pos.CENTER);

                boolean borderRow = row == 0 || row == 9;
                boolean borderCol = col == 0 || col == 9;

                if (borderRow || borderCol) {
                    label.getStyleClass().add("border-square");
                    label.setText(getBorderLabel(row, col));
                } else {
                    int r = row - 1;
                    int c = col - 1;

                    boolean light = (r + c) % 2 == 0;
                    label.getStyleClass().add(light ? "light-square" : "dark-square");

                    String coord = toCoord(r, c);
                    squares.put(coord, label);
                }

                grid.add(label, col, row);
            }
        }
        return grid;
    }

    private String getBorderLabel(int row, int col) {
        if ((row == 0 || row == 9) && col >= 1 && col <= 8)
            return "" + (char) ('A' + (col - 1));
        if ((col == 0 || col == 9) && row >= 1 && row <= 8)
            return "" + (9 - row);
        return "";
    }

    private String toCoord(int r, int c) {
        return "" + (char) ('a' + c) + (8 - r);
    }

    /** Zugriff auf einzelne GUI-Felder */
    public Label getSquare(String coord) {
        return squares.get(coord);
    }

    public Map<String, Label> getAllSquares() {
        return squares;
    }
}
