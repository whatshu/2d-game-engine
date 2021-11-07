package ge.util;

import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.gif.GIFImageReaderSpi;
import ge.geException.geException;
import ge.geException.geIncompatibleFileType;
import ge.geException.geLoadFailureException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class animationManager {
    private final static animationManager singleton = new animationManager();

    private final Map<String, List<Image>> animationMap = new HashMap<>();

    private animationManager() {
    }

    public static animationManager getAnimationManager() {
        return singleton;
    }

    public void load(String resourceName, String filePath) throws geException {
        if (!(filePath.endsWith("gif") || filePath.endsWith("GIF"))) {
            throw new geIncompatibleFileType();
        }

        try {
            List<Image> frames = new ArrayList();
            int width = ImageIO.read(new File(filePath)).getWidth();
            int height = ImageIO.read(new File(filePath)).getHeight();
            ImageReader reader = new GIFImageReader(new GIFImageReaderSpi());
            reader.setInput(ImageIO.createImageInputStream(new File(filePath)));
            for (int i = 0; i < reader.getNumImages(true); i++) {
                Image t = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                t.getGraphics().drawImage(reader.read(i), 0, 0, null);
                frames.add(t);
            }
            animationMap.put(resourceName, frames);
            imageManager.getFrameManager().load("gif-0", frames.get(10)); // todo remove, only for test
        } catch (IOException e) {
            throw new geLoadFailureException();
        }
    }

    public List<Image> get(String animationName) {
        return animationMap.get(animationName);
    }

}
