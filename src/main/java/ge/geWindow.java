package ge;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class geWindow {

    class geContentJFrame extends JFrame {
        @Override
        public void paint(Graphics g) {
            super.paint(g);

            for (geLayer layer : layers) {
                g.drawImage(layer.getBackground(), 100, 100, this);
            }
            g.fillOval(0, 0, 100, 100);
        }
    }

    private geContentJFrame mainJFrame;
    private List<geLayer> layers;

    public geWindow(int width, int height, String title) {
        mainJFrame = new geContentJFrame();

        mainJFrame.setSize(width, height);
        mainJFrame.setResizable(false);
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainJFrame.setTitle(title);
        mainJFrame.setVisible(true);

        layers = new LinkedList<>();
    }

    public geWindow() {
        this(480, 240, "default window title");
    }

    public void setSize(int width, int height) {
        mainJFrame.setSize(width, height);
    }

    public List<geLayer> getLayers() {
        return layers;
    }

    public void update() {
        mainJFrame.repaint();
    }

}
