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

    private Vector2 difference;

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
        v = new Vector2(0.3f ,0.3f);
        buf = new Vector2();
        difference = new Vector2(pos);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        buf.set(touch);
//        if(buf.sub(pos).len()> v.len()) {
//            pos.add(v);
//        } else {
//            pos.set(touch);
//        }

        if(difference.len()>0.3) {
            pos.add(v);
            difference.set(buf.sub(pos));
        } else {
            pos.set(touch);
        }

        //Делаем матрицу проекта единичной
        //теперь объект рисуется в другой системе координат. Мы задаем координаты уже теперь неспосредственно
        //в openGl-системе координат
   //     batch.getProjectionMatrix().idt();


        batch.begin();
        batch.draw(img, pos.x, pos.y, 10f, 10f);
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
 //       v.set(touch.cpy().sub(pos).scl(0.01f));
        difference = touch.cpy().sub(pos);
        v.setAngle(difference.angle());
        return false;
    }
}
