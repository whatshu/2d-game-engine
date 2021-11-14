import ge.geCore;
import ge.geEvent;

public class keyEventTest implements geEvent {
    @Override
    public void operate(geCore core) {
        core.getLayerByName("layer-1").move(0.01f, 0);
    }

    @Override
    public boolean predicate() {
        return true;
    }
}
