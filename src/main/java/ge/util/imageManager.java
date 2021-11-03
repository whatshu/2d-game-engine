package ge.util;

import ge.geCore;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class imageManager {
    public static final String GIF = "gif";
    public static final String JPG = "JPEG";
    public static final String PNG = "png";

    private final static imageManager singleton = new imageManager();

    private Map<String, Image> imageMap = new HashMap();

    private imageManager() {
    }

    public static imageManager getImageManager() {
        return singleton;
    }

    public void load(String resourceName, String filePath) throws IOException {
        if (getImgType(filePath).equals(GIF)) {
            imageMap.put(resourceName,
                    Toolkit.getDefaultToolkit().createImage(filePath));
        }else{
            imageMap.put(resourceName, ImageIO.read(new File(filePath)));
        }
    }

    public Image get(String resourceName) {
        return imageMap.get(resourceName);
    }

    public String getImgType(String string){
        File file = new File(string);
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(file);
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext()) {
                throw new RuntimeException("No readers found!");
            }
            ImageReader reader = iter.next();

            String s = reader.getFormatName();
            System.out.println("Format: " + reader.getFormatName());
            iis.close();

            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    static int count = 0;
    public static String getSeriesPicTest(String... filePath){
        if(count == filePath.length){
            count = 0;
        }
        return filePath[count++];
    }

    public static String getSeriesPic(String... filePath){
        if(geCore.coreCounter == filePath.length){
            geCore.coreCounter = 0;
        }
        return filePath[geCore.coreCounter];
    }

    public static void main(String[] args) {
//        getImgType("resources/123.jpg");
//        System.out.println(getImgType("resources/ae86.gif"));
        while(true){
            System.out.println(getSeriesPicTest("resources/123.jpg","resources/ae86.gif","resources/777.png"));
        }

    }

}




