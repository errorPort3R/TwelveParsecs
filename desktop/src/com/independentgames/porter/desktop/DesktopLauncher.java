package com.independentgames.porter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.independentgames.porter.MyGdxGame;

public class DesktopLauncher {

	public static int WINDOW_HEIGHT = 600;
	public static int WINDOW_WIDTH = 1000;


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WINDOW_WIDTH;
		config.height = WINDOW_HEIGHT;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
