package ge.base;

public class SCREEN_POINT {
    public int x;
    public int y;

    private static SCREEN_POINT last = null;


    public SCREEN_POINT(int x, int y) {
        this.x = x;
        this.y = y;
        last = this;
    }

    public SCREEN_POINT(SCREEN_POINT O, POINT p, int width, int height) {
        x = (int) (O.x + width * (p.x + 1)/2 );
        y = (int) (O.y + height * (1 - p.y)/2);
        last = this;
    }

    public SCREEN_POINT getLast() {
        return last;
    }

    public static SCREEN_POINT genOriginPoint() {
        return new SCREEN_POINT(0, 0);// layer的初始位置
    }
}
