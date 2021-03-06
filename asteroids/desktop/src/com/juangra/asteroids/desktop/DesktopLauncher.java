package com.juangra.asteroids.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.juangra.asteroids.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen=true;
		config.initialBackgroundColor=Color.BLUE;
		config.addIcon("asteroide.png",FileType.Internal);
		new LwjglApplication(new MyGdxGame(), config);
	}
}
