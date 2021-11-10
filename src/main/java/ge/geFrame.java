package ge;

import ge.base.COLLISION_BORDER;
import ge.util.imageManager;

import java.awt.*;

public class geFrame {
    private COLLISION_BORDER collisionBorder;
    private Image image;

    public geFrame(String imageName, COLLISION_BORDER collisionBorder) {
        this.image = imageManager.getFrameManager().get(imageName);
        this.collisionBorder = collisionBorder;
    }

    public geFrame(Image image, COLLISION_BORDER collisionBorder) {
        this.image = image;
        this.collisionBorder = collisionBorder;
    }

    public Image getImage() {
        return image;
    }

    public COLLISION_BORDER getCollisionBorder() {
        return collisionBorder;
    }

}
