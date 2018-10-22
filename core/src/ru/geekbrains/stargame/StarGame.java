package ru.geekbrains.stargame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {

		img = new Texture("textureSpace.jpg");
        System.out.println(img.getHeight());

		img = new Texture("badlogic.jpg");
		System.out.println(img.getHeight());

		//вектор скорости из примера
		Vector2 v1 = new Vector2(2,3);
		//вектор направления - сила тяжести
		Vector2 v2 = new Vector2( 0,-1 );
		//сложение
		v1.add( v2 );
		System.out.println("v1.x = "+ v1.x + " v1 y = " + v1.y);


		//вычитание
		//задаем
		v1.set(3,2);
		v2.set(1,1);
		//вычитаем
//		v1.sub(v2);
		System.out.println(v1);
		//если хотим, чтобы результат был записан в третий вектор - есть мктод cpy()
		Vector2 v3 = v1.cpy().sub(v2);
		System.out.println(v3);

		//умножение векторов
		v1.set(43,51);
		//умножение на скаляр - увеличение и уменьшения размера
		v1.scl(0.9f);
		System.out.println(v1 + "длина" + v1.len());

		//нормализация вектора
		v1.nor();
		System.out.println(v1 + "длина" + v1.len());

		//скалярное произведение 2х векторов
		v1.set(3,5);
		v2.set(24,9);
		//чтобы узнать угол - сперва нужно нормализовать
		v1.nor();
		v2.nor();
		System.out.println("Скалярное произведение "+ v1+ "  "+ v2 + ": ");
		//узнать угол между векторами в радианах:
		System.out.println(Math.acos(v1.dot(v2)));



		//FIXME
		//Если приложение запускается на компьютере, то зададим размер экрана равным размеру фона
        //размер окна не соответствует размеру картинки!!! Возможно, дело в том, что не совпадают некие Unit, или разрешение экрана разное берется
//		if (Gdx.app.getType().equals(Application.ApplicationType.Desktop)) {
//			Gdx.graphics.setWindowedMode(img.getWidth(), img.getHeight());		}
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
