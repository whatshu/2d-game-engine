package ge;

import ge.base.COLLISION_BORDER;
import ge.geException.geException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class unitTest {
    public static void main(String[] args) {

        geCore core = new geCore("test-core");

        try {
            core.loadResource("pic", "./resources/princessAdventure/start.png");
        } catch (geException ignored) {}

        // test for layer init
        core.addLayer("test-layer", "pic", 0, -1, 1, 1, 1);
        core.update();
        assertEquals(-1, core.getLayerByName("test-layer").getX());
        assertEquals(1, core.getLayerByName("test-layer").getY());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        // test for layer move
        core.layerMove("test-layer", -0.5f, 0);
        core.update();
        assertEquals(-1.5, core.getLayerByName("test-layer").getX());
        assertEquals(1, core.getLayerByName("test-layer").getY());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        // test for sprite init
        core.addSprite("test-layer", "test-sprite", "pic", COLLISION_BORDER.genDefaultCollisionBorder(), 0.5f, 0.5f);
        core.update();
        assertEquals(0, core.getSpriteByName("test-sprite").getX());
        assertEquals(0, core.getSpriteByName("test-sprite").getY());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        // test for sprite contact
        core.addSprite("test-layer", "test-sprite2", "pic", COLLISION_BORDER.genDefaultCollisionBorder(), 0.5f, 0.5f);
        core.spriteMove("test-sprite2", 0.1f, 0.1f);
        core.update();
        assertTrue(core.getSpriteByName("test-sprite").contact(core.getSpriteByName("test-sprite2")));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        // test for multiple layers
        core.addLayer("test-layer2", "pic", 1, -1.1f, 0.9f, 1, 1);
        core.update();
        assertEquals(2, core.getLayers().size());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        // test for multiple sprites
        core.update();
        assertEquals(2, core.getLayerByName("test-layer").getSprites().size());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
    }

}
