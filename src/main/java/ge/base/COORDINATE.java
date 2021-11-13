package ge.base;

public class COORDINATE {
    public enum COORDINATE_TYPE {
        CORNER, CENTER,
    }

    public static COORDINATE windowCoordinate() {
        return new COORDINATE(COORDINATE_TYPE.CORNER, POINT.zero(), 1, 1, 1, 1);
    }

    private final COORDINATE_TYPE coordinateType;
    private final POINT           origin;
    private final float           width;
    private final float           height;
    private final float           xRange;
    private final float           yRange;

    /**
     * @param origin window coordinate, can get from the convert function
     * @param width  window coordinate
     * @param height window coordinate
     */
    public COORDINATE(COORDINATE_TYPE type, POINT origin, float width, float height, float xRange, float yRange) {
        this.coordinateType = type;
        this.origin = origin;
        this.xRange = xRange;
        this.yRange = yRange;
        if (type == COORDINATE_TYPE.CENTER) { // convert to corner which is easier to operate
            this.width = width / 2;
            this.height = height / 2;
        } else {
            this.width = width;
            this.height = height;
        }
    }

    public POINT getStandardPoint(POINT p) {
        return getStandardPoint(p.x, p.y);
    }

    public POINT getStandardPoint(float x, float y) {
        if (coordinateType == COORDINATE_TYPE.CENTER) y = -y; // if center, convert to corner
        POINT p = new POINT(origin.x + width / xRange * x, origin.y + height / yRange * y);
        if (coordinateType == COORDINATE_TYPE.CENTER) p.y = -p.y; // if converted, convert it back
        return p;
    }

    public POINT fromStandardPoint(POINT p) {
        return fromStandardPoint(p.x, p.y);
    }

    public POINT fromStandardPoint(float x, float y) {
        POINT p = new POINT(xRange / width * (x - origin.x), yRange / height * (y - origin.y));
        if (coordinateType == COORDINATE_TYPE.CENTER) p.y = -p.y;
        return p;
    }

    public POINT convertTo(COORDINATE otherCoordinate, POINT point) {
        return convertTo(otherCoordinate, point.x, point.y);
    }

    public POINT convertTo(COORDINATE otherCoordinate, float x, float y) {
        POINT sp = getStandardPoint(x, y);
        return otherCoordinate.fromStandardPoint(sp);
    }

}
