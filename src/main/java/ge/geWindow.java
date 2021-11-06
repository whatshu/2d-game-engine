package ge;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class geWindow {

    class geContentPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if (layers == null || layers.size() == 0) return;

            int minDepth = layers.get(0).getDepth();
            int maxDepth = layers.get(0).getDepth();
            for (geLayer layer : layers) {
                if (layer.getDepth() < minDepth) minDepth = layer.getDepth();
                if (layer.getDepth() > maxDepth) maxDepth = layer.getDepth();
            }

            for (int i = minDepth; i <= maxDepth; i++) {
                for (geLayer layer : layers) {
                    if (layer.isVisible() && layer.getDepth() == i) {
                        int actualLayerX = (int) ((layer.getX() + 1) / 2 * this.getWidth());
                        int actualLayerY = (int) ((1 - layer.getY()) / 2 * this.getHeight());
                        int actualLayerWidth = (int) (layer.getWidth() / 2 * this.getWidth());
                        int actualLayerHeight = (int) (layer.getHeight() / 2 * this.getHeight());
                        g.drawImage(layer.getBackground(), actualLayerX, actualLayerY, actualLayerWidth, actualLayerHeight, this);

                        // todo draw sprite

                    }
                }
            }
        }
    }

    private JFrame mainFrame;
    private geContentPanel mainPanel;
    private List<geLayer> layers;

    public geWindow(int width, int height, String title) {
        mainFrame = new JFrame();
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle(title);
        mainFrame.setVisible(true);
        mainFrame.setSize(width, height);

        mainPanel = new geContentPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainFrame.add(mainPanel);

        layers = new LinkedList<>();
    }

    public geWindow() {
        this(480, 240, "default window title");
    }

    public void setSize(int width, int height) {
        mainFrame.setSize(width, height);
    }

    public List<geLayer> getLayers() {
        return layers;
    }

    public void update() {
        mainPanel.repaint();
    }

}
