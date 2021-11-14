import ge.geCore;
import ge.geEvent;

public class SPressed implements geEvent {
    @Override
    public void operate(geCore core) {
        core.getLayerByName("layer-1").move(0, -0.01f);
    }

    @Override
    public boolean predicate() {
        return true;
    }
}
