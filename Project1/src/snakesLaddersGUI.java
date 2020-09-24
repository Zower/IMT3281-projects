package Project1.src;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.File;

@SuppressWarnings("serial")
public class snakesLaddersGUI extends JPanel {

    public snakesLaddersGUI() {
        System.out.println("Created class snakesLaddersGUI");
    }

    /**
     * This function creates the JFrame, adds all the pieces and draws the frame. It
     * is the backbone of the GUI.
     */
    public void initGUI() {
        JFrame frame = new JFrame("Snakes and Ladders");
        frame.setLayout(null);
        frame.setSize(1000, 650);

        // Create all the buttons
        BoardPiece[] board = populateBoard();

        gameLogic(board, frame);

        /**
         * Draws the playing board based on the image 'board.png'
         */
        try {
            BufferedImage myPicture = ImageIO.read(new File("Project1\\assets\\board.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(0, 0, 600, 600);
            frame.add(picLabel);
        } catch (Exception e) {
            System.out.println(e);
        }

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // TODO: Implement game logic.
    public void gameLogic(BoardPiece[] board, JFrame frame) {

        // Configure the buttons
        for (int i = 0; i < board.length; i++) {
            BoardPiece currTile = board[i];
            currTile.config(frame);
        }

        // Create the players
        GameController controller = new GameController();
        controller.insertBoard(board);
        try {
            controller.insertPlayers(new Player(1, 10, 525, ImageIO.read(new File("Project1\\assets\\player1.png"))),
                    new Player(1, 30, 525, ImageIO.read(new File("Project1\\assets\\player2.png"))));

        } catch (Exception e) {
            System.out.println(e);
            System.exit(-1);
        }

        controller.configPlayers(frame);
        controller.getPlayer(0).print();
        controller.getPlayer(1).print();
        controller.advancePos(0, 4);
    }

    /**
     * This function populates the play field with a set of hardcoded 36 buttons, to
     * be used by the players to click on.
     * 
     * @return An array of BoardPieces, where each boardPiece' tileNo is
     *         representative of the in-game tile number, which "snakes" back and
     *         forth. This gives the function it's need for the weird populate
     *         logic.
     */

    public BoardPiece[] populateBoard() {
        int x = -100;
        int y = 500;
        boolean reverse = false; // Populate from right-to-left or vice versa.

        BoardPiece[] board = new BoardPiece[36];

        for (int i = 1; i <= board.length; i++) {
            board[i - 1] = new BoardPiece(i, x += operate(100, reverse), y);
            if (i % 12 == 0) { // Left side of the board
                y -= 100;
                x = -100;
                reverse = false;
            } else if (i % 6 == 0) { // Right side of the board
                x = 600;
                y -= 100;
                reverse = true;
            }
        }

        return board;

    }

    /**
     * Function that allows the program to reduce or increase by tile size based on
     * a boolean.
     * 
     * @param reverse  The deciding factor for whether to reduce or increase by tile
     *                 size.
     * @param tileSize The size to increase or decrease by.
     * @return the given tileSize, either negative or positive based on @reverse
     */
    public int operate(int tileSize, boolean reverse) {
        if (reverse) {
            return -tileSize;
        }
        return tileSize;
    }
}
