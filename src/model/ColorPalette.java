package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class ColorPalette {
    private List<Color> colors;
    private Color currentColor;

    public ColorPalette() {
        colors = new ArrayList<>();
        // Добавим несколько стандартных цветов по умолчанию
        colors.add(Color.BLACK);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.CYAN);
        colors.add(Color.MAGENTA);
        currentColor = colors.get(0); // Выбираем первый цвет по умолчанию
    }

    public void addColor(Color color) {
        colors.add(color);
    }

    public List<Color> getColors() {
        return colors;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public boolean removeColor(Color color) {
        return colors.remove(color);
    }

    @Override
    public String toString() {
        return "ColorPalette{" +
                "colors=" + colors +
                ", currentColor=" + currentColor +
                '}';
    }
}
