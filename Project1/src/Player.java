package Project1.src;

import javax.swing.JLabel;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Player extends GUIElement {
    private JLabel playerImage;
    private int nextTile;

    public Player(int tileNo, int x, int y, BufferedImage playerIcon) {
        super(tileNo, x, y);
        this.playerImage = new JLabel(new ImageIcon(playerIcon));
    }

    public void setSize(int width, int height) {
        playerImage.setSize(width, height);
    }

    public void setLocation(int x, int y) {
        playerImage.setLocation(x, y);
    }

    public void setNextTile(int nextTile) {
        this.nextTile = nextTile;
    }

    public int getNextTile() {
        return nextTile;
    }

    public void config(JFrame frame) {
        setSize(60, 60);
        setLocation(getX(), getY());
        frame.add(playerImage);
    }

    public void advance(int[] coords, int tile) {
        setLocation(coords[0], coords[1]);
        setTile(tile);
    }

}
