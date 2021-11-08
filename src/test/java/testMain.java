import ge.base.COLLISION_BORDER;
import ge.geCore;
import ge.geException.geException;

public class testMain {

    public static void main(String[] args) {
        geCore core = new geCore();

        try {
            core.loadResource("gif_test", "resources/321.gif");
            core.loadAction("sprite-action", "gif_test", COLLISION_BORDER.genDefaultCollisionBorder());
            core.loadResource("test", "resources/123.jpg");
            core.loadResource("sprite_test", "resources/1234.png");
        } catch (geException e) {
            System.out.println(e);
        }
        core.addLayer("layer-1", "test", 2, -1, 1, 2, 1.2f);
        core.addLayer("layer-0", "test", 1, -1, 0, 2, 1);
        core.addSprite("layer-0", "sprite-0", "sprite_test", COLLISION_BORDER.genDefaultCollisionBorder(), 0.5f, 0.5f);
        core.spriteSetAction("sprite-0", "sprite-action");

        core.addEvent(new eventTest());

        while (true) {
            core.pullEvent();
            core.update();
        }
    }

}
