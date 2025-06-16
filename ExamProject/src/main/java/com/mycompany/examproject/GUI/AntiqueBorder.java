/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.GUI;

/**
 * Класс для создания декоративной античной рамки в интерфейсе RPG-игры.
 * <p>
 * Наследуется от {@link AbstractBorder} и рисует стилизованную рамку с орнаментами
 * в углах, используя линии и дуги. Используется для оформления элементов интерфейса,
 * таких как панели или окна.
 *
 * @author Arseniy
 * @version 1.0
 * @since 2025-06-16
 */
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class AntiqueBorder extends AbstractBorder {
    
    /**
     * Цвет рамки (серый с RGB 150, 150, 150).
     */
    private final Color borderColor = new Color(150, 150, 150);

    /**
     * Рисует рамку вокруг компонента.
     * <p>
     * Создает прямоугольную рамку с отступами и орнаментами в углах, используя
     * линии и дуги с антиалиасингом для сглаживания.
     *
     * @param c компонент, для которого рисуется рамка
     * @param g графический контекст
     * @param x координата x верхнего левого угла
     * @param y координата y верхнего левого угла
     * @param width ширина рамки
     * @param height высота рамки
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int margin = 12;
        int arcLen = 16;

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2.2f));

        g2.drawLine(x + margin, y + margin + arcLen, x + margin, y + height - margin - arcLen);
        g2.drawLine(x + width - margin, y + margin + arcLen, x + width - margin, y + height - margin - arcLen);
        g2.drawLine(x + margin + arcLen, y + margin, x + width - margin - arcLen, y + margin);
        g2.drawLine(x + margin + arcLen, y + height - margin, x + width - margin - arcLen, y + height - margin);

        drawOrnateCorner(g2, x + margin, y + margin, 90);

        drawOrnateCorner(g2, x + width - margin, y + margin, 0);

        drawOrnateCorner(g2, x + margin, y + height - margin, 180);

        drawOrnateCorner(g2, x + width - margin, y + height - margin, 270);

        g2.dispose();
    }

    /**
     * Рисует орнамент в углу рамки.
     * <p>
     * Создает декоративный элемент из дуг и линий, поворачивая его на заданный угол.
     *
     * @param g2 графический контекст
     * @param cx координата x центра орнамента
     * @param cy координата y центра орнамента
     * @param rotateDeg угол поворота орнамента (в градусах)
     */
    private void drawOrnateCorner(Graphics2D g2, int cx, int cy, int rotateDeg) {

        Graphics2D g = (Graphics2D) g2.create();
        g.translate(cx, cy);
        g.rotate(Math.toRadians(rotateDeg));

        g.drawArc(-7, -7, 28, 28, 180, 90);

        g.drawArc(-2, -2, 12, 12, 190, 90);

        g.drawLine(-2, 7, 4, 13);

        g.setStroke(new BasicStroke(1.1f));
        g.drawArc(5, 5, 9, 9, 170, 75);

        g.dispose();
    }

    /**
     * Возвращает отступы рамки.
     * <p>
     * Определяет внутренние отступы для содержимого компонента, чтобы оно не
     * перекрывалось рамкой.
     *
     * @param c компонент, для которого запрашиваются отступы
     * @return объект {@link Insets} с отступами (28 пикселей со всех сторон)
     */
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(28, 28, 28, 28);
    }
}