package ru.geekbrains.stargame.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.math.MatrixUtils;
import ru.geekbrains.stargame.math.Rect;

public class Base2DScreen implements Screen, InputProcessor {


    protected SpriteBatch batch;

    //Несколько объектов класа Rect
    //Наш экран с границами в пикселях
    private Rect screenBounds; //границы области рисования в пикселях
    private Rect worldBounds; // граници проэкции мировых координат высота будет фиксирвоаная,ширина плавающая, граница проекции мировых координат
    private Rect glBouns; //границы проекции world - gl
    //задача споецироваться из worldBounds в наш квадрат gl

    protected Matrix4 worldToGl;
    protected Matrix3 screenToWorld;

    private Vector2 touch;

    @Override
    public void show() {
        System.out.println("show");
        this.batch = new SpriteBatch();
        //Чтобы LibGDX знал, что у нас этот класс - inputprocessor
        Gdx.input.setInputProcessor(this);
        //не можем запомнить. нужны границы, а они в методе render
        this.screenBounds = new Rect();
        this.worldBounds = new Rect();
        this.glBouns = new Rect(0,0, 1f, 1f);
        this.worldToGl = new Matrix4();
        this.screenToWorld = new Matrix3();
        this.touch = new Vector2();
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize w = " + width + " h = " + height);
    //посчитать соотношение сторон
        screenBounds.setSize(width, height);
        //делаем нашу 0-0 точку в левом нижнем углу
        screenBounds.setLeft(0);
        screenBounds.setBottom(0);

     //   счиатем соотношение сторон
        float aspect = width/ (float) height;
        worldBounds.setHeight(1f);
        worldBounds.setWidth(1f*aspect);
        //считаем матрицу проекции
        MatrixUtils.calcTransitionMatrix(worldToGl, worldBounds, glBouns);
        batch.setProjectionMatrix(worldToGl);
        //тыкаем по экрану,поулчаем точку в пикселях и нужно спроэцироват ь в систему координат с высотой 1f и шриной плавающей
        MatrixUtils.calcTransitionMatrix(screenToWorld, screenBounds, worldBounds);


    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("keyUp keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("keyTyped keycode = " + character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchDown screenX = "+screenX + " touchDown screenY = "+ screenY);
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorld);
        touchDown(touch, pointer);
        return false;
    }

    public boolean touchDown(Vector2 touch, int pointer) {
        System.out.println("touchDown touch.x = " + touch.x + " touch.y = " + touch.y);
        return false;
    }



    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchUp screenX = "+screenX + " touchUp screenY = "+ screenY);
        touch.set(screenX, screenBounds.getHeight() - screenY).mul(screenToWorld);
        touchDown(touch, pointer);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        System.out.println("touchUp touch.x = " + touch.x + " touch.y = " + touch.y);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("touchDragged screenX = "+screenX + " touchDragged screenY = "+ screenY + " pointer = " +pointer );
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        System.out.println("scrolled");
        return false;
    }
}
