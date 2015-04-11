package com.luminositygames.smoothietycoon.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.luminositygames.smoothietycoon.Constants;
import com.luminositygames.smoothietycoon.Main;

public class DesktopLauncher {

	private static final int scale = 100;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Constants.NAME;
		config.width = Constants.WIDTH * scale / 100;
		config.height = Constants.HEIGHT * scale / 100;
		config.initialBackgroundColor = Color.valueOf("FFFFFF");
		config.resizable = false;
		config.vSyncEnabled = true;
		config.addIcon("icon_16.png", FileType.Internal);
		config.addIcon("icon_32.png", FileType.Internal);
		config.addIcon("icon_128.png", FileType.Internal);
		new LwjglApplication(new Main(), config);
	}
}
