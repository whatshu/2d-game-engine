import ge.base.COLLISION_BORDER;
import ge.geCore;
import ge.geException.geException;
import ge.geKeyBoard;

public class testMain {

    public static void main(String[] args) {
        geCore core = new geCore();

        try {
            core.loadResource("gif_test", "resources/hammer.gif");
            core.loadAction("sprite-action", "gif_test", COLLISION_BORDER.genDefaultCollisionBorder());
            core.loadResource("test", "resources/jpg.jpg");
            core.loadResource("sprite_test", "resources/bw.jpg");
            core.loadResource("sprite2_test", "resources/1004.jpg");
        } catch (geException e) {
            System.out.println(e);
        }
        core.addLayer("layer-0", "test", 1, -1f, 1f, 2f, 2f);
//        core.addSprite("layer-0", "sprite-0", "sprite_test",
//                COLLISION_BORDER.genDefaultCollisionBorder(), 1f, 1f);
        core.addSprite("layer-0", "sprite-1", "sprite2_test",
                COLLISION_BORDER.genDefaultCollisionBorder(), 0.5f, 0.5f);
//        core.spriteMoveTo("sprite-0", -1.5f, -1.5f);
        core.spriteMoveTo("sprite-1", 2f, -4f);

//        core.spriteSetAction("sprite-0", "sprite-action");

        core.addEvent(new eventTest());


        geKeyBoard.getInstance().setObject(core, "sprite-1", "layer-0");

        while (true) {
            core.pullEvent();
            core.update();
        }
    }

}
