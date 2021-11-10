package ge;

import ge.base.COLLISION_BORDER;
import ge.geException.geException;
import ge.geException.geUnknownFileType;
import ge.util.actionManager;
import ge.util.animationManager;
import ge.util.imageManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class geCore {

    private imageManager imageManager = ge.util.imageManager.getFrameManager();
    private animationManager animationManager = ge.util.animationManager.getAnimationManager();
    private actionManager actionManager = ge.util.actionManager.getActionManager();
    private geWindow window = new geWindow();
    private List<geEvent> events = new ArrayList<>();

    public geCore() {
    }

    /*
    -------------       resource part       --------------
     */

    /**
     * load resources into managers
     *
     * @return success
     */
    public String loadResource(String resourceName, String filePath) throws geException {
        String suffix = filePath.substring(filePath.length() - 3);
        if (suffix.equals("gif") || suffix.equals("GIF")) {
            animationManager.load(resourceName, filePath);
        } else if (suffix.equals("png") || suffix.equals("PNG") ||
                suffix.equals("jpg") || suffix.equals("JPG")) {
            imageManager.load(resourceName, filePath);
        } else {
            throw new geUnknownFileType();
        }
        return resourceName;
    }

    /**
     * load action from frames
     */
    public void loadAction(String actionName, List<geFrame> frames) {
        actionManager.load(actionName, frames);
    }

    /**
     * load action from pictures
     * if borders is null, it will be set to default
     */
    public void loadAction(String actionName, List<Image> images, List<COLLISION_BORDER> borders) {
        if (borders == null) {
            actionManager.load(actionName, images, COLLISION_BORDER.genDefaultCollisionBorder());
        } else {
            actionManager.load(actionName, images, borders);
        }
    }

    /**
     * use unify border for one action
     */
    public void loadAction(String actionName, List<Image> images, COLLISION_BORDER border) {
        actionManager.load(actionName, images, border);
    }

    /**
     * load action from animation
     * if borders is null, it will be set to default
     */
    public void loadAction(String actionName, String animationName, List<COLLISION_BORDER> borders) {
        if (borders == null) {
            actionManager.load(actionName, animationManager.get(animationName), COLLISION_BORDER.genDefaultCollisionBorder());
        } else {
            actionManager.load(actionName, animationManager.get(animationName), borders);
        }
    }

    /**
     * use unify border for one action
     */
    public void loadAction(String actionName, String animationName, COLLISION_BORDER border) {
        actionManager.load(actionName, animationManager.get(animationName), border);
    }

    /*
    -------------       window part       --------------
     */

    /**
     * set window size
     */
    public void setSize(int width, int height) {
        window.setSize(width, height);
    }

    /**
     * repaint all layers
     */
    public void update() {
        window.update();
    }

    public void pullEvent() {
        for (geEvent event : events) {
            if (event.predicate()) {
                event.operate(this);
            }
        }
    }

    /*
    -------------       layer part       --------------
     */

    /**
     * add one layer with normalized location and size
     *
     * @param name       layer name
     * @param background background resource name
     * @param depth      layer depth
     * @return the layer added
     */
    public geLayer addLayer(String name, String background, int depth, float x, float y, float width, float height) {
        geLayer t = new geLayer(name, background, depth, x, y, width, height);
        window.getLayers().add(t);
        return t;
    }

    /**
     * remove a specific layer
     */
    public void removeLayer(geLayer layer) {
        window.getLayers().remove(layer);
    }

    /**
     * get a layer with its name
     */
    public geLayer getLayerByName(String name) {
        for (geLayer layer : window.getLayers()) {
            if (layer.getName().equals(name)) {
                return layer;
            }
        }
        return null;
    }

    /**
     * get a random layer at a specific depth
     * return null if not exist
     */
    public geLayer getLayerByDepth(int depth) {
        for (geLayer layer : window.getLayers()) {
            if (layer.getDepth() == depth) {
                return layer;
            }
        }
        return null;
    }

    /**
     * get all layers
     */
    public List<geLayer> getLayers() {
        return window.getLayers();
    }

    /**
     * set layer to visible
     *
     * @return the layer changed
     */
    public geLayer layerSetVisible(geLayer layer) {
        layer.setVisible(true);
        return layer;
    }

    /**
     * set layer to invisible
     *
     * @return the layer changed
     */
    public geLayer layerSetInvisible(geLayer layer) {
        layer.setVisible(false);
        return layer;
    }

    /**
     * set layer depth
     *
     * @return the layer changed
     */
    public geLayer layerSetDepth(geLayer layer, int depth) {
        layer.setDepth(depth);
        return layer;
    }

    /**
     * get depth of a layer
     */
    public int getLayerDepth(geLayer layer) {
        return layer.getDepth();
    }

    /*
    -------------       sprite part       --------------
     */

    /**
     * add a new sprite to a layer
     */
    public geSprite addSprite(geLayer layer, String spriteName, Image defaultSpriteFrame, COLLISION_BORDER defaultCollisionBorder, float width, float height) {
        geSprite t = new geSprite(layer, spriteName, defaultSpriteFrame, defaultCollisionBorder, width, height);
        layer.addSprite(t);
        return t;
    }

    public geSprite addSprite(String layerName, String spriteName, Image defaultSpriteFrame, COLLISION_BORDER defaultCollisionBorder, float width, float height) {
        return addSprite(getLayerByName(layerName), spriteName, defaultSpriteFrame, defaultCollisionBorder, width, height);
    }

    public geSprite addSprite(String layerName, String spriteName, String defaultSpriteFrameName, COLLISION_BORDER defaultCollisionBorder, float width, float height) {
        return addSprite(getLayerByName(layerName), spriteName, imageManager.get(defaultSpriteFrameName), defaultCollisionBorder, width, height);
    }

    public geSprite getSpriteByName(String spriteName) {
        for (geLayer layer : getLayers()) {
            geSprite t = layer.getSpriteByName(spriteName);
            if (t != null) {
                return t;
            }
        }
        return null;
    }

    public geLayer getSpriteLayer(geSprite sprite) {
        return sprite.getLayer();
    }

    public geLayer getSpriteLayer(String spriteName) {
        geSprite t = getSpriteByName(spriteName);
        if (t != null) {
            return t.getLayer();
        } else {
            return null;
        }
    }

    /**
     * remove a sprite
     */
    public void removeSprite(geSprite sprite) {
        sprite.getLayer().removeSprite(sprite);
    }

    public void removeSprite(String spriteName) {
        geSprite t = getSpriteByName(spriteName);
        if (t != null) {
            removeSprite(t);
        }
    }

    public void spriteSetStatic(geSprite sprite, boolean isStatic) {
        sprite.setStaticCoordinate(isStatic);
    }

    public void spriteSetStatic(String spriteName, boolean isStatic) {
        geSprite t = getSpriteByName(spriteName);
        if (t != null) {
            t.setStaticCoordinate(isStatic);
        }
    }

    public boolean spriteIsStatic(geSprite sprite) {
        return sprite.isStatic();
    }

    public boolean spriteIsStatic(String spriteName) {
        geSprite t = getSpriteByName(spriteName);
        if (t != null) {
            return t.isStatic();
        } else {
            return false;
        }
    }

    public boolean spriteHasAction(geSprite sprite) {
        return sprite.hasAction();
    }

    public boolean spriteHasAction(String spriteName) {
        geSprite t = getSpriteByName(spriteName);
        if (t != null) {
            return t.hasAction();
        } else {
            return false;
        }
    }

    public String getSpriteNowAction(geSprite sprite) {
        return sprite.getAction();
    }

    public String getSpriteNowAction(String spriteName) {
        geSprite t = getSpriteByName(spriteName);
        if (t != null) {
            return t.getAction();
        } else {
            return null;
        }
    }

    /**
     * add action to a sprite
     */
    public void spriteSetAction(geSprite sprite, String action) {
        sprite.setAction(action);
    }

    public void spriteSetAction(String spriteName, String action) {
        geSprite t = getSpriteByName(spriteName);
        if (t != null) {
            t.setAction(action);
        }
    }

    public void spriteNextFrame(geSprite sprite) {
        sprite.nextFrame();
    }

    public void spriteNextFrame(String spriteName) {
        geSprite t = getSpriteByName(spriteName);
        if (t != null) {
            t.nextFrame();
        }
    }

    public void spriteResetAction(geSprite sprite) {
        sprite.resetAction();
    }

    public void spriteResetAction(String sprite) {
        geSprite t = getSpriteByName(sprite);
        if (t != null) {
            t.resetAction();
        }
    }

    public void spriteMove(geSprite sprite, float dx, float dy) {
        sprite.move(dx, dy);
    }

    public void spriteMove(String spriteName, float dx, float dy) {
        geSprite t = getSpriteByName(spriteName);
        if (t != null) {
            t.move(dx, dy);
        }
    }

    public void spriteMoveTo(geSprite sprite, float x, float y) {
        sprite.moveTo(x, y);
    }

    public void spriteMoveTo(String spriteName, float x, float y) {
        geSprite t = getSpriteByName(spriteName);
        if (t != null) {
            t.moveTo(x, y);
        }
    }

    /*
    -------------       sprite part       --------------
     */

    public void addEvent(geEvent e) {
        events.add(e);
    }

    public void removeEvent(geEvent e) {
        events.remove(e);
    }
}