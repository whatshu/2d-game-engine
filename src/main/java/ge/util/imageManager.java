package ge.util;

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

    /*
     * @Descroption TODO input the path of pic, return its type
     * @Author windowUserr
     * @Date 2021/11/2
     * @Return [java.lang.String]
    */
    public static String getImgType(String string){
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

    public static void main(String[] args) {
        getImgType("resources/123.jpg");
        System.out.println(getImgType("resources/ae86.gif"));
    }

}




