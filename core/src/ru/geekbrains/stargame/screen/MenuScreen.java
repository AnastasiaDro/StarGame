package ru.geekbrains.stargame.screen;


import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {


    private Texture img;
    //фон
    private Texture background;


    //чтобы объект двигался
    //вектор позиции
    private Vector2 pos;
    private Vector2 touch;
    private Vector2 v;
    private Vector2 buf;

    @Override
    public void show() {
       super.show();
       img = new Texture("duck2.png");
      // background = new Texture("space2.png");
//        if (Gdx.app.getType().equals(Application.ApplicationType.Desktop)) {
//            Gdx.graphics.setWindowedMode(background.getWidth(), background.getHeight());		}

        //инициализация векторов
        pos = new Vector2(0,0);
        touch = new Vector2();
        v = new Vector2();
        buf = new Vector2();

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        buf.set(touch);
        if(buf.sub(pos).len()> v.len()) {
            pos.add(v);
        } else {
            pos.set(touch);
        }

        //Делаем матрицу проекта единичной
        //теперь объект рисуется в другой системе координат. Мы задаем координаты уже теперь неспосредственно
        //в openGl-системе координат
   //     batch.getProjectionMatrix().idt();


        batch.begin();
        //а тут задаём наши вектора
       // batch.draw(background,0,0);
        batch.draw(img, pos.x, pos.y, 1f, 1f);
        batch.end();


    }

    @Override
    public void dispose() {

        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch,int pointer) {
        this.touch = touch;
        v.set(touch.cpy().sub(pos).scl(0.01f));

        return false;
    }
}
