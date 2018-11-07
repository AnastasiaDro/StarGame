package ru.geekbrains.stargame.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class ScaledTouchUpButton extends Sprite {

    //константа
    private static final float PRESS_SCALE = 0.9f;
    //чтобы кнопка не нажималась,если отодвинули палец
    //палец
    private int pointer;
    //нажата ли кнопка
    private boolean isPressed;

    public ScaledTouchUpButton(TextureRegion region) {
        super(region);
        //при нажатии на кнопку ее размер станет 90% от исходной

    }

    //методы обработки нажатий
    //взяли из суперкласса
    @Override
    public boolean touchDown(Vector2 touch, int pointer){
        //попал ли вектор тач по нашй кнопке
        //isMe - метод из прямоугольника, попадаем ли в тач
        if (isPressed || !isMe(touch)){
            return false;
        }
        //если попали по кнопке
        this.pointer = pointer;
        //размер кнопки
        this.scale = PRESS_SCALE;
        this.isPressed = true;
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer){
        //отпустил ли эту кнопку
        if (this.pointer != pointer || !isPressed) {
            return false;
        }
        //отпустил ли в месте кнопки, или в другом
        if (isMe(touch)){
            //TODO
        }
        //сбрасываем pressed
        isPressed = false;
        //устанавливаем исходный размер кнопки
        scale = 1f;
        return false;
    }

}
