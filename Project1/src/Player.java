package Project1.src;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;
import java.awt.Container;

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
        playerImage.revalidate();
    }

    public void setNextTile(int nextTile) {
        this.nextTile = nextTile;
    }

    public int getNextTile() {
        return nextTile;
    }

    public void config(Container c) {
        setSize(60, 60);
        setLocation(getX(), getY());
        c.add(playerImage);
    }

    public void advance(BoardPiece[] board, int tile) {
        int[] tilePos = getTilePos(board, tile - 1);
        setLocation(tilePos[0], tilePos[1]);
        setTile(tile);
    }

    public int[] getTilePos(BoardPiece[] board, int tile) {
        int[] coords = { board[tile].getX() + 22, board[tile].getY() + 20 };
        return coords;
    }

}
