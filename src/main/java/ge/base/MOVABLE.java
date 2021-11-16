package ge.base;

public interface MOVABLE {
    void move(float dx, float dy);

    void move(POINT dp);

    void moveTo(float x, float y);

    void moveTo(POINT p);

    POINT getPosition();

    POINT getWindowPosition();

    float getX();

    float getY();

    COORDINATE getCoordinate();
}
