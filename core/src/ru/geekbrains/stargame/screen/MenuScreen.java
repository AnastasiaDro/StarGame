package ru.geekbrains.stargame.screen;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private SpriteBatch batch;
    private Texture img;
    //фон
    private Texture background;


    //чтобы объект двигался
    //вектор позиции
    private Vector2 pos;


    @Override
    public void show() {
       super.show();
       img = new Texture("duck2.png");
      // background = new Texture("space2.png");
//        if (Gdx.app.getType().equals(Application.ApplicationType.Desktop)) {
//            Gdx.graphics.setWindowedMode(background.getWidth(), background.getHeight());		}
        batch = new SpriteBatch();
        //инициализация векторов
        pos = new Vector2(0,0);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Делаем матрицу проекта единичной
        //теперь объект рисуется в другой системе координат. Мы задаем координаты уже теперь неспосредственно
        //в openGl-системе координат
        batch.getProjectionMatrix().idt();


        batch.begin();
        //а тут задаём наши вектора
       // batch.draw(background,0,0);
        batch.draw(img, -1f, -1f, 1, 1);
        batch.end();


    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return false;
    }
}
