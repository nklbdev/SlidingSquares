package com.polly5315.slidingsquares.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.polly5315.slidingsquares.SlidingSquaresGame;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Sliding Squares";
        config.width = 240;
        config.height = 320;
        new LwjglApplication(new SlidingSquaresGame(), config);
    }
}
