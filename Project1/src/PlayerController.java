package Project1.src;

import javax.swing.JFrame;

public class PlayerController {
    private int turn;
    private Player[] players = new Player[2];

    PlayerController() {
        this.turn = 0;
    }

    PlayerController(Player player1, Player player2) {
        this.turn = 0; // 0 = player 1, 1 = player2
        this.players[0] = player1;
        this.players[1] = player2;
    }

    public void configPlayers(JFrame frame) {
        this.players[0].config(frame);
        this.players[1].config(frame);
    }

    /**
     * Sets the bounds for the specified player.
     * 
     * @param player The player to operate on. 0 = player1, 1 = player2.
     * @param x      x coordinate.
     * @param y      y coordinate.
     */
    public void setPos(int player, int x, int y) {
        players[player].setLocation(x, y);
    }

    public int getTurn() {
        return turn;
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

}
