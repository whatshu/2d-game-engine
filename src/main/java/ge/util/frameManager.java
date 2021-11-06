package ge.util;

//import com.sun.tools.attach.AgentInitializationException;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.gif.GIFImageReaderSpi;
import ge.geCore;
import ge.geException.geException;
import ge.geException.geLoadFailureException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.spi.ImageReaderSpi;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.io.*;
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

    public void load(String resourceName, Image image) throws geException {
        try{
            imageMap.put(resourceName, image);
        }catch (Exception e){
            throw new geLoadFailureException();
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

    public Image gif2AFrame(String imagePath) {
        InputStream inStream = null;
        Image read = null;
        try {
            inStream = new FileInputStream(imagePath);
            ImageReaderSpi readerSpi = new GIFImageReaderSpi();
            GIFImageReader gifReader = (GIFImageReader) readerSpi.createReaderInstance();
            gifReader.setInput(ImageIO.createImageInputStream(inStream));
            int num = gifReader.getNumImages(true);
            System.out.println("总帧数: " + num);
            read = gifReader.read(num-2);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return read;
    }

    public void gif2Frames(String imagePath) {
        InputStream inStream = null;
        try {
            inStream = new FileInputStream(imagePath);
            ImageReaderSpi readerSpi = new GIFImageReaderSpi();
            GIFImageReader gifReader = (GIFImageReader) readerSpi.createReaderInstance();
            gifReader.setInput(ImageIO.createImageInputStream(inStream));
            int num = gifReader.getNumImages(true);
            System.out.println("总帧数: " + num);
            for(int i = 0; i < num; i++){
//                Image read = gifReader.read(i);//TODO "Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 4096"
//                imageMap.put(String.valueOf(i),read);
            }
            System.out.println("map长度: " + imagePath.length());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        geCore core = new geCore();
        frameManager fm = new frameManager();

        System.out.println("ae86 " + core.loadResource("ae86", fm.gif2AFrame("resources/ae86.gif")));
        core.addLayer("layer-1", "ae86", 1, 1, 1, 1, 1);

        fm.gif2Frames("resources/ae86.gif");
        core.update();
    }
}




