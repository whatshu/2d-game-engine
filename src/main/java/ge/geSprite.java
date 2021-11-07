package ge;

import ge.base.COLLISION_BORDER;
import ge.base.LINE;
import ge.base.MOVABLE;
import ge.base.POINT;
import ge.util.actionManager;

import java.awt.*;

public class geSprite implements MOVABLE {
    private geLayer parentLayer;
    private final String name;
    private final geFrame defaultFrame;
    private geAction nowAction = null;
    private final float width;
    private final float height;
    private float x, y;
    private boolean staticCoordinate = false;

    public geSprite(geLayer parent, String name, Image image, COLLISION_BORDER collisionBorder, float w, float h) {
        this.parentLayer = parent;
        this.name = name;
        this.defaultFrame = new geFrame(image, collisionBorder);
        this.width = w;
        this.height = h;
        parent.addSprite(this);
    }

    public geSprite(geLayer parent, String name, geFrame defaultFrame, float w, float h) {
        this.parentLayer = parent;
        this.name = name;
        this.defaultFrame = defaultFrame;
        this.width = w;
        this.height = h;
        parent.addSprite(this);
    }

    public void setAction(String actionName) {
        nowAction = actionManager.getActionManager().get(actionName);
    }

    public void resetAction() {
        nowAction = null;
    }

    public boolean hasAction() {
        return nowAction != null;
    }

    public String getAction(){
        return nowAction.getName();
    }

    public geFrame getFrame() {
        if (parentLayer == null) {
            return null;
        }

        if (nowAction == null) {
            return defaultFrame;
        } else {
            return nowAction.nowFrame();
        }
    }

    public geFrame nextFrame() {
        if (nowAction == null) {
            return defaultFrame;
        } else {
            return nowAction.next();
        }
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

    public void changeParentLayer(geLayer layer) {
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

    public boolean isStatic() {
        return staticCoordinate;
    }

    public void setStatic() {
        staticCoordinate = true;
    }

    public void resetStatic() {
        staticCoordinate = false;
    }

    public void setStaticCoordinate(Boolean s) {
        staticCoordinate = s;
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
            return defaultFrame.getCollisionBorder();
        } else {
            return nowAction.nowFrame().getCollisionBorder();
        }
    }
}
