package ge.util;

import com.sun.tools.attach.AgentInitializationException;
import ge.geException.geException;
import ge.geException.geLoadFailureException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class frameManager {
    private enum imgType {
        GIF, JPG, PNG
    }

    private final static frameManager singleton = new frameManager();

    private Map<String, Image> imageMap = new HashMap();

    private frameManager() {
    }

    public static frameManager getFrameManager() {
        return singleton;
    }

    public void load(String resourceName, String filePath) throws geException {
        if (imageType(filePath) == imgType.GIF) {
            imageMap.put(resourceName, Toolkit.getDefaultToolkit().createImage(filePath));
        } else {
            try {
                imageMap.put(resourceName, ImageIO.read(new File(filePath)));
            } catch (IOException e) {
                throw new geLoadFailureException();
            }
        }
    }

    public Image get(String resourceName) {
        return imageMap.getOrDefault(resourceName, null);
    }

    private imgType imageType(String filename) {
        switch (filename.substring(filename.length() - 3)) {
            default:
                return null;
            case "gif":
            case "GIF":
                return imgType.GIF;
            case "png":
            case "PNG":
                return imgType.PNG;
            case "jpg":
            case "JPG":
                return imgType.JPG;
        }
    }
}




