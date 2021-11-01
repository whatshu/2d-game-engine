package ge.base;

import ge.base.POINT;

public interface MOVABLE {
    void move(float dx, float dy);

    void move(POINT dp);

    void set(float x, float y);

    void set(POINT p);

    POINT getPosition();

    float getX();

    float getY();
}
