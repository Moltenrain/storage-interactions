package com.molte.storageinteractions.settings;

import java.awt.*;

public enum Colour {
    ORANGE (Color.ORANGE),
    RED (Color.RED),
    GREEN (Color.GREEN),
    YELLOW (Color.YELLOW),
    PINK (Color.PINK),
    CYAN (Color.CYAN),
    WHITE (Color.WHITE),
    BLACK (Color.black),
    PURPLE (Color.MAGENTA),
    DARK_GRAY (Color.darkGray),
    GRAY (Color.GRAY),
    BLUE (Color.BLUE);

    private final Color _color;

    Colour(Color color) {
        _color = color;
    }

    public Color getColour() { return _color; }
}
