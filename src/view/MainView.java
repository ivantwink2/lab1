package view;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class MainView extends JFrame {
    private CanvasPanel canvasPanel;
    private final JButton clearButton;
    private final JButton saveButton;
    private final JButton brushButton;
    private final JButton circleBrushButton;
    private final JButton squareBrushButton;
    private final JSlider brushSizeSlider;
    private final JButton colorButton;

    private final JPopupMenu brushMenu;

    public MainView() {
        canvasPanel = new CanvasPanel();

        // Цвет верхней панели
        Color panelColor = new Color(220, 220, 220); // Используйте желаемый цвет

        // Панель операций с файлом
        JPanel filePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        filePanel.setBackground(panelColor); // Установка цвета фона
        clearButton = createStyledButton(panelColor);
        saveButton = createStyledButton(panelColor);
        filePanel.add(clearButton);
        filePanel.add(saveButton);

        // Выпадающее меню для выбора кисти
        brushMenu = new JPopupMenu();

        // Ползунок для выбора размера кисти
        brushSizeSlider = new JSlider(5, 100, 10); // Минимум 5, максимум 50, начальное значение 10
        brushSizeSlider.setMajorTickSpacing(10);
        brushSizeSlider.setMinorTickSpacing(1);
        brushSizeSlider.setPaintTicks(true);
        brushSizeSlider.setPaintLabels(true);

        // Кнопки для выбора типа кисти
        circleBrushButton = new JButton("Circle");
        squareBrushButton = new JButton("Square");

        // Добавление кнопок выбора кисти в выпадающее меню
        JPanel brushSettingsPanel = new JPanel();
        brushSettingsPanel.add(new JLabel("Brush Type:"));
        brushSettingsPanel.add(circleBrushButton);
        brushSettingsPanel.add(squareBrushButton);
        brushSettingsPanel.add(new JLabel("Size:"));
        brushSettingsPanel.add(brushSizeSlider);
        brushMenu.add(brushSettingsPanel);

        // Кнопка для отображения меню выбора кисти
        brushButton = new JButton();
        brushButton.setBackground(panelColor);
        brushButton.setOpaque(true);
        brushButton.setBorderPainted(false);
        brushButton.addActionListener(e -> {
            brushMenu.show(brushButton, 0, brushButton.getHeight());
            brushMenu.requestFocusInWindow();
        });

        // Панель выбора цвета
        JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        colorPanel.setBackground(panelColor); // Установка цвета фона
        colorButton = createStyledButton(panelColor);
        colorPanel.add(colorButton);

        // Основная панель инструментов
        JPanel toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolPanel.setBackground(panelColor); // Установка цвета фона
        toolPanel.add(filePanel);
        toolPanel.add(brushButton); // Используем кнопку для выбора кисти
        toolPanel.add(colorPanel);

        // Настройка основной панели
        setLayout(new BorderLayout());
        add(toolPanel, BorderLayout.NORTH);
        add(canvasPanel, BorderLayout.CENTER);

        setSize(1024, 1024);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Paint LoL)");
    }

    private JButton createStyledButton(Color backgroundColor) {
        JButton button = new JButton((ImageIcon) null);
        button.setBackground(backgroundColor);
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }

    public void setCanvasPanel(CanvasPanel canvasPanel) {
        this.canvasPanel = canvasPanel;
        add(canvasPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void setIcons(ImageIcon clearIcon, ImageIcon saveIcon, ImageIcon brushIcon, ImageIcon colorIcon) {
        clearButton.setIcon(clearIcon);
        saveButton.setIcon(saveIcon);
        brushButton.setIcon(brushIcon);
        colorButton.setIcon(colorIcon);
    }

    public void setClearButtonListener(ActionListener listener) {
        clearButton.addActionListener(listener);
    }

    public void setSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void setBrushSizeSliderListener(ChangeListener listener) {
        brushSizeSlider.addChangeListener(listener);
    }

    public void setCircleBrushButtonListener(ActionListener listener) {
        circleBrushButton.addActionListener(listener);
    }

    public void setSquareBrushButtonListener(ActionListener listener) {
        squareBrushButton.addActionListener(listener);
    }

    public void setColorButtonListener(ActionListener listener) {
        colorButton.addActionListener(listener);
    }

    public void setCanvasMouseListener(MouseAdapter mouseAdapter) {
        canvasPanel.addMouseListener(mouseAdapter);
        canvasPanel.addMouseMotionListener(mouseAdapter);
    }

    public CanvasPanel getCanvasPanel() {
        return canvasPanel;
    }

    public JSlider getBrushSizeSlider() {
        return brushSizeSlider;
    }

}
