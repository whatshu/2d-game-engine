package snake;

import ge.*;
import ge.geException.geException;

public class snakeMain {


    public static void main(String[] args) {
        geCore core = new geCore("snake-core");
        try {
            core.loadResource("ball", "resources/ball.png");
        } catch (geException ignored) {}


    }

}
