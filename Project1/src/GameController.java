package Project1.src;

import java.util.HashMap;

import java.awt.Container;
import javax.swing.AbstractButton;

import java.awt.event.*;

/**
 * Class handling the controlling of the game, controls the turn and other
 * related things.
 */
public class GameController implements ActionListener {
    private int turn;
    final private int LASTTILE = 36;
    private boolean toBeCycled;
    private boolean doubleRoll;
    private Player[] players = new Player[2];
    private BoardPiece[] board = new BoardPiece[36];
    private HashMap<Integer, Integer> obstacles = new HashMap<Integer, Integer>();

    GameController() {
        this.turn = 0;
        this.toBeCycled = true;
        this.doubleRoll = false;
        this.obstacles = populateObstacles();
    }

    GameController(int turn, Player player1, Player player2, BoardPiece[] board) {
        this.turn = turn;
        this.toBeCycled = true;
        this.doubleRoll = false;
        this.players[0] = player1;
        this.players[1] = player2;
        this.board = board;
    }

    /**
     * Advances (graphically and otherwise) the current players position.
     * 
     * @param advanceNo The amount of tiles to move.
     * @param doubleR   Whether the player rolled two equal die.
     * @return boolean representing whether the game was won or not.
     */
    public boolean advancePos(int advanceNo, boolean doubleR) {
        doubleRoll = doubleR;
        int tempTile = players[turn].getTile() + advanceNo;

        if (tempTile == LASTTILE) { // Game is won
            players[turn].advance(board, LASTTILE);
            return true;
        } else if (tempTile > LASTTILE) { // The player rolled outside the playing field
            if (!doubleRoll) {
                cycleTurn();
            }
            return false;
        }

        int obstTile = checkObstacles(tempTile);

        players[turn].advance(board, tempTile); // Advance to the tile the player rolled to.
        if (obstTile == 0) { // No obstacles.
            if (!doubleRoll) { // Not equal die.
                cycleTurn();
            }

        } else { // There was an obstacle.
            toBeCycled = false;
            players[turn].setNextTile(obstTile); // The new tile is remembered inside the Player class.
            board[tempTile - 1].addActionListener(this); // Action listener on the corresponding button.
        }

        return false;

    }

    /**
     * Simply checks if a tile is an "obstacle", that is to say the tile is either
     * the first step on a ladder or a snake-head.
     * 
     * @param tile The tile to check
     * @return If the tile was an "obstacle", it will return the new tile to jump
     *         to. If 12 -> 2 is an obstacle, and 12 is given as a tile, this
     *         function will return 2. If the tile was not an obstacle, it will
     *         return 0.
     */
    private int checkObstacles(int tile) {
        if (obstacles.containsKey(tile)) {
            return obstacles.get(tile);
        } else {
            return 0;
        }
    }

    /**
     * This function is fired when the player clicks a button to ascend a ladder or
     * descend a snake.
     */
    public void actionPerformed(ActionEvent e) {
        int nextTile = players[turn].getNextTile();
        toBeCycled = true;

        try {
            players[turn].advance(board, nextTile);
        } catch (Exception ex) { // Reset button has been clicked while a player was on a snake/ladder
            ((AbstractButton) e.getSource()).removeActionListener(this);
            return;
        }

        if (!doubleRoll) {
            cycleTurn();
        }

        ((AbstractButton) e.getSource()).removeActionListener(this); // Remove the action listener now.
    }

    public void insertBoard(BoardPiece[] board) {
        this.board = board;
    }

    /**
     * Insert the given players into the controllers players array.
     * 
     * @param player1 Player 1.
     * @param player2 Player 2.
     */
    public void insertPlayers(Player player1, Player player2) {
        players[0] = player1;
        players[1] = player2;
    }

    /**
     * Asks the players to configure themselves in the given Container
     * 
     * @param c The container to pass to the players
     */
    public void configPlayers(Container c) {
        players[0].config(c);
        players[1].config(c);
    }

    public void cycleTurn() {
        if (turn == 0) {
            turn = 1;
        } else {
            turn = 0;
        }
    }

    public int getTurn() {
        return turn;
    }

    public boolean getToBeCycled() {
        return toBeCycled;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * @return The Player object for the player who's current turn it is
     */
    public Player getPlayer() {
        return players[turn];
    }

    /**
     * Returns the player given, 0 = player1, 1 = player2
     * 
     * @param player the player to return
     * @return The Player object for the corresponding player specified
     */
    public Player getPlayer(int player) {
        return players[player];
    }

    /**
     * Resets local variables and asks the players to do the same, along with their
     * starting positions
     */
    public void reset() {
        turn = 0;
        toBeCycled = true;
        doubleRoll = false;
        players[0].reset(10, 525);
        players[1].reset(30, 525);
    }

    /**
     * Populates a HashMap with hard-coded obstacles. The key is the "from" tile,
     * and the value is the "to" tile.
     * 
     * If a snake goes from tile 12 -> 2 then that would be signified by
     * obst.put(12, 2). Whereas if a ladder goes from 3 -> 13 then that would be
     * singified by obst.put(3, 13);
     */
    private HashMap<Integer, Integer> populateObstacles() {
        HashMap<Integer, Integer> obst = new HashMap<Integer, Integer>();
        obst.put(6, 22);
        obst.put(12, 2);
        obst.put(13, 24);
        obst.put(18, 9);
        obst.put(27, 34);
        obst.put(32, 30);
        obst.put(35, 21);
        return obst;
    }

}
