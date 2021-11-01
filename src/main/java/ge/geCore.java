package ge;

import ge.util.imageManager;

import java.io.IOException;

public class geCore {

    private imageManager imageManager = ge.util.imageManager.getImageManager();
    private geWindow     window;

    public geCore() {
        window = new geWindow();
        try {
            imageManager.read("testImage", "resources/123.jpg");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void setSize(int width, int height) {
        window.setSize(width, height);
    }

    public boolean loadResource(String resourceName, String filePath) {
        try {
            imageManager.read(resourceName, filePath);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new geCore();
    }
}
