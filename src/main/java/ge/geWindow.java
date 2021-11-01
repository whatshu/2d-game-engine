package ge;

import javax.swing.*;

public class geWindow {

    private JFrame frame;

    public geWindow(int width, int height) {
        frame = new JFrame();

        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("geEngine");
    }

    public geWindow() {
        this(480, 240);
    }

    public void setSize(int width, int height) {
        frame.setSize(width, height);
    }

}
