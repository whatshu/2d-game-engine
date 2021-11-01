package ge;

import ge.base.COLLISION_BORDER;
import ge.base.MOVABLE;
import ge.base.POINT;

import java.util.HashMap;
import java.util.Map;

public class geSprite implements MOVABLE {
    // todo biggest todo here

    private COLLISION_BORDER defaultCollisionBorder;
    private String defaultFrameResourceName;
    private Map<String, geAction> actions = new HashMap<>();
    private float width, height;
    private float x, y;

    public geSprite(String frame, COLLISION_BORDER collisionBorder, float w, float h, float x, float y) {
        defaultFrameResourceName = frame;
        defaultCollisionBorder = collisionBorder;
        width = w;
        height = h;
        this.x = x;
        this.y = y;
    }

    public float getWidth() {
        return 0;
    }

    float getHeight() {
        return 0;
    }

    COLLISION_BORDER getCollisionBorder() {
        return null;
    }

    geLayer getLayer() {
        return null;
    }

    POINT getCenter() {
        return null;
    }

    void setAction(String actionName) {

    }

    void nextFrame() {

    }

    void resetFrame() {

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
