package snake;

import ge.*;
import ge.geException.geException;

public class snakeMain {


    public static void main(String[] args) {
        geCore core = new geCore("snake-core");
        core.setSize(1280, 960);
        try {
            core.loadResource("ball", "resources/ball.png");
        } catch (geException ignored) {}

        core.addLayer("ball-layer", "ball", 0, -1, 1, 1, 1);

        core.update();
    }

}
