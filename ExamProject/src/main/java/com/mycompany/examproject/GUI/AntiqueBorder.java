/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examproject.GUI;

/**
 *
 * @author Arseniy
 */
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class AntiqueBorder extends AbstractBorder {
    
    private final Color borderColor = new Color(150, 150, 150);

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int margin = 12;
        int arcLen = 16;

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2.2f));

        // Линии по краям, не доходящие до углов
        // Слева
        g2.drawLine(x + margin, y + margin + arcLen, x + margin, y + height - margin - arcLen);
        // Справа
        g2.drawLine(x + width - margin, y + margin + arcLen, x + width - margin, y + height - margin - arcLen);
        // Сверху
        g2.drawLine(x + margin + arcLen, y + margin, x + width - margin - arcLen, y + margin);
        // Снизу
        g2.drawLine(x + margin + arcLen, y + height - margin, x + width - margin - arcLen, y + height - margin);

        // Угловые завитки (ручная имитация референса):

        // Верхний левый
        drawOrnateCorner(g2, x + margin, y + margin, 90);

        // Верхний правый
        drawOrnateCorner(g2, x + width - margin, y + margin, 0);

        // Нижний левый
        drawOrnateCorner(g2, x + margin, y + height - margin, 180);

        // Нижний правый
        drawOrnateCorner(g2, x + width - margin, y + height - margin, 270);

        g2.dispose();
    }

    private void drawOrnateCorner(Graphics2D g2, int cx, int cy, int rotateDeg) {
        // Кастомный угловой завиток, чуть стилизованно под твой референс
        Graphics2D g = (Graphics2D) g2.create();
        g.translate(cx, cy);
        g.rotate(Math.toRadians(rotateDeg));

        // Главная дуга
        g.drawArc(-7, -7, 28, 28, 180, 90);

        // Внутренняя дуга/волютка
        g.drawArc(-2, -2, 12, 12, 190, 90);

        // Маленький хвостик
        g.drawLine(-2, 7, 4, 13);

        // Немного дополнительного декора:
        g.setStroke(new BasicStroke(1.1f));
        g.drawArc(5, 5, 9, 9, 170, 75);

        g.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(28, 28, 28, 28);
    }
}