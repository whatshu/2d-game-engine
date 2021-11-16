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
    private STATE gameState = STATE.PRELOADING;
    private geLayer storyBackground;
    private geLayer[] forest = new geLayer[2];
    private geSprite bird;
    private geLayer endingBackground;
    private geSprite castle;
    private geSprite princess;

    // preloading
    private boolean preloadingEnterPressed = false;
    private long preloadingEnterPressTime;

    // moving
    private final int CAL_FRAMERATE = 480;
    private final int MOVE_FRAMERATE = 120;
    private final float FRACTION = 1.5f;
    private final float PRESS_ACC = 1.1f;
    private final float MAX_VELOCITY = 2.0f;
    private final float BIRD_WIDTH = 0.125f;
    private final float BIRD_HEIGHT = 0.25f;
    private final float CASTLE_WIDTH = 1f;
    private final float CASTLE_HEIGHT = 2f;
    private float[] velocity = new float[2]; // 0 -> vertical; 1 -> horizontal
    private float[] acceleration = new float[2];
    private long lastUpdateTime;
    private int forestNum = 0;
    private float traveledDistance = 0;

    private void preloadingInit() {
        core.addKeyEvent(KEY.getKey(KeyEvent.VK_ENTER, KEY.KEY_TYPE.PRESSED), new geEvent() {
            @Override
            public void operate(geCore core) {
                preloadingEnterPressed = true;
                preloadingEnterPressTime = System.currentTimeMillis();
            }

            @Override
            public boolean predicate() {
                return gameState == STATE.PRELOADING;
            }
        });

        core.addEvent(new geEvent() { // movement
            private long lastUpdateTime = System.currentTimeMillis();

            @Override
            public void operate(geCore core) {
                if (System.currentTimeMillis() - lastUpdateTime > 10) {
                    storyBackground.move(0, 0.05f);
                    lastUpdateTime = System.currentTimeMillis();
                }

                if (System.currentTimeMillis() - preloadingEnterPressTime > 800) {
                    storyBackground.setVisible(false);
                    gameState = STATE.RUNNING;
                }
            }

            @Override
            public boolean predicate() {
                return preloadingEnterPressed && gameState == STATE.PRELOADING;
            }
        });
    }

    private void preloading() {
    }

    private void runningInit() {
        bird.setAction("bird-fly");
        bird.setStatic();
        castle.moveTo(1, 2);

        // W
        core.addKeyEvent(KEY.getKey(KeyEvent.VK_W, KEY.KEY_TYPE.PRESSED), new geEvent() {
            @Override
            public void operate(geCore core) {
                acceleration[0] = PRESS_ACC;
            }

            @Override
            public boolean predicate() {
                return gameState == STATE.RUNNING;
            }
        });

        // S
        core.addKeyEvent(KEY.getKey(KeyEvent.VK_S, KEY.KEY_TYPE.PRESSED), new geEvent() {
            @Override
            public void operate(geCore core) {
                acceleration[0] = -PRESS_ACC;
            }

            @Override
            public boolean predicate() {
                return gameState == STATE.RUNNING;
            }
        });

        // A
        core.addKeyEvent(KEY.getKey(KeyEvent.VK_A, KEY.KEY_TYPE.PRESSED), new geEvent() {
            @Override
            public void operate(geCore core) {
                acceleration[1] = -PRESS_ACC;
            }

            @Override
            public boolean predicate() {
                return gameState == STATE.RUNNING;
            }
        });

        // D
        core.addKeyEvent(KEY.getKey(KeyEvent.VK_D, KEY.KEY_TYPE.PRESSED), new geEvent() {
            @Override
            public void operate(geCore core) {
                acceleration[1] = PRESS_ACC;
            }

            @Override
            public boolean predicate() {
                return gameState == STATE.RUNNING;
            }
        });

        core.addKeyEvent(KEY.getKey(KeyEvent.VK_SPACE, KEY.KEY_TYPE.PRESSED), new geEvent() {
            @Override
            public void operate(geCore core) {
                gameState = STATE.END;
            }

            @Override
            public boolean predicate() {
                return gameState == STATE.RUNNING;
            }
        });

        // fraction
        core.addEvent(new geEvent() {
            private long lastCalTime = System.currentTimeMillis();

            @Override
            public void operate(geCore core) {
                for (int d = 0; d < 2; d++) {
                    if (ge.util.compare.floatEqual(acceleration[d], 0.0f)) continue;

                    if (acceleration[d] > 0) {
                        acceleration[d] -= FRACTION / CAL_FRAMERATE;
                        if (acceleration[d] < 0) {
                            acceleration[d] = 0;
                        }
                    } else {
                        acceleration[d] += FRACTION / CAL_FRAMERATE;
                        if (acceleration[d] > 0) {
                            acceleration[d] = 0;
                        }
                    }
                }
                lastCalTime = System.currentTimeMillis();
            }

            @Override
            public boolean predicate() {
                return gameState == STATE.RUNNING && System.currentTimeMillis() - lastCalTime > 1000.0f / CAL_FRAMERATE;
            }
        });

        // update position
        core.addEvent(new geEvent() {
            private long lastCalTime = System.currentTimeMillis();

            @Override
            public void operate(geCore core) {
                for (int d = 0; d < 2; d++) {
                    velocity[d] += acceleration[d] / MOVE_FRAMERATE;
                    if (ge.util.compare.floatEqual(acceleration[d], 0.0f)) {
                        velocity[d] = 0;
                    }
                    if (velocity[d] > MAX_VELOCITY) {
                        velocity[d] = MAX_VELOCITY;
                    }
                    if (velocity[d] < -MAX_VELOCITY) {
                        velocity[d] = -MAX_VELOCITY;
                    }
                }
                bird.move(velocity[1] / MOVE_FRAMERATE, velocity[0] / MOVE_FRAMERATE);
                traveledDistance += velocity[1] / MOVE_FRAMERATE;

                if (bird.getX() > 0.3 - BIRD_WIDTH / 2) {
                    bird.moveTo(0.3f - BIRD_WIDTH / 2, bird.getY());
                    forest[0].move(-velocity[1] / MOVE_FRAMERATE, 0);
                    forest[1].move(-velocity[1] / MOVE_FRAMERATE, 0);
                }
                if (bird.getX() < -0.3 - BIRD_WIDTH / 2) {
                    bird.moveTo(-0.3f - BIRD_WIDTH / 2, bird.getY());
                    forest[0].move(-velocity[1] / MOVE_FRAMERATE, 0);
                    forest[1].move(-velocity[1] / MOVE_FRAMERATE, 0);
                }

                for (int i = 0; i < 2; i++) {
                    if (forest[i].getX() < -3) {
                        forest[i].moveTo(forest[1 - i].getX() + 2, forest[1 - i].getY());
                    }
                    if (forest[i].getX() > 1) {
                        forest[i].moveTo(forest[1 - i].getX() - 2, forest[1 - i].getY());
                    }
                }

                if (bird.getY() > 1) {
                    bird.moveTo(bird.getX(), 1);
                    acceleration[0] = 0;
                    velocity[0] = 0;
                }
                if (bird.getY() < -(1 - BIRD_HEIGHT)) {
                    bird.moveTo(bird.getX(), -(1 - BIRD_HEIGHT));
                    acceleration[0] = 0;
                    velocity[0] = 0;
                }
                lastCalTime = System.currentTimeMillis();
            }

            @Override
            public boolean predicate() {
                return gameState == STATE.RUNNING && System.currentTimeMillis() - lastCalTime > 1000.0f / MOVE_FRAMERATE;
            }
        });

        lastUpdateTime = System.currentTimeMillis();
    }

    private void running() {
        if (System.currentTimeMillis() - lastUpdateTime > 1000 / 10) {
            bird.nextFrame();
            lastUpdateTime = System.currentTimeMillis();
        }
        if (traveledDistance > 1.5) {
            gameState = STATE.END;
        }
    }

    private void endInit() {
        core.addEvent(new geEvent() {
            private long lastUpdateTime = System.currentTimeMillis();

            @Override
            public void operate(geCore core) {
                princess.nextFrame();
                lastUpdateTime = System.currentTimeMillis();
            }

            @Override
            public boolean predicate() {
                return gameState == STATE.END && System.currentTimeMillis() - lastUpdateTime > 1000 / 10;
            }

        });
    }

    private void end() {
        endingBackground.setDepth(2);
    }

    private void init() {
        core.setSize(700, 400);

        try {
            core.loadResource("storyBackground", "./resources/princessAdventure/storyBackground.jpg");
            core.loadResource("forest", "./resources/princessAdventure/forestBackground.jpg");
            core.loadResource("ending", "./resources/princessAdventure/ending.jpg");
            core.loadResource("castle", "./resources/princessAdventure/castle.png");
            List<Image> birdImages = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                core.loadResource("bird-" + i, "./resources/princessAdventure/bird/" + i + ".png");
                birdImages.add(core.getImageManager().get("bird-" + i));
            }
            core.loadAction("bird-fly", birdImages, COLLISION_BORDER.genDefaultCollisionBorder());

            List<Image> princessImages = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                core.loadResource("princess-" + i, "./resources/princessAdventure/princess/" + i + ".png");
                princessImages.add(core.getImageManager().get("princess-" + i));
            }
            core.loadAction("princess-move", princessImages, COLLISION_BORDER.genDefaultCollisionBorder());
        } catch (geException ignored) {
        }

        storyBackground = core.addLayer("storyBackground", "storyBackground", 10, -1, 1, 1, 1);
        forest[0] = core.addLayer("forest", "forest", 1, -1, 1, 1, 1);
        forest[1] = core.addLayer("forest2", "forest", 0, 1, 1, 1, 1);

        castle = core.addSprite("forest", "castle", "castle", COLLISION_BORDER.genDefaultCollisionBorder(), CASTLE_WIDTH, CASTLE_HEIGHT);
        bird = core.addSprite("forest", "bird", "bird-0", COLLISION_BORDER.genDefaultCollisionBorder(), BIRD_WIDTH, BIRD_HEIGHT);

        endingBackground = core.addLayer("endingBackground", "ending", -1, -1, 1, 1, 1);
        princess = core.addSprite("endingBackground", "princess", "princess-0", COLLISION_BORDER.genDefaultCollisionBorder(), 0.3f, 0.5f);
        princess.setAction("princess-move");
        princess.moveTo(-0.25f, 0.25f);

        preloadingInit();
        runningInit();
        endInit();
    }

    private void run() {
        switch (gameState) {
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
