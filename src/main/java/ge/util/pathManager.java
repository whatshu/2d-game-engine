package ge.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class pathManager {
    public static String getPath(String key, String properties){
        String value = null;
        try {
            InputStream reader = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(properties);
            Properties pro = new Properties();
            pro.load(reader);
            reader.close();
            value = pro.getProperty(key);
            return value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void main(String[] args) {
//        System.out.println(getPath("pic1", "classinfo.properties"));
    }
}
