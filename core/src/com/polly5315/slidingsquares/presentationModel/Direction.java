package com.polly5315.slidingsquares.presentationModel;

public enum Direction {
    Right(1, 0),
    Up(0, 1),
    Left(-1, 0),
    Down(0, -1);

    public final int x;
    public final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
