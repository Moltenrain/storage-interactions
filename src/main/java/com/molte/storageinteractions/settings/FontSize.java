package com.molte.storageinteractions.settings;

public enum FontSize {
    VERY_SMALL (8, 1, 0, 0),
    SMALL (10, 3, 0, 0),
    MEDIUM (12, 4, 1, 0),
    LARGE (14, 5, 2, 1),
    EXTRA_LARGE (18, 7, 3, 2);

    private final int _size;
    private final float _outlineThickness;
    private final int _xOffset;
    private final int _yOffset;

    FontSize(int size, float outlineThickness, int xOffset, int yOffset){
        _size = size;
        _outlineThickness = outlineThickness;
        _xOffset = xOffset;
        _yOffset = yOffset;
    }

    public int getSize() { return _size; }
    public float getOutlineThickness() {return _outlineThickness; }
    public int getXOffset() {return _xOffset; }
    public int getYOffset() {return _yOffset; }
}
