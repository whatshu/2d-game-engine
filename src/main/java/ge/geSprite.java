package ge;

import ge.base.MOVABLE;
import ge.base.POINT;

import java.util.List;

public interface geSprite extends MOVABLE {
    // border
    float getWidth();

    float getHeight();

    // collision border
    List<POINT> getCollisionBorder();

    // layer of the sprite
    geLayer getLayer();

    // center of the sprite
    POINT getCenter();

    // action
    void setAction(String actionName);

    void nextFrame();

    void resetFrame();
}
