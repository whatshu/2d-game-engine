package ge.base;

public class POINT {
    public float x;
    public float y;

    public POINT(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public LINE lineWithAnotherPoint(POINT other) {
        return new LINE(this, other);
    }

    public static POINT zero() {
        return new POINT(0, 0);
    }

}
