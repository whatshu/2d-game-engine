package ge;

import ge.base.COLLISION_BORDER;
import ge.util.frameManager;

import java.awt.*;

public class geFrame {
    private COLLISION_BORDER collisionBorder;
    private String imageResourceName;

    public geFrame(String imageName, COLLISION_BORDER collisionBorder) {
        this.imageResourceName = imageName;
        this.collisionBorder = collisionBorder;
    }

    public Image getImage() {
        return frameManager.getFrameManager().get(imageResourceName);
    }

    public COLLISION_BORDER getCollisionBorder(){
        return collisionBorder;
    }

}
