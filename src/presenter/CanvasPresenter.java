
package presenter;

import model.CanvasModel;
import model.Brush;
import view.CanvasPanel;
import view.MainView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class CanvasPresenter {
    private final CanvasModel model;
    private final MainView view;

    public CanvasPresenter(CanvasModel model, MainView view) {
        this.model = model;
        this.view = view;

        // Инициализация интерфейса
        view.setCanvasPanel(new CanvasPanel());
        view.setClearButtonListener(new ClearButtonListener());
        view.setSaveButtonListener(new SaveButtonListener());
        view.setCircleBrushButtonListener(new CircleBrushButtonListener());
        view.setSquareBrushButtonListener(new SquareBrushButtonListener());
        view.setBrushSizeSliderListener(new BrushSizeSliderListener());
        view.setColorButtonListener(new ColorButtonListener());
        view.setCanvasMouseListener(new CanvasMouseListener());

        // Инициализация начального состояния
        view.getCanvasPanel().setImage(model.getImage());
        view.getBrushSizeSlider().setValue(model.getCurrentBrush().getSize());

        // Передача иконок в MainView
        view.setIcons(
                loadIcon("icons/clear.png"),
                loadIcon("icons/save.png"),
                loadIcon("icons/brush.png"),
                loadIcon("icons/color.png")
        );
    }

    private ImageIcon loadIcon(String path) {
        File file = new File("src/" + path);
        if (file.exists()) {
            ImageIcon originalIcon = new ImageIcon(file.getAbsolutePath());
            Image originalImage = originalIcon.getImage();
            Image scaledImage = originalImage.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } else {
            System.err.println("Icon file not found: " + path);
            return null;
        }
    }

    private class ColorButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = JColorChooser.showDialog(null, "Choose Color", model.getCurrentColor());
            if (color != null) {
                model.setColor(color);
            }
        }
    }

    private class CircleBrushButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.setBrush(new Brush(model.getCurrentBrush().getSize(), Brush.BrushType.CIRCULAR));
        }
    }

    private class SquareBrushButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.setBrush(new Brush(model.getCurrentBrush().getSize(), Brush.BrushType.SQUARE));
        }
    }

    private class BrushSizeSliderListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            int size = view.getBrushSizeSlider().getValue();
            model.setBrush(new Brush(size, model.getCurrentBrush().getType()));
        }
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showSaveDialog(view);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    ImageIO.write(model.getImage(), "png", file);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    private class ClearButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int result = JOptionPane.showConfirmDialog(
                    view,
                    "Do you want to save your work before clearing the canvas?",
                    "Save Work",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (result == JOptionPane.YES_OPTION) {
                // Сохранение работы
                JFileChooser fileChooser = new JFileChooser();
                int userSelection = fileChooser.showSaveDialog(view);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    saveImage(String.valueOf(fileToSave));  // Здесь ваш метод сохранения изображения
                }
            } else if (result == JOptionPane.NO_OPTION) {
                // Просто очистить
                model.clear();
                view.getCanvasPanel().clear();
            }
            // Если пользователь выбрал Cancel, ничего не происходит
        }

        private void saveImage(String nameOfFile) {
            try {
                // Определение пути и формата файла
                File file = new File(nameOfFile+".png");
                ImageIO.write(view.getCanvasPanel().getImage(), "png", file);
                JOptionPane.showMessageDialog(view, "Image saved successfully.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(view, "Error saving image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private class CanvasMouseListener extends MouseAdapter {
        private int prevX, prevY;
        @Override
        public void mousePressed(MouseEvent e) {
            if (model.getCurrentBrush() != null) {
                prevX = e.getX();
                prevY = e.getY();
                model.draw(e.getX(), e.getY());
                view.getCanvasPanel().repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (model.getCurrentBrush() != null) {
                int currentX = e.getX();
                int currentY = e.getY();
                model.drawLine(prevX, prevY, currentX, currentY); // Рисуем линию между предыдущей и текущей точками

/*                if (model.getCurrentBrush().getType() == Brush.BrushType.CIRCULAR) {
                    // Рисуем круг на каждой точке
                    model.drawCircle(currentX, currentY, model.getCurrentBrush().getSize());
                } else {
                    // Если не круглая кисть, рисуем линию
                    model.drawLine(prevX, prevY, currentX, currentY);
                }*/

                prevX = currentX; // Обновляем предыдущие координаты
                prevY = currentY;
                view.getCanvasPanel().repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
           //model.draw(e.getX(), e.getY());
        }


    }

}


