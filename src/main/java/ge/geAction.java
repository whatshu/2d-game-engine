package ge;

import java.util.List;

public class geAction {
    private List<geFrame> frames;
    private int now_at = 0;
    private String name;

    public geAction(String name, List<geFrame> frames) {
        this.name = name;
        this.frames = frames;
    }

    public geFrame nowFrame() {
        return frames.get(now_at);
    }

    public geFrame next() {
        now_at += 1;
        return frames.get(now_at - 1);
    }

    public void reset() {
        now_at = 0;
    }
}
