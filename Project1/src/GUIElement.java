package Project1.src;

/**
 * Parent-class for some GUI elements.
 */
public abstract class GUIElement {
    private int x;
    private int y;
    private int tileNo;

    public GUIElement() {

    }

    public GUIElement(int tileNo, int x, int y) {
        this.tileNo = tileNo;
        this.x = x;
        this.y = y;
    }

    protected void setTile(int tileNo) {
        this.tileNo = tileNo;
    }

    public int getTile() {
        return tileNo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    /**
     * Reset local variables and set x and y.
     * 
     * @param x The x coordinate to set.
     * @param y The y coordinate to set.
     */
    protected void resetSuper(int x, int y) {
        this.x = x;
        this.y = y;
        this.tileNo = 1;
    }

}
