package ge;

import ge.base.*;
import ge.util.actionManager;

import java.awt.*;
import java.util.List;

public class geSprite implements MOVABLE {
    private       geLayer  parentLayer;
    private final String   name;
    private final geFrame  defaultFrame;
    private       geAction nowAction = null;
    private final float    width;
    private final float    height;
    private       float    x, y;
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

    public String getAction() {
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

    public geLayer getParentLayer() {
        return parentLayer;
    }

    public boolean contact(geSprite other) {
        POINT thisOrigin  = new POINT(0, 0);
        POINT otherOrigin = new POINT(0, 0);

        thisOrigin.x = this.parentLayer.getX() + this.parentLayer.getWidth() / 2;
        thisOrigin.y = this.parentLayer.getY() + this.parentLayer.getHeight() / 2;
        otherOrigin.x = other.parentLayer.getX() + other.parentLayer.getWidth() / 2;
        otherOrigin.y = other.parentLayer.getY() + other.parentLayer.getHeight() / 2;

        float thisMaxX = -1;
        float thisMaxY = -1;
        float thisMinX = 1;
        float thisMinY = 1;

        List<POINT> thisBorderPoints = this.getCollisionBorder().getPoints();
        for (POINT thisBorderPoint : thisBorderPoints) {
            float x = thisOrigin.x + thisBorderPoint.x * this.parentLayer.getWidth() / 2;
            float y = thisOrigin.y + thisBorderPoint.y * this.parentLayer.getHeight() / 2;

            if (x > thisMaxX) thisMaxX = x;
            if (x < thisMinX) thisMinX = x;
            if (y > thisMaxY) thisMaxY = y;
            if (y < thisMinY) thisMinY = y;
        }

        float otherMaxX = -1;
        float otherMaxY = -1;
        float otherMinX = 1;
        float otherMinY = 1;

        List<POINT> otherBorderPoints = this.getCollisionBorder().getPoints();
        for (POINT otherBorderPoint : otherBorderPoints) {
            float x = otherOrigin.x + otherBorderPoint.x * this.parentLayer.getWidth() / 2;
            float y = otherOrigin.y + otherBorderPoint.y * this.parentLayer.getHeight() / 2;

            if (x > otherMaxX) otherMaxX = x;
            if (x < otherMinX) otherMinX = x;
            if (y > otherMaxY) otherMaxY = y;
            if (y < otherMinY) otherMinY = y;
        }

        if (((thisMinX < otherMinX && otherMinX < thisMaxX && thisMaxX < otherMaxX) ||
                (otherMinX < thisMaxX && thisMaxX < otherMaxX && otherMaxX < thisMaxX)) &&
                ((thisMinY < otherMinY && otherMinY < thisMaxY && thisMaxY < otherMaxY) ||
                        (otherMinY < thisMaxY && thisMaxY < otherMaxY && otherMaxY < thisMaxY))) {
            return true;
        } else return false;
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
    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void moveTo(POINT p) {
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
