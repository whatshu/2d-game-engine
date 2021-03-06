package ge;

import ge.base.COORDINATE;
import ge.base.MOVABLE;
import ge.base.POINT;
import ge.base.SCREEN_POINT;
import ge.util.imageManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class geLayer implements MOVABLE {
    private COORDINATE coordinate;
    private float      x, y;
    private float width, height;
    private String         background;
    private List<geSprite> sprites = new ArrayList<>();
    private boolean        visible = true;
    private int            depth;
    private String         name;

    public geLayer(String name, String background, int depth, float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.background = background;
        this.depth = depth;
        this.name = name;
        this.coordinate = new COORDINATE(COORDINATE.COORDINATE_TYPE.CENTER, new POINT(0.5f, 0.5f), 1, 1, 1, 1);
    }

    public void addSprite(geSprite s) {
        sprites.add(s);
    }

    public void removeSprite(geSprite s) {
        sprites.remove(s);
        s.abandon();
    }

    public void removeSprite(String spriteName) {
        removeSprite(getSpriteByName(spriteName));
    }

    public Image getBackground() {
        return imageManager.getFrameManager().get(background);
    }

    public geSprite getSpriteByName(String name) {
        for (geSprite sprite : sprites) {
            if (sprite.getName().equals(name)) {
                return sprite;
            }
        }
        return null;
    }

    public List<geSprite> getSprites() {
        return sprites;
    }

    public boolean containSprite(String name) {
        return getSpriteByName(name) != null;
    }

    public boolean containSprite(geSprite sprite) {
        return sprites.contains(sprite);
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

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    private void updateAllSpriteCoordinate(){
        for (geSprite sprite : sprites) {
            sprite.updateCoordinate();
        }
    }

    @Override
    public void move(float dx, float dy) {
        x += dx;
        y += dy;
        updateAllSpriteCoordinate();
    }

    @Override
    public void move(POINT dp) {
        x += dp.x;
        y += dp.y;
        updateAllSpriteCoordinate();
    }

    @Override
    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
        updateAllSpriteCoordinate();
    }

    @Override
    public void moveTo(POINT p) {
        this.x = p.x;
        this.y = p.y;
        updateAllSpriteCoordinate();
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
        return coordinate;
    }
}
