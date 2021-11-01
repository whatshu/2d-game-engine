package ge.base;

import ge.base.POINT;

public interface MOVABLE {
    POINT move(float dx, float dy);

    POINT move(POINT dp);

    void set(float x, float y);

    void set(POINT p);

    POINT getPosition();

    float getX();

    float getY();
}
