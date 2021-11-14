package snake;

import ge.*;
import ge.base.COLLISION_BORDER;
import ge.base.KEY;
import ge.base.POINT;
import ge.geException.geException;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class snake {
    private class snakePoint {
        public int x, y;

        public snakePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // constants
    private final long UPDATE_GAP = 500; // millisecond
    private final int WIDTH = 10;
    private final int HEIGHT = 10;
    private final float GRID_WIDTH = 1.0f / WIDTH;
    private final float GRID_HEIGHT = 1.0f / HEIGHT;

    // variables
    private geCore core = new geCore("snake-core");
    private List<geSprite> body = new ArrayList<>();
    private int dx = -1;
    private int dy = 0;
    private int next_dx = -1;
    private int next_dy = 0;
    private geSprite food;
    private int foodX = -2;
    private int foodY = 0;

    private POINT convertToGePoint(snakePoint p) {
        return new POINT(((float) p.x) / ((float) WIDTH) * 2.0f, ((float) p.y) / ((float) HEIGHT) * 2.0f);
    }

    private snakePoint convertFromGePoint(POINT p) {
        return new snakePoint(((int) (p.x * WIDTH / 2)), ((int) (p.y * HEIGHT / 2)));
    }

    private void genFood() {
        boolean goodPoint = false;
        while (true) {
            foodX = ((int) (Math.random() * 10 - 5));
            foodY = ((int) (Math.random() * 10 - 5));
            goodPoint = true;
            for (geSprite b : body) {
                snakePoint p = convertFromGePoint(b.getPosition());
                if (p.x == foodX && p.y == foodY) {
                    goodPoint = false;
                    break;
                }
            }
            if (goodPoint) {
                break;
            }
        }
        food.moveTo(convertToGePoint(new snakePoint(foodX, foodY)));
    }

    private void nextFrame() {
        boolean haveFood = false;
        snakePoint nextHeadPos = convertFromGePoint(body.get(0).getPosition());
        nextHeadPos.x += dx;
        nextHeadPos.y += dy;
        if (nextHeadPos.x >= WIDTH / 2) nextHeadPos.x = -WIDTH / 2;
        if (nextHeadPos.x < -WIDTH / 2) nextHeadPos.x = WIDTH / 2 - 1;
        if (nextHeadPos.y >= HEIGHT / 2) nextHeadPos.y = -HEIGHT / 2;
        if (nextHeadPos.y < -HEIGHT / 2) nextHeadPos.y = HEIGHT / 2 - 1;

        if (nextHeadPos.x == foodX && nextHeadPos.y == foodY) {
            body.add(core.addSprite("background", "body-" + body.size(), "circle", COLLISION_BORDER.genDefaultCollisionBorder(), GRID_WIDTH, GRID_HEIGHT));
            body.get(body.size() - 1).moveTo(body.get(body.size() - 2).getPosition());
            haveFood = true;
            genFood();
        }

        for (int i = body.size() - 1; i > 0; i--) {
            if (haveFood && i == body.size() - 1) continue;
            body.get(i).moveTo(body.get(i - 1).getPosition());
        }

        body.get(0).moveTo(convertToGePoint(nextHeadPos));

        dx = next_dx;
        dy = next_dy;
    }

    private void init() {
        // resize window
        core.setSize(560, 560);

        // load resource
        try {
            core.loadResource("circle", "resources/circle.png");
            core.loadResource("food", "resources/1234.png");
            core.loadResource("background", "resources/plain-background.png");
        } catch (geException ignored) {
        }

        // add background layer
        core.addLayer("background", "background", 0, -1, 1, 1, 1);

        // add default body
        body.add(core.addSprite("background", "body-0", "circle", COLLISION_BORDER.genDefaultCollisionBorder(), GRID_WIDTH, GRID_HEIGHT));
        body.add(core.addSprite("background", "body-1", "circle", COLLISION_BORDER.genDefaultCollisionBorder(), GRID_WIDTH, GRID_HEIGHT));
        body.add(core.addSprite("background", "body-2", "circle", COLLISION_BORDER.genDefaultCollisionBorder(), GRID_WIDTH, GRID_HEIGHT));
        body.get(0).moveTo(convertToGePoint(new snakePoint(0, 0)));
        body.get(1).moveTo(convertToGePoint(new snakePoint(1, 0)));
        body.get(2).moveTo(convertToGePoint(new snakePoint(2, 0)));

        // add food
        food = core.addSprite("background", "food", "food", COLLISION_BORDER.genDefaultCollisionBorder(), GRID_WIDTH, GRID_HEIGHT);
        genFood();

        // update window event
        core.addEvent(new geEvent() {
            private long lastUpdateTime = System.currentTimeMillis();

            @Override
            public void operate(geCore core) {
                nextFrame();
                lastUpdateTime = System.currentTimeMillis();
            }

            @Override
            public boolean predicate() {
                return System.currentTimeMillis() > lastUpdateTime + UPDATE_GAP;
            }
        });

        // keyboard events(w a s d)
        core.addKeyEvent(KEY.getKey(KeyEvent.VK_W, KEY.KEY_TYPE.PRESSED), new geEvent() {
            @Override
            public void operate(geCore core) {
                next_dx = 0;
                next_dy = 1;
            }

            @Override
            public boolean predicate() {
                return dy != -1;
            }
        });

        core.addKeyEvent(KEY.getKey(KeyEvent.VK_S, KEY.KEY_TYPE.PRESSED), new geEvent() {
            @Override
            public void operate(geCore core) {
                next_dx = 0;
                next_dy = -1;
            }

            @Override
            public boolean predicate() {
                return dy != 1;
            }
        });

        core.addKeyEvent(KEY.getKey(KeyEvent.VK_A, KEY.KEY_TYPE.PRESSED), new geEvent() {
            @Override
            public void operate(geCore core) {
                next_dx = -1;
                next_dy = 0;
            }

            @Override
            public boolean predicate() {
                return dx != 1;
            }
        });

        core.addKeyEvent(KEY.getKey(KeyEvent.VK_D, KEY.KEY_TYPE.PRESSED), new geEvent() {
            @Override
            public void operate(geCore core) {
                next_dx = 1;
                next_dy = 0;
            }

            @Override
            public boolean predicate() {
                return dx != -1;
            }
        });
    }

    public void run() {
        while (true) {
            core.pullEvent();
            core.update(); // update data exclude the window for data need more framerate to ensure receiving every user input
        }
    }

    public snake() {
        init();
        run();
    }

    public static void main(String[] args) {
        snake snake = new snake();
    }

}
