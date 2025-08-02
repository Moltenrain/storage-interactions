package com.molte.storageinteractions.settings;

public enum OverlaySize {
    SMALL (10, 3, 10, 16, 7, 3, 20, 20),
    MEDIUM (12, 4, 11, 20, 7, 4, 23, 23),
    LARGE (14, 5, 12, 20, 7, 1, 26, 26),
    X_LARGE(18, 7, 12, 17, 6, -7, 35, 35);

    private final int _fontSize;
    private final float _fontOutlineThickness;
    private final int _fontXOffset;
    private final int _fontYOffset;

    private final int _bankNoteXOffset;
    private final int _bankNoteYOffset;
    private final int _bankNoteWidth;
    private final int _bankNoteHeight;

    OverlaySize(int fontSize, float fontOutlineThickness, int fontXOffset, int fontYOffset, int bankNoteXOffset, int bankNoteYOffset, int bankNoteWidth, int bankNoteHeight){
        _fontSize = fontSize;
        _fontOutlineThickness = fontOutlineThickness;
        _fontXOffset = fontXOffset;
        _fontYOffset = fontYOffset;
        _bankNoteXOffset = bankNoteXOffset;
        _bankNoteYOffset = bankNoteYOffset;
        _bankNoteWidth = bankNoteWidth;
        _bankNoteHeight = bankNoteHeight;
    }

    public int getFontSize()
    {
        return _fontSize;
    }

    public float getFontOutlineThickness()
    {
        return _fontOutlineThickness;
    }

    public int getFontXOffset()
    {
        return _fontXOffset;
    }

    public int getFontYOffset()
    {
        return _fontYOffset;
    }

    public int getBankNoteXOffset() {
        return _bankNoteXOffset;
    }

    public int getBankNoteYOffset() {
        return _bankNoteYOffset;
    }

    public int getBankNoteWidth() {
        return _bankNoteWidth;
    }

    public int getBankNoteHeight() {
        return _bankNoteHeight;
    }
}
