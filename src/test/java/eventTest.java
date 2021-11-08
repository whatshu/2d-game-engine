import ge.geCore;
import ge.geEvent;

public class eventTest implements geEvent {
    private long last_frame_time = System.currentTimeMillis();

    @Override
    public void operate(geCore core) {
        last_frame_time = System.currentTimeMillis();
        core.spriteNextFrame("sprite-0");
        core.spriteMove("sprite-0", 0.05f, 0);
    }

    @Override
    public boolean predicate() {
        return System.currentTimeMillis() - last_frame_time > 100;
    }
}
