package ge.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class imageManager {

    private static imageManager singleton = new imageManager();

    private Map<String, BufferedImage> imageMap = new HashMap();

    private imageManager() {
    }

    public static imageManager getImageManager() {
        return singleton;
    }

    public void load(String resourceName, String filePath) throws IOException {
        imageMap.put(resourceName, ImageIO.read(new File(filePath)));
    }

    public Image get(String resourceName) {
        return imageMap.get(resourceName);
    }

}
