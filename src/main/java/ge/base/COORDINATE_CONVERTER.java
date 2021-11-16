package ge.base;

public class COORDINATE_CONVERTER {
    private COORDINATE fromCor;
    private COORDINATE toCor;

    public COORDINATE_CONVERTER(COORDINATE from, COORDINATE to) {
        fromCor = from;
        toCor = to;
    }

    public POINT getNewPosition(POINT p) {
        return fromCor.convertTo(toCor, p);
    }

    public POINT getNewPosition(float x, float y) {
        return fromCor.convertTo(toCor, x, y);
    }

}
