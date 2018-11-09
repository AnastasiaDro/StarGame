package ru.geekbrains.stargame.math;


import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;

/**
 * Утилита для работы с матрицами
 */
public class MatrixUtils {

    private MatrixUtils() {
    }

    /**
     * Расчёт матрицы перехода 4x4
     * @param mat итоговая матрица преобразований
     * @param src исходный квадрат
     * @param dst итоговый квадрат
     */


    // src - прямоуг, с которого хотим проэцироваться, и dst - в который хотим проецироваться
    // т.е. в качестве прямоуг-в будут выступать наши экраны
    //рассчитываем матрицу преобразований, учитывая соотношения сторон экрана

    //приводим матрицу к единичному виду,считаем соотношения сторон ScaleX и ScaleY
    //метод для батчера
    public static void calcTransitionMatrix(Matrix4 mat, Rect src, Rect dst) {
        //считаем соотношение сторон - насколько увлеичивать, насколько уменьшатьы
        //стороны
        float scaleX = dst.getWidth() / src.getWidth();
        float scaleY = dst.getHeight() / src.getHeight();
        //Переносим в начало координат наш src-прямоугольник - методом translate
        //методом scale() - увеличиваем иили уменьшаем данный прямоугольник
        //возвращаем с помошью транслет в место, откуда брали.
        //это даст нам матрицу преобразований. В качестве прямоугольников испольщуем экраны
        mat.idt().translate(dst.pos.x, dst.pos.y, 0f).scale(scaleX, scaleY, 1f).translate(-src.pos.x, -src.pos.y, 0f);
    }

    /**
     * Расчёт матрицы перехода 3x3
     * @param mat итоговая матрица преобразований
     * @param src исходный квадрат
     * @param dst итоговый квадрат
     */

    //считает матрицу 3 на 3 для игры
    public static void calcTransitionMatrix(Matrix3 mat, Rect src, Rect dst) {
        float scaleX = dst.getWidth() / src.getWidth();
        float scaleY = dst.getHeight() / src.getHeight();
        mat.idt().translate(dst.pos.x, dst.pos.y).scale(scaleX, scaleY).translate(-src.pos.x, -src.pos.y);
    }
}