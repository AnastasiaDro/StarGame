package ru.geekbrains.stargame.base;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.stargame.math.Rect;

public class Sprite extends Rect {
    //Угол поворота объекта.
    protected float angle;
    //Величина, на которую мы можем уменьшить или увлеичить объект
    protected float scale = 1f;
    //массив регионов, так как покадровая анимация,
    // а в ней как пленка.
    //пока нет анимации - будем единичный массив юзать
    protected TextureRegion[] regions;
    //текущий индекс массива региона
    //для покадровой анимации - смещаемся от одного кадра к другому
    //нет анимации - frame сегда равен 0
    protected int frame;


    //конструктор
    public Sprite(TextureRegion region) {
        //не равен ли нулю Texture region
        if (region == null) {
            throw new NullPointerException("region is null");

        }
        //инициализируем массив
        //тк одна текстура - массив из одного элемента
        regions = new TextureRegion[1];
        regions[0] = region;
    }
 //метод отрисовки, сюда передаем батчер
    public void draw(SpriteBatch batch){
        batch.draw(
                regions[frame], //текущий регион
                //точка отрисовки, смещаем текстуру влево на половину ширины и вниз на половину высоты
                // то есть центруемся
                getLeft(), getBottom(), // точка отрисовки
                halfWidth, halfHeight, //точка вращения
                getWidth(), getHeight(), //ширина и высота объекта
                scale, scale, //масштаб по х и по y
                angle //угол вращения
        );
    }
}
