package Project1.src;

public class GUIElement {
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

    public void setTile(int tileNo) {
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void print() {
        System.out.println("Tile no: " + Integer.toString(tileNo));
        System.out.println("X: " + Integer.toString(x));
        System.out.println("Y: " + Integer.toString(y));
    }

    public void resetSuper(int x, int y) {
        this.x = x;
        this.y = y;
        this.tileNo = 1;
    }

}
