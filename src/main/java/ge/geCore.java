package ge;

import ge.base.COLLISION_BORDER;
import ge.geException.geException;
import ge.util.frameManager;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class geCore {

    private frameManager frameManager = ge.util.frameManager.getFrameManager();
    private geWindow window = new geWindow();

    public geCore() {
    }

    public void setSize(int width, int height) {
        window.setSize(width, height);
    }

    public boolean loadResource(String resourceName, String filePath) {
        try {
            frameManager.load(resourceName, filePath);
        } catch (geException e) {
            return false;
        }
        return true;
    }

    public boolean loadResource(String resourceName, Image image){
        try {
            frameManager.load(resourceName, image);
        }catch (geException e){
            return false;
        }
        return true;

    }

    public void addLayer(String name, String background, int depth, float x, float y, float width, float height) {
        window.getLayers().add(new geLayer(name, background, depth, x, y, width, height));
    }

    public void removeLayer(geLayer layer) {
        window.getLayers().remove(layer);
    }

    public geLayer getLayerByName(String name) {
        for (geLayer layer : window.getLayers()) {
            if (layer.getName().equals(name)) {
                return layer;
            }
        }
        return null;
    }

    public geLayer getLayerByDepth(int depth) {
        for (geLayer layer : window.getLayers()) {
            if (layer.getDepth() == depth) {
                return layer;
            }
        }
        return null;
    }

    public List<geLayer> getLayers() {
        return window.getLayers();
    }

    public void layerSetVisible(geLayer layer) {
        layer.setVisible(true);
    }

    public void layerSetInvisible(geLayer layer) {
        layer.setVisible(false);
    }

    public void addSprite(geLayer layer, String spriteName, String defaultSpriteFrame, COLLISION_BORDER defaultCollisionBorder, float width, float height) {
        layer.addSprite(new geSprite(layer, spriteName, defaultSpriteFrame, defaultCollisionBorder, width, height));
    }

    public void removeSprite(geLayer layer, geSprite sprite) {
        layer.removeSprite(sprite);
    }

    public void spriteAddAction(geSprite sprite, geAction action) {
        // todo add action to sprite
    }

    public void update() {
        window.update();
    }

    public static void main(String[] args) {
        geCore core = new geCore();
        System.out.println("test " + core.loadResource("test", "resources/123.jpg"));
        core.addLayer("layer-0", "test", 0, 0, 0, 1, 1);

        System.out.println("ae86 " + core.loadResource("ae86", "resources/ae86.gif"));
        core.addLayer("layer-1", "ae86", 1, 1, 1, 1, 1);
        core.update();
    }
}
