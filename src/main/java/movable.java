public interface movable {
    POINT move(float dx, float dy);

    POINT move(POINT dp);

    void set(float x, float y);

    void set(POINT p);

    POINT getPosition();

    float getX();

    float getY();
}
