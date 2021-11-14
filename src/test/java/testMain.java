import ge.base.COLLISION_BORDER;
import ge.geCore;
import ge.geException.geException;

import java.awt.event.KeyEvent;

public class testMain {

    public static void main(String[] args) {
        geCore core = new geCore("test-core");

        try {
            core.loadResource("gif_test", "resources/321.gif");
            core.loadAction("sprite-action", "gif_test", COLLISION_BORDER.genDefaultCollisionBorder());
            core.loadResource("test", "resources/123.jpg");
            core.loadResource("sprite_test", "resources/1234.png");
            core.loadResource("sprite2_test", "resources/4321.png");
        } catch (geException e) {
            System.out.println(e);
        }
        core.addLayer("layer-0", "test", 1, -1, 0, 1, .5f);
        core.addLayer("layer-1", "test", 1, 0, 0, 1, .5f);
        core.addSprite("layer-0", "sprite-0", "sprite_test", COLLISION_BORDER.genDefaultCollisionBorder(), 0.25f, 0.25f);
        core.addSprite("layer-0", "sprite-1", "sprite_test", COLLISION_BORDER.genDefaultCollisionBorder(), 0.25f, 0.25f);
        core.spriteMoveTo("sprite-1", -1, 1);
        core.spriteMoveTo("sprite-0", 0.75f, 1);
        core.spriteSetAction("sprite-0", "sprite-action");
//        core.addEvent(new eventTest());

        core.addKeyEvent(KeyEvent.VK_D, new keyEventTest());

        while (true) {
            core.pullEvent();
            core.update();
        }
    }

}
