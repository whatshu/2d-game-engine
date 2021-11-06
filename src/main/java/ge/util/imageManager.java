package ge.util;

import ge.geException.geException;
import ge.geException.geIncompatibleFileType;
import ge.geException.geLoadFailureException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class imageManager {
    private final static imageManager singleton = new imageManager();

    private imageManager() {
    }

    public static imageManager getFrameManager() {
        return singleton;
    }

    private final Map<String, Image> imageMap = new HashMap();

    public void load(String resourceName, String filePath) throws geException {
        if (!(filePath.endsWith("PNG") || filePath.endsWith("png") || filePath.endsWith("JPG") || filePath.endsWith("jpg"))) {
            throw new geIncompatibleFileType();
        }

        try {
            imageMap.put(resourceName, ImageIO.read(new File(filePath)));
        } catch (IOException e) {
            throw new geLoadFailureException();
        }
    }

    public void load(String resourceName, Image image) {
        imageMap.put(resourceName, image);
    }

    public Image get(String resourceName) {
        return imageMap.getOrDefault(resourceName, null);
    }
}




