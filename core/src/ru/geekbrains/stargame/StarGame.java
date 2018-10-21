package ru.geekbrains.stargame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {

		img = new Texture("textureSpace.jpg");
        System.out.println(img.getHeight());

        //FIXME
		//Если приложение запускается на компьютере, то зададим размер экрана равным размеру фона
        //размер окна не соответствует размеру картинки!!! Возможно, дело в том, что не совпадают некие Unit, или разрешение экрана разное берется
		if (Gdx.app.getType().equals(Application.ApplicationType.Desktop)) {
			Gdx.graphics.setWindowedMode(img.getWidth(), img.getHeight());		}
        batch = new SpriteBatch();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
