package ge;

import ge.base.POINT;
import ge.base.SCREEN_POINT;

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
                        SCREEN_POINT p = new SCREEN_POINT(SCREEN_POINT.genOriginPoint(), new POINT(layer.getX(), layer.getY()), this.getWidth(), this.getHeight());

                        int layerWidth  = (int) (layer.getWidth() / 2 * this.getWidth());
                        int layerHeight = (int) (layer.getHeight() / 2 * this.getHeight());
                        g.drawImage(layer.getBackground(), p.x, p.y, layerWidth, layerHeight, this);

                        for (geSprite sprite : layer.getSprites()) {
                            int spriteWidth  = (int) (sprite.getWidth() / 2 * this.getWidth());
                            int spriteHeight = (int) (sprite.getHeight() / 2 * this.getHeight());

                            SCREEN_POINT sp;
                            if (sprite.isStatic()) {
                                sp = new SCREEN_POINT(SCREEN_POINT.genOriginPoint(), new POINT(sprite.getX(), sprite.getY()), spriteWidth, spriteHeight);
                            } else {
                                sp = new SCREEN_POINT(SCREEN_POINT.genOriginPoint(), sprite.getWindowPosition(POINT.zero()), spriteWidth, spriteHeight);
                            }
                            g.drawImage(sprite.getFrame().getImage(), sp.x, sp.y, spriteWidth, spriteHeight, this);
                        }
                    }
                }
            }
        }
    }

    private JFrame         mainFrame;
    private geContentPanel mainPanel;
    private List<geLayer>  layers;
    private long           frameCount     = 0;
    private long           lastUpdateTime = System.currentTimeMillis();

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

        mainFrame.addKeyListener(geKeyBoard.getInstance());

        layers = new LinkedList<>();
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public geWindow() {
        this(600, 500, "default window title");
    }

    public void setSize(int width, int height) {
        mainFrame.setSize(width, height);
    }

    public List<geLayer> getLayers() {
        return layers;
    }

    public void update() {
        mainPanel.repaint();
        frameCount += 1;
        lastUpdateTime = System.currentTimeMillis();
    }

}
