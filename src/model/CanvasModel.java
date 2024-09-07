package model;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class CanvasModel {
    private BufferedImage image;
    private Brush currentBrush;
    private Color currentColor;

    public CanvasModel(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        currentBrush = new Brush(10, Brush.BrushType.CIRCULAR);
        currentColor = Color.BLACK;
    }

    public void setBrush(Brush brush) {
        this.currentBrush = brush;
    }

    public void setColor(Color color) {
        this.currentColor = color;
    }

    public void draw(int x, int y) {
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(currentColor);

        // Рисуем в зависимости от типа кисти
        switch (currentBrush.getType()) {
            case CIRCULAR:
                g2d.fill(new Ellipse2D.Float(x - currentBrush.getSize() / 2, y - currentBrush.getSize() / 2,
                        currentBrush.getSize(), currentBrush.getSize()));
                break;
            case SQUARE:
                g2d.fillRect(x - currentBrush.getSize() / 2, y - currentBrush.getSize() / 2,
                        currentBrush.getSize(), currentBrush.getSize());
                break;
        }

        g2d.dispose();
    }
    public void drawLine(int x1, int y1, int x2, int y2) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(currentColor);
        g2d.setStroke(new BasicStroke(currentBrush.getSize(),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND )); // Размер кисти

        // Рисуем линию между двумя точками
        g2d.drawLine(x1, y1, x2, y2);
    }

/*    public void drawCircle(int x, int y, int size) {
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setColor(currentColor);

        // Рисуем круг (центр в (x, y), радиус size / 2)
        g2d.fillOval(x - size / 2, y - size / 2, size, size);
    }*/


    public BufferedImage getImage() {
        return image;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public Brush getCurrentBrush() {
        return currentBrush;
    }
    public void clear() {
        // Очищаем изображение
        Graphics2D g2d = image.createGraphics();
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, image.getWidth(), image.getHeight());
        g2d.dispose();
    }
}
