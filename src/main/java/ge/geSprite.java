package ge;

import ge.base.*;
import ge.util.actionManager;
import ge.util.compare;

import java.awt.*;

public class geSprite implements MOVABLE {
    private       COORDINATE coordinate;
    private       COORDINATE actionCoordinate;
    private       geLayer    parentLayer;
    private final String     name;
    private final geFrame    defaultFrame;
    private       geAction   nowAction = null;
    private final float      width;
    private final float      height;
    private       float      x, y;
    private boolean staticCoordinate = false;

    public geSprite(geLayer parent, String name, Image image, COLLISION_BORDER collisionBorder, float w, float h) {
        this(parent, name, new geFrame(image, collisionBorder), w, h);
    }

    public geSprite(geLayer parent, String name, geFrame defaultFrame, float w, float h) {
        this.parentLayer = parent;
        this.name = name;
        this.defaultFrame = defaultFrame;
        this.width = w;
        this.height = h;
        POINT t = parent.getWindowPosition();
        t.x += parent.getWidth() / 2;
        t.y += parent.getHeight() / 2;
        this.coordinate = new COORDINATE(COORDINATE.COORDINATE_TYPE.CENTER, t, parent.getWidth(), parent.getHeight(), 1, 1);
        this.actionCoordinate = new COORDINATE(COORDINATE.COORDINATE_TYPE.CORNER, getWindowPosition(), width, height, 1, 1);
        parent.addSprite(this);
    }

    public void updateCoordinate() {
        POINT t = parentLayer.getWindowPosition();
        t.x += parentLayer.getWidth() / 2;
        t.y += parentLayer.getHeight() / 2;
        this.coordinate = new COORDINATE(COORDINATE.COORDINATE_TYPE.CENTER, t, parentLayer.getWidth(), parentLayer.getHeight(), 1, 1);
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
        float thisMaxX = -1;
        float thisMaxY = -1;
        float thisMinX = 1;
        float thisMinY = 1;
        for (POINT p : this.getCollisionBorder().getPoints()) {
            POINT real = actionCoordinate.getStandardPoint(p);
            if (real.x > thisMaxX) thisMaxX = real.x;
            if (real.x < thisMinX) thisMinX = real.x;
            if (real.y > thisMaxY) thisMaxY = real.y;
            if (real.y < thisMinY) thisMinY = real.y;
        }

        float otherMaxX = -1;
        float otherMaxY = -1;
        float otherMinX = 1;
        float otherMinY = 1;
        for (POINT p : other.getCollisionBorder().getPoints()) {
            POINT real = other.actionCoordinate.getStandardPoint(p);
            if (real.x > otherMaxX) otherMaxX = real.x;
            if (real.x < otherMinX) otherMinX = real.x;
            if (real.y > otherMaxY) otherMaxY = real.y;
            if (real.y < otherMinY) otherMinY = real.y;
        }

        return (compare.inOrder(new float[]{thisMinX, otherMinX, thisMaxX, otherMaxX}) ||
                compare.inOrder(new float[]{otherMinX, thisMinX, otherMaxX, thisMaxX})) &&
                (compare.inOrder(new float[]{thisMinY, otherMinY, thisMaxY, otherMaxY}) ||
                        compare.inOrder(new float[]{otherMinY, thisMinY, otherMaxY, thisMaxY}));
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
        this.actionCoordinate = new COORDINATE(COORDINATE.COORDINATE_TYPE.CORNER, getWindowPosition(), width, height, 1, 1);
    }

    @Override
    public void move(POINT dp) {
        x += dp.x;
        y += dp.y;
        this.actionCoordinate = new COORDINATE(COORDINATE.COORDINATE_TYPE.CORNER, getWindowPosition(), width, height, 1, 1);
    }

    @Override
    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
        this.actionCoordinate = new COORDINATE(COORDINATE.COORDINATE_TYPE.CORNER, getWindowPosition(), width, height, 1, 1);
    }

    @Override
    public void moveTo(POINT p) {
        this.x = p.x;
        this.y = p.y;
        this.actionCoordinate = new COORDINATE(COORDINATE.COORDINATE_TYPE.CORNER, getWindowPosition(), width, height, 1, 1);
    }

    @Override
    public POINT getPosition() {
        return new POINT(x, y);
    }

    @Override
    public POINT getWindowPosition() {
        return getCoordinate().getStandardPoint(x, y);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public COORDINATE getCoordinate() {
        if (staticCoordinate) {
            return COORDINATE.windowCoordinate();
        } else {
            return coordinate;
        }
    }

    private COLLISION_BORDER getCollisionBorder() {
        if (nowAction == null) {
            return defaultFrame.getCollisionBorder();
        } else {
            return nowAction.nowFrame().getCollisionBorder();
        }
    }
}
