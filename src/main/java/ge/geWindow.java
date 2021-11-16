package ge;

import ge.base.KEY;
import ge.base.SCREEN_POINT;
import ge.geCore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

            SCREEN_POINT.setScreenSize(this.getWidth(), this.getHeight());
            for (int i = minDepth; i <= maxDepth; i++) {
                for (geLayer layer : layers) {
                    if (layer.isVisible() && layer.getDepth() == i) {
                        SCREEN_POINT p = new SCREEN_POINT(layer.getWindowPosition());
                        g.drawImage(layer.getBackground(), p.x, p.y, (int) (layer.getWidth() * this.getWidth()), (int) (layer.getHeight() * this.getHeight()), this);

                        for (geSprite sprite : layer.getSprites()) {
                            SCREEN_POINT sp = new SCREEN_POINT(sprite.getWindowPosition());
                            g.drawImage(sprite.getFrame().getImage(), sp.x, sp.y, (int) (sprite.getWidth() * this.getWidth()), (int) (sprite.getHeight() * this.getHeight()), this);
                        }
                    }
                }
            }
        }
    }

    private geCore         core;
    private JFrame         mainFrame;
    private geContentPanel mainPanel;
    private List<geLayer>  layers         = new LinkedList<>();
    private long           frameCount     = 0;
    private long           lastUpdateTime = System.currentTimeMillis();
    private KeyListener    keyListener    = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            core.performKeyEvent(KEY.getKey(e.getKeyCode(), KEY.KEY_TYPE.TYPED));
        }

        @Override
        public void keyPressed(KeyEvent e) {
            core.performKeyEvent(KEY.getKey(e.getKeyCode(), KEY.KEY_TYPE.PRESSED));
        }

        @Override
        public void keyReleased(KeyEvent e) {
            core.performKeyEvent(KEY.getKey(e.getKeyCode(), KEY.KEY_TYPE.RELEASED));
        }
    };

    public geWindow(geCore core, int width, int height, String title) {
        this.core = core;

        mainFrame = new JFrame();
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle(title);
        mainFrame.setVisible(true);
        mainFrame.setSize(width, height);

        mainPanel = new geContentPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        mainFrame.add(mainPanel);

        mainFrame.addKeyListener(keyListener);
    }

    public geWindow(geCore core) {
        this(core, 480, 240, "default window title");
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
