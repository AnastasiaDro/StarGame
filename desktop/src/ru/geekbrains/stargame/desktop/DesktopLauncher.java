package ru.geekbrains.stargame.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.geekbrains.stargame.Star2DGame;
import ru.geekbrains.stargame.StarGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//Задала размер окна. Как задать его с помощью размера картнки?
//		config.height=512;
//		config.width=1024;
		new LwjglApplication(new Star2DGame(), config);
	}
}
