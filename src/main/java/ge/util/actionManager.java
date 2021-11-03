package ge.util;

import ge.geAction;

import java.util.HashMap;
import java.util.Map;

public class actionManager {
    private final static actionManager manager = new actionManager();

    private Map<String, geAction> actionMap = new HashMap<String, geAction>();

    private actionManager() {
    }

    public static actionManager getActionManager() {
        return manager;
    }

    public geAction get(String actionName) {
        return manager.get(actionName);
    }

    public void add(String actionName, geAction action) {
        actionMap.put(actionName, action);
    }

}
