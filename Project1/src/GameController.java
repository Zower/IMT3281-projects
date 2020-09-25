package Project1.src;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.AbstractButton;

import java.awt.event.*;

public class GameController implements ActionListener {
    private int turn;
    final private int TILENO = 36;
    private Player[] players = new Player[2];
    private BoardPiece[] board = new BoardPiece[36];
    private HashMap<Integer, Integer> obstacles = new HashMap<Integer, Integer>();
    {

    };

    GameController() {
        this.turn = 0;
        this.obstacles = populateObstacles();
    }

    GameController(int turn, Player player1, Player player2, BoardPiece[] board) {
        this.turn = turn;
        this.players[0] = player1;
        this.players[1] = player2;
        this.board = board;
    }

    /**
     * Advances (graphically and otherwise) the current players position. TODO:
     * handle winning, handle going beyond the board.
     * 
     * @param advanceNo The amount of tiles to move.
     */
    public void advancePos(int advanceNo) {
        int tempTile = players[turn].getTile() + advanceNo;
        int obstTile = checkObstacles(tempTile);
        int[] tilePos = getTilePos(tempTile - 1);

        players[turn].advance(tilePos, tempTile);
        if (obstTile == 0) {
            cycleTurn();
        } else {
            players[turn].setNextTile(obstTile);
            board[tempTile - 1].addActionListener(this);
        }
        // if (tempTile == TILENO) {
        // // won = true;
        // }

    }

    private int checkObstacles(int tile) {
        if (obstacles.containsKey(tile)) {
            return obstacles.get(tile);
        } else {
            return 0;
        }
    }

    public void actionPerformed(ActionEvent e) {
        int nextTile = players[turn].getNextTile();
        int[] tilePos = getTilePos(nextTile - 1);
        players[turn].advance(tilePos, nextTile);
        cycleTurn();
        ((AbstractButton) e.getSource()).removeActionListener(this);
    }

    public void insertBoard(BoardPiece[] board) {
        this.board = board;
    }

    public void insertPlayers(Player player1, Player player2) {
        players[0] = player1;
        players[1] = player2;
    }

    public void configPlayers(JFrame frame) {
        players[0].config(frame);
        players[1].config(frame);
    }

    public void cycleTurn() {
        if (turn == 0) {
            turn = 1;
        } else {
            turn = 0;
        }
    }

    public int[] getTilePos(int tile) {
        int[] coords = { board[tile].getX() + 22, board[tile].getY() + 20 };
        return coords;
    }

    /**
     * Sets the players position on the playing field. advancePos() should be used
     * to control game logic. Does not check for out of bounds positions.
     * 
     * @param player The player to move.
     * @param x      x coordinate.
     * @param y      y coordinate.
     */
    public void setPlayerPos(int player, int x, int y) {
        players[player].setLocation(x, y);
    }

    public int getTurn() {
        return turn;
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
