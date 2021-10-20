public interface geLayer extends movable {
    // visible
    void getVisible();

    void setVisible(boolean v);

    // depth
    int getDepth();

    void switchDepth(int otherLayer);

    // sprite
    void addSprite(geSprite s);

    void removeSprite(geSprite s);
}
