package ge.base;

import ge.base.POINT;

public interface MOVABLE {
    void move(float dx, float dy);

    void move(POINT dp);

    void moveTo(float x, float y);

    void moveTo(POINT p);

    POINT getPosition();

    float getX();

    float getY();
}
