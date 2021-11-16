package ge;

import ge.base.COLLISION_BORDER;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class geAction {
    private List<geFrame> frames;
    private int now_at = 0;
    private String name;

    public geAction(String name, List<geFrame> frames) {
        this.name = name;
        this.frames = frames;
    }

    public geAction(String name, List<Image> images, List<COLLISION_BORDER> borders) {
        this.name = name;
        frames = new ArrayList<>();
        int len = Math.min(images.size(), borders.size());
        for (int i = 0; i < len; i++) {
            frames.add(new geFrame(images.get(i), borders.get(i)));
        }
    }

    public geAction(String name, List<Image> images, COLLISION_BORDER unifyBorder) {
        this.name = name;
        frames = new ArrayList<>();
        for (java.awt.Image image : images) {
            frames.add(new geFrame(image, unifyBorder));
        }
    }

    public String getName() {
        return name;
    }

    public geFrame nowFrame() {
        return frames.get(now_at);
    }

    public geFrame next() {
        now_at = (now_at + 1) % frames.size();
        return frames.get(now_at);
    }

    public void reset() {
        now_at = 0;
    }
}
