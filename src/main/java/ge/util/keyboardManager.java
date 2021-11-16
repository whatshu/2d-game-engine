package ge.util;

import ge.base.KEY;
import ge.geCore;
import ge.geEvent;

import java.util.HashMap;
import java.util.Map;

public class keyboardManager {
    private final static keyboardManager singleton = new keyboardManager();

    private keyboardManager() {
    }

    public static keyboardManager getKeyboardManager() {
        return singleton;
    }


    private final Map<KEY, geEvent> keyEventMap = new HashMap<>();

    public void addKeyEvent(KEY key, geEvent event) {
        keyEventMap.put(key, event);
    }

    public void removeKeyEvent(KEY key) {
        keyEventMap.remove(key);
    }

    public void perform(KEY key, geCore core) {
        geEvent t = keyEventMap.getOrDefault(key, null);
        if (t != null && t.predicate()) {
            t.operate(core);
        }
    }

}
