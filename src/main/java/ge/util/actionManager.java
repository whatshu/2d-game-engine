package ge.util;

import ge.base.COLLISION_BORDER;
import ge.geAction;
import ge.geFrame;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class actionManager {
    private final static actionManager singleton = new actionManager();

    private Map<String, geAction> actionMap = new HashMap<>();

    private actionManager() {
    }

    public static actionManager getActionManager() {
        return singleton;
    }

    public void load(String actionName, List<java.awt.Image> images, List<COLLISION_BORDER> borders) {
        actionMap.put(actionName, new geAction(images, borders));
    }

    public void load(String actionName, List<java.awt.Image> images, COLLISION_BORDER unifyBorder) {
        actionMap.put(actionName, new geAction(images, unifyBorder));
    }

    public void load(String actionName, List<geFrame> frames) {
        actionMap.put(actionName, new geAction(frames));
    }

    public geAction get(String actionName) {
        return actionMap.getOrDefault(actionName, null);
    }

}
