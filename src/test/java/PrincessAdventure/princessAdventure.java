package PrincessAdventure;

import ge.base.COLLISION_BORDER;
import ge.base.KEY;
import ge.geCore;
import ge.geEvent;
import ge.geException.geException;
import ge.geLayer;
import ge.geSprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class princessAdventure {
    private enum STATE {
        PRELOADING, RUNNING, END,
    }

    private geCore core = new geCore("princessAdventureCore");
    private boolean gameRunning = true;
    private STATE nowState = STATE.PRELOADING;
    private geLayer storyBackground;
    private geLayer forest;
    private geSprite bird;

    private void preloading() {
    }

    private void running() {
        bird.nextFrame();
    }

    private void end() {

    }

    private void init() {
        core.setSize(700, 400);

        try {
            core.loadResource("storyBackground", "./resources/princessAdventure/storyBackground.jpg");
            core.loadResource("forest", "./resources/princessAdventure/forest.png");
            List<Image> images = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                core.loadResource("bird-" + i, "./resources/princessAdventure/bird/" + i + ".png");
                images.add(core.getImageManager().get("bird-" + i));
            }
            core.loadAction("bird-fly", images, COLLISION_BORDER.genDefaultCollisionBorder());
        } catch (geException ignored) {
        }

        storyBackground = core.addLayer("storyBackground", "storyBackground", 10, -1, 1, 1, 1);
        forest = core.addLayer("forest", "forest", 1, -1, 1, 1, 1);

        bird = core.addSprite("forest", "bird", "bird-0", COLLISION_BORDER.genDefaultCollisionBorder(), 0.125f, 0.25f);
        bird.setAction("bird-fly");

        core.addKeyEvent(KEY.getKey(KeyEvent.VK_ENTER, KEY.KEY_TYPE.PRESSED), new geEvent() {
            @Override
            public void operate(geCore core) {
                storyBackground.setVisible(false);
                nowState = STATE.RUNNING;
            }

            @Override
            public boolean predicate() {
                return nowState == STATE.PRELOADING;
            }
        });
    }

    private void run() {
        switch (nowState) {
            case PRELOADING:
                preloading();
                break;
            case RUNNING:
                running();
                break;
            case END:
                end();
                break;
        }
        core.pullEvent();
        core.update();
    }

    private void start() {
        init();
        while (gameRunning) {
            run();
        }
    }

    public princessAdventure() {
        start();
    }

    public static void main(String[] args) {
        princessAdventure p = new princessAdventure();
    }

}
