package ru.geekbrains.stargame.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {

    private SpriteBatch batch;
    private Texture img;

    //чтобы объект двигался
    //вектор позиции
    private Vector2 pos;
    //вектор скорости
    private Vector2 v;
    //FIXME
    private Vector2 userPushedPoint;
    private Vector2 direction;
    private Vector2 vNormalized;
    Vector2 difNormalized;
    private Vector2 difference;

    @Override
    public void show() {
       super.show();
       batch = new SpriteBatch();
       img = new Texture("badlogic.jpg");
       //инициализация векторов
        pos = new Vector2(0,0);
        v = new Vector2(2f,2f);
        userPushedPoint = new Vector2(0,0);
        //получаем направление
        direction = userPushedPoint.cpy().nor();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //а тут задаём наши вектора
        batch.draw(img, pos.x, pos.y);
        batch.end();

        //до тех пор пока разница конечной точки и точки позиции не равна нулю, мы двигаем наше изображение

            //после рисования изменяем вектор позиции
          if (Math.abs(pos.x) < Math.abs(userPushedPoint.x) || Math.abs(pos.y) < Math.abs(userPushedPoint.y)) {
              pos.add(v);
          }

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //меняем y-координату нажатия
        float pushedY = Gdx.graphics.getHeight() - screenY;
        //точка, куда нажал пользователь, задаёт конечную точку нашей картинке
        userPushedPoint.set(screenX,pushedY);
        difference = userPushedPoint.cpy().sub(pos);
        v.setAngle(difference.angle());
        System.out.println("вектор между позицией и userPushedPoint = "+difference );
        return super.touchDown(screenX, screenY, pointer, button);

    }
}
