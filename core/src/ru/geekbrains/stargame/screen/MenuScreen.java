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
    //вектор, обозначающий куда указал пользователь
    private Vector2 userPushedPoint;
    //разница между вектором, куда указал user и начальной позицией. По умолчанию равен позиции
    private Vector2 difference;

    //флаг
    boolean help = false;

    @Override
    public void show() {
       super.show();
       batch = new SpriteBatch();
       img = new Texture("badlogic.jpg");
       //инициализация векторов
        pos = new Vector2(0,0);
        v = new Vector2(2f,2f);
        //точка, куда указал user
        userPushedPoint = new Vector2(0,0);
        difference = new Vector2(pos);
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

        //ноль всегда проскакивает, а единица - тоже часто (в связи с координатами изначально заданного вектора скорости 2,2)
        //в интернете нашла примеры, где используют отличное от нуля число.
        //если длина вектора difference между точкой нахождения объекта и указанной юзером точкой больше двух, то мы добавляем вектор скорости и задаем новое значение в difference
        if (difference.len()>2) {
              pos.add(v);
              difference.set(userPushedPoint.cpy().sub(pos));

          } else {
            //здесь поставила флаг, чтобы можно было выводить лог без зацикливания
            //также позиция фигуры выравнивается под позицию, указанную юзером. Из-за частоты обновлений render-a сдвиг оказывается незаметным
                if (help == false) {
                    System.out.println("координаты вектора позиции = " + pos);
                    System.out.println("Координаты UserPushedPoint = " + userPushedPoint);
                    help = true;
                    pos.set(userPushedPoint);
                }
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
        help = false;
        //вектор расстояния между нажатой точкой и точкой, в которой находится наша фигура
        difference = userPushedPoint.cpy().sub(pos);
        System.out.println(" вектор difference = " + difference);
        System.out.println("длина difference = " + difference.len());
        v.setAngle(difference.angle());
        return super.touchDown(screenX, screenY, pointer, button);

    }
}
