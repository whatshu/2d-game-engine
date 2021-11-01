package ge;

import ge.util.imageManager;

import java.io.IOException;
import java.util.List;

public class geCore {

    private imageManager imageManager = ge.util.imageManager.getImageManager();
    private geWindow window = new geWindow();

    public geCore() {
    }

    public void setSize(int width, int height) {
        window.setSize(width, height);
    }

    public boolean loadResource(String resourceName, String filePath) {
        try {
            imageManager.load(resourceName, filePath);
        } catch (IOException e) {
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

    public void addSprite(geLayer layer, geSprite sprite) {
        // todo sprite
    }

    public void update() {
        window.update();
    }

    public static void main(String[] args) {
        geCore core = new geCore();
        System.out.println(core.loadResource("test", "/Users/whatshu/projects/IdeaProjects/gameEngineGE/src/main/java/ge/resources/123.jpg"));
        core.addLayer("layer-0", "test", 0, 0, 0, 1, 1);
        core.update();
    }
}
