package ge;

import ge.base.MOVABLE;
import ge.base.POINT;
import ge.util.imageManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class geLayer implements MOVABLE {
    private float x, y;
    private float width, height;
    private String background;
    private List<geSprite> sprites = new ArrayList<>();
    private boolean visible = true;
    private int depth;
    private String name;

    public geLayer(String name, String background, int depth, float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.background = background;
        this.depth = depth;
        this.name = name;
    }

    void addSprite(geSprite s) {
        // todo add a sprite for a layer
    }

    void removeSprite(geSprite s) {
        // todo remove a sprite for a layer
    }

    public Image getBackground() {
        return imageManager.getImageManager().get(background);
    }

    public List<geSprite> getSprites() {
        return sprites;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean v) {
        visible = v;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getName() {
        return name;
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
}
