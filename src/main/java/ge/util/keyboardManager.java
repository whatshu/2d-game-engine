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


    private final Map<KEY, geEvent> keyEvent = new HashMap<KEY, geEvent>();

    public void addKeyEvent(KEY keyCode, geEvent event) {
        keyEvent.put(keyCode, event);
    }

    public void removeKeyEvent(KEY keyCode) {
        keyEvent.remove(keyCode);
    }

    public void perform(KEY keyCode, geCore core) {
        geEvent t = keyEvent.getOrDefault(keyCode, null);
        if (t != null && t.predicate()) {
            t.operate(core);
        }
    }

}
