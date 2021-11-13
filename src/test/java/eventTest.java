import ge.geCore;
import ge.geEvent;
import ge.geSprite;

public class eventTest implements geEvent {
    private long last_frame_time = System.currentTimeMillis();

    private int direction = -1;

    @Override
    public void operate(geCore core) {
        last_frame_time = System.currentTimeMillis();
        core.spriteNextFrame("sprite-0");
        core.spriteMove("sprite-0", direction * 0.01f, 0);

        geSprite sprite1 = core.getSpriteByName("sprite-0");
        geSprite sprite2 = core.getSpriteByName("sprite-1");
        if (sprite1.contact(sprite2)) {
            System.out.println("contact");
            direction = -direction;
        }
    }

    @Override
    public boolean predicate() {
        return System.currentTimeMillis() - last_frame_time > 10;
    }
}
