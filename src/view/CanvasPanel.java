package view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CanvasPanel extends JPanel {
    private BufferedImage image;
    private Graphics2D graphics2D;

    public CanvasPanel() {
        setPreferredSize(new Dimension(1024, 1024));
        image = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_ARGB);
        graphics2D = image.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setBackground(Color.WHITE);
        graphics2D.clearRect(0, 0, image.getWidth(), image.getHeight());
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        this.graphics2D = image.createGraphics();
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void clear() {
        graphics2D.setBackground(Color.WHITE);
        graphics2D.clearRect(0, 0, image.getWidth(), image.getHeight());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
