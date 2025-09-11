package graph;

import fila.Queue;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class ImageUtil {
    private fila.Queue<BufferedImage> frames;
    private JFrame currFrame;
    private JPanel currPanel;
    private BufferedImage currImage;

    private ImageUtil() { }

    public static ImageUtil setupAnimation(String imagePath)  {
        ImageUtil animationContext = new ImageUtil();

        JFrame frame = new JFrame("Visualizador Animação");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            animationContext.frames = new Queue<>(img.getWidth() * img.getHeight());
            animationContext.currImage = img;

            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (animationContext.currImage != null) {
                        g.drawImage(animationContext.currImage, 0, 0, this);
                    }
                }

                @Override
                public Dimension getPreferredSize() { return new Dimension(img.getWidth(), img.getHeight()); }
            };
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            animationContext.currFrame = frame;
            animationContext.currPanel = panel;

            return animationContext;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível carregar a imagem");
            return null;
        }
    }

    public void addNewFrame(BufferedImage newFrame) {
        this.frames.push(newFrame);
    }

    public void draw() {
        if (!frames.isEmpty()) {
            this.currImage = this.frames.pop();
            this.currPanel.repaint();
        }
    }

    public static void showImage(String imagePath) {
        JFrame frame = new JFrame("Image Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.drawImage(img, 0, 0, this);
                }

                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(img.getWidth(), img.getHeight());
                }
            };
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not load image: " + e.getMessage());
        }
    }
}
