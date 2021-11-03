package ge;

import ge.base.COLLISION_BORDER;
import ge.base.MOVABLE;
import ge.base.POINT;

import java.util.HashMap;
import java.util.Map;

public class geSprite implements MOVABLE {
    // todo biggest todo here

    private geLayer parentLayer;
    private String name;
    private COLLISION_BORDER defaultCollisionBorder;
    private String defaultFrameResourceName;
    private Map<String, geAction> actions = new HashMap<>();
    private float width, height;
    private float x, y;

    public geSprite(geLayer parentLayer, String name, String frame, COLLISION_BORDER collisionBorder, float w, float h) {
        this.parentLayer = parentLayer;
        this.name = name;
        this.defaultFrameResourceName = frame;
        this.defaultCollisionBorder = collisionBorder;
        this.width = w;
        this.height = h;
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

    public COLLISION_BORDER getCollisionBorder() {
        return null;
    }

    public geLayer getLayer() {
        return parentLayer;
    }

    public void setAction(String actionName) {

    }

    public void nextFrame() {

    }

    public void resetFrame() {

    }

    @Override
    public void move(float dx, float dy) {
    }

    @Override
    public void move(POINT dp) {
    }

    @Override
    public void set(float x, float y) {

    }

    @Override
    public void set(POINT p) {

    }

    @Override
    public POINT getPosition() {
        return null;
    }

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }
}
