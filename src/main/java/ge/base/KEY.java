package ge.base;

import java.util.ArrayList;
import java.util.List;

public class KEY {
    public enum KEY_TYPE {
        PRESSED, RELEASED, TYPED
    }

    public int      keyCode;
    public KEY_TYPE keyType;

    private static List<KEY> keyList = new ArrayList<>();

    private KEY(int keyCode, KEY_TYPE keyType) {
        this.keyCode = keyCode;
        this.keyType = keyType;
    }

    public static KEY getKey(int keyCode, KEY_TYPE keyType) {
        for (KEY key : keyList) {
            if (key.keyCode == keyCode && key.keyType == keyType) {
                return key;
            }
        }
        KEY t = new KEY(keyCode, keyType);
        keyList.add(t);
        return t;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof KEY) {
//            return ((KEY) obj).keyCode == keyCode && ((KEY) obj).keyType == keyType;
//        }
//        return super.equals(obj);
//    }
}
