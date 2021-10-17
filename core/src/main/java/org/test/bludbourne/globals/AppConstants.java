package org.test.bludbourne.globals;

public final class AppConstants {

    private AppConstants() {}

    public static final int FRAME_WIDTH = 16;
    public static final int FRAME_HEIGHT = 16;

    public enum Keys {
        LEFT, RIGHT, UP, DOWN, QUIT
    }

    public enum Mouse {
        SELECT, DO_ACTION
    }
}
