package ge.base;

public class SCREEN_POINT {
    public int x;
    public int y;

    private static SCREEN_POINT last = null;
    private static int          screenWidth;
    private static int          screenHeight;

    public SCREEN_POINT(int x, int y) {
        this.x = x;
        this.y = y;
        last = this;
    }

    public SCREEN_POINT(POINT p) {
        x = (int) (p.x * screenWidth);
        y = (int) (p.y * screenHeight);
        last = this;
    }

    public SCREEN_POINT getLast() {
        return last;
    }

    public static void setScreenSize(int width, int height) {
        screenHeight = height;
        screenWidth = width;
    }
}
