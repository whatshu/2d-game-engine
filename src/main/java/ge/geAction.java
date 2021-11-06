package ge;

import ge.base.COLLISION_BORDER;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class geAction {
    private List<geFrame> frames;
    private int now_at = 0;

    public geAction(List<geFrame> frames) {
        this.frames = frames;
    }

    public geAction(List<Image> images, List<COLLISION_BORDER> borders) {
        frames = new ArrayList<>();
        int len = Math.min(images.size(), borders.size());
        for (int i = 0; i < len; i++) {
            frames.add(new geFrame(images.get(i), borders.get(i)));
        }
    }

    public geAction(List<Image> images, COLLISION_BORDER unifyBorder) {
        frames = new ArrayList<>();
        for (java.awt.Image image : images) {
            frames.add(new geFrame(image, unifyBorder));
        }
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
