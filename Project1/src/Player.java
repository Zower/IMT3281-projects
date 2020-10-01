package Project1.src;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;
import java.awt.Container;

/**
 * Class representing a player.
 */
public class Player extends GUIElement {
    private JLabel playerImage;
    private int nextTile;

    public Player(int tileNo, int x, int y, BufferedImage playerIcon) {
        super(tileNo, x, y);
        this.playerImage = new JLabel(new ImageIcon(playerIcon));
    }

    /**
     * Sets the size of the JLabel object.
     * 
     * @param width  The width to set.
     * @param height The height to set.
     */
    private void setSize(int width, int height) {
        playerImage.setSize(width, height);
    }

    /**
     * Sets the local variables and the playerImage to the given coordinates.
     * 
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    private void setLocation(int x, int y) {
        setX(x);
        setY(y);
        playerImage.setLocation(x, y);
    }

    public void setNextTile(int nextTile) {
        this.nextTile = nextTile;
    }

    public int getNextTile() {
        return nextTile;
    }

    /**
     * Configures size and location, then adds it to a container
     * 
     * @param c The container to add the playerImage to.
     */
    public void config(Container c) {
        setSize(60, 60);
        setLocation(getX(), getY());
        c.add(playerImage);
    }

    /**
     * Advance the player to a certain tile.
     * 
     * @param board The playing board.
     * @param tile  The tile to advance to.
     */
    public void advance(BoardPiece[] board, int tile) {
        int[] tilePos = getTilePos(board, tile - 1);
        setLocation(tilePos[0], tilePos[1]);
        setTile(tile);
    }

    /**
     * Gets the coordinates for a given board piece.
     * 
     * @param board The playing board to find the tile in.
     * @param tile  The tile to search for.
     * @return An array where [0] is the x coordinate and [1] is the y coordinate.
     */
    private int[] getTilePos(BoardPiece[] board, int tile) {
        int[] coords = { board[tile].getX() + 22, board[tile].getY() + 20 };
        return coords;
    }

    /**
     * Sets location to x and y, and resets local variables.
     * 
     * @param x x coordinate, should be starting field.
     * @param y y coordinate, should be starting field.
     */
    public void reset(int x, int y) {
        resetSuper(x, y);
        setLocation(x, y);
        this.nextTile = 0;
    }

}
