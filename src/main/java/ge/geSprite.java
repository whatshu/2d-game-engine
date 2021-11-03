package ge;

import ge.base.COLLISION_BORDER;
import ge.base.LINE;
import ge.base.MOVABLE;
import ge.base.POINT;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class geSprite implements MOVABLE {
    private geLayer parentLayer;
    private String name;
    private final COLLISION_BORDER defaultCollisionBorder;
    private final String defaultFrameResourceName;
    private Map<String, geAction> actions = new HashMap<>();
    private geAction nowAction = null;
    private float width, height;
    private float x, y;

    public geSprite(geLayer parent, String name, String frame, COLLISION_BORDER collisionBorder, float w, float h) {
        this.parentLayer = parent;
        this.name = name;
        this.defaultFrameResourceName = frame;
        this.defaultCollisionBorder = collisionBorder;
        this.width = w;
        this.height = h;
        parent.addSprite(this);
    }

    public void setAction(String actionName) {
        nowAction = actions.getOrDefault(actionName, null);
    }

    public void resetAction() {
        nowAction = null;
    }

    public Image getFrame() {
        if (parentLayer == null) {
            return null;
        }

        if (nowAction == null) {
            return ge.util.imageManager.getImageManager().get(defaultFrameResourceName);
        } else {
            return nowAction.nowFrame().getImage();
        }
    }

    public Image nextFrame() {
        return nowAction.next().getImage();
    }

    public void resetFrame() {
        nowAction.reset();
    }

    public boolean contact(geSprite other) {
        return this.getCollisionBorder().contact(other.getCollisionBorder());
    }

    public boolean contact(LINE line) {
        return this.getCollisionBorder().contact(line);
    }

    public void abandon() {
        parentLayer = null;
    }

    public void changeParentLayer(geLayer layer){
        parentLayer.removeSprite(this);
        layer.addSprite(this);
        parentLayer = layer;
    }

    public String getName() {
        return name;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public geLayer getLayer() {
        return parentLayer;
    }

    @Override
    public void move(float dx, float dy) {
        x += dx;
        y += dy;
    }

    @Override
    public void move(POINT dp) {
        x += dp.x;
        y += dp.y;
    }

    @Override
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void set(POINT p) {
        this.x = p.x;
        this.y = p.y;
    }

    @Override
    public POINT getPosition() {
        return new POINT(x, y);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    private COLLISION_BORDER getCollisionBorder() {
        if (nowAction == null) {
            return defaultCollisionBorder;
        } else {
            return nowAction.nowFrame().getCollisionBorder();
        }
    }
}
