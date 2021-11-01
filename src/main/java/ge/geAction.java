package ge;

import java.util.ArrayList;
import java.util.List;

public class geAction {
    private List<geFrame> frames = new ArrayList<>();
    private int           now_at = 0;

    public geAction() {
    }

    public geFrame next() {
        now_at += 1;
        return frames.get(now_at - 1);
    }

    public void reset() {
        now_at = 0;
    }
}
