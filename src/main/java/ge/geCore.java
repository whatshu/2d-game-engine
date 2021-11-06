package ge;

import ge.base.COLLISION_BORDER;
import ge.geException.geException;
import ge.geException.geUnknownFileType;
import ge.util.animationManager;
import ge.util.imageManager;

import java.util.List;

public class geCore {

    private imageManager imageManager = ge.util.imageManager.getFrameManager();
    private animationManager animationManager = ge.util.animationManager.getAnimationManager();
    private geWindow window = new geWindow();

    public geCore() {
    }

    /**
     * load resources into managers
     *
     * @return success
     */
    public void loadResource(String resourceName, String filePath) throws geException {
        String suffix = filePath.substring(filePath.length() - 3);
        if (suffix.equals("gif") || suffix.equals("GIF")) {
            animationManager.load(resourceName, filePath);
        } else if (suffix.equals("png") || suffix.equals("PNG") ||
                suffix.equals("jpg") || suffix.equals("JPG")) {
            imageManager.load(resourceName, filePath);
        } else {
            throw new geUnknownFileType();
        }

    }

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
     * set window size
     */
    public void setSize(int width, int height) {
        window.setSize(width, height);
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

    /**
     * add a new sprite to a layer
     */
    public void addSprite(geLayer layer, String spriteName, String defaultSpriteFrame, COLLISION_BORDER defaultCollisionBorder, float width, float height) {
        layer.addSprite(new geSprite(layer, spriteName, defaultSpriteFrame, defaultCollisionBorder, width, height));
    }

    /**
     * remove a sprite
     */
    public void removeSprite(geLayer layer, geSprite sprite) {
        layer.removeSprite(sprite);
    }

    /**
     * add action to a sprite
     */
    public void spriteAddAction(geSprite sprite, String action) {
        // todo add action to sprite
    }

    public void update() {
        window.update();
    }

    public static void main(String[] args) {
        geCore core = new geCore();

        try {
            core.loadResource("test", "resources/321.gif");
        } catch (geException e) {
            System.out.println(e);
        }
        geLayer l1 = core.addLayer("layer-0", "test", 1, -1, 0, 2, 1);
        geLayer l2 = core.addLayer("layer-1", "test", 0, -1, 1, 2, 1.2f);
        core.update();
    }
}
