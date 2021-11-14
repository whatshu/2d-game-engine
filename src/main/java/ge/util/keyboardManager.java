package ge.util;

import ge.geCore;
import ge.geEvent;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class keyboardManager {
    private final static keyboardManager singleton = new keyboardManager();

    private keyboardManager() {
    }

    public static keyboardManager getKeyboardManager() {
        return singleton;
    }


    private final Map<Integer, geEvent> keyEvent = new HashMap<>();

    public void addKeyEvent(int keyCode, geEvent event) {
        keyEvent.put(keyCode, event);
    }

    public void removeKeyEvent(int keyCode) {
        keyEvent.remove(keyCode);
    }

    public void perform(int keyCode, geCore core) {
        geEvent t = keyEvent.getOrDefault(keyCode, null);
        if (t != null && t.predicate()) {
            t.operate(core);
        }
    }

}
