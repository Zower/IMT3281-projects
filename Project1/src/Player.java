package Project1.src;

import javax.swing.JLabel;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Player extends GUIElement {
    private JLabel playerImage;
    final private int TILENO = 36;

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

    public void config(JFrame frame) {
        setSize(60, 60);
        setLocation(getX(), getY());
        frame.add(playerImage);
    }

    public void advancePos(int advanceNo) {
        int tempTile = getTile() + advanceNo;
        // tempTile.checkObstacles();
        if (tempTile == 36) {
            // won = true;
        }
        int[] tilePos = getTilePos(tempTile);
        playerImage.setLocation(tilePos[0], tilePos[1]);
        setTile(tempTile);
    }

    public int[] getTilePos(int tile) {
        int[] test = { 3, 2 };
        return test;
    }

    /**
     * Roll two die.
     * 
     * @return int array containing the dice rolls.
     */
    public int[] rollDie() {
        int[] die = { (int) (Math.random() * 6 + 1), (int) (Math.random() * 6 + 1) };
        return die;
    }
}
