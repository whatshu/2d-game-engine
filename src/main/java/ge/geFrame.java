package ge;

import ge.base.BORDER;

import java.awt.*;

public class geFrame {
    private BORDER collisionBorder;
    private Image  image;

    public geFrame(Image image, BORDER collisionBorder) {
        this.image = image;
        this.collisionBorder = collisionBorder;
    }

    public Image getImage() {
        return image;
    }

}
