package PrincessAdventure;

import ge.base.KEY;
import ge.geCore;
import ge.geEvent;
import ge.geException.geException;

import java.awt.event.KeyEvent;

public class princessAdventure {

    public static void main(String[] args) {
        geCore core = new geCore("princessAdventureCore");

        try {
            core.loadResource("start", "./resources/princessAdventure/start.png");
            core.loadResource("image", "./resources/123.jpg");
        } catch (geException ignored) {
        }

        core.addLayer("start", "start", 0, -1, 1, 1, 1);
        core.addLayer("image", "image", 1, -1, 1, 1, 1);

        core.addKeyEvent(KEY.getKey(KeyEvent.VK_ENTER, KEY.KEY_TYPE.PRESSED), new geEvent() {
            @Override
            public void operate(geCore core) {
                core.layerSetInvisible("start");
            }

            @Override
            public boolean predicate() {
                return true;
            }
        });

        for (int i = 0; i < 800; i++) {
            core.layerMove("image", 0, 0.1f);
            core.delay(100);
            core.pullEvent();
            core.update();
        }

    }

}
