package Project1.src;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.event.*;
import java.awt.Container;

import javax.imageio.ImageIO;

import java.io.File;

@SuppressWarnings("serial")
public class snakesLaddersGUI extends JFrame implements ActionListener {

    private Container c;
    private JButton rollDice;
    private JTextField diceResult1;
    private JTextField diceResult2;
    private JLabel infoText;
    private GameController controller;
    private int suspectedTurn;

    public snakesLaddersGUI() {
        super("Snakes and Ladders");
        this.suspectedTurn = 0;
    }

    /**
     * This function creates the JFrame, adds all the pieces and draws the frame. It
     * is the backbone of the GUI.
     */

    public void initGUI() {
        setLayout(null);
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        c = getContentPane();

        // Create all the buttons
        BoardPiece[] board = populateBoard();

        gameLogic(board, c);
        configGUIButtons(c);

        /**
         * Draws the playing board based on the image 'board.png'
         */
        try {
            BufferedImage myPicture = ImageIO.read(new File("Project1\\assets\\board.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(0, 0, 600, 600);
            add(picLabel);
        } catch (Exception e) {
            System.out.println(e);
        }

        setVisible(true);

    }

    private void gameLogic(BoardPiece[] board, Container c) {

        // Configure the buttons
        for (int i = 0; i < board.length; i++) {
            BoardPiece currTile = board[i];
            currTile.config(c);
        }

        // Create the players
        controller = new GameController();
        controller.insertBoard(board);
        try {
            controller.insertPlayers(new Player(1, 10, 525, ImageIO.read(new File("Project1\\assets\\player1.png"))),
                    new Player(1, 30, 525, ImageIO.read(new File("Project1\\assets\\player2.png"))));

        } catch (Exception e) {
            System.out.println(e);
            System.exit(-1);
        }

        controller.configPlayers(c);

    }

    /**
     * Progression of the game, happens on rollDice mouseDown.
     * 
     */
    public void actionPerformed(ActionEvent e) {
        if (suspectedTurn == controller.getTurn()) {

            int[] diceResults = rollDie();

            diceResult1.setText(Integer.toString(diceResults[0]));
            diceResult2.setText(Integer.toString(diceResults[1]));

            if (controller.advancePos(c, diceResults[0] + diceResults[1])) {
                int visTurn = controller.getTurn() + 1;
                infoText.setText("Player " + visTurn + " won!");
                rollDice.setEnabled(false);
            } else {
                int visTurn = controller.getTurn() + 1;
                infoText.setText("It's player " + visTurn + "' turn");
                cycleSuspectedTurn();
            }

        } else {
            infoText.setText("Click your player icon!");
        }
    }

    private void configGUIButtons(Container c) {
        rollDice = new JButton("Roll the dice!");
        diceResult1 = new JTextField("0");
        diceResult2 = new JTextField("0");
        infoText = new JLabel("It's player 1' turn");

        Font diceFont = new Font(infoText.getFont().getName(), Font.PLAIN, 22);

        rollDice.setBounds(683, 225, 120, 25);
        rollDice.addActionListener(this);

        diceResult1.setBounds(683, 270, 25, 25);
        diceResult1.setFont(diceFont);
        diceResult2.setBounds(783, 270, 25, 25);
        diceResult2.setFont(diceFont);

        infoText.setBounds(653, 150, 300, 50);
        infoText.setFont(new Font(infoText.getFont().getName(), Font.PLAIN, 30));

        c.add(rollDice);
        c.add(diceResult1);
        c.add(diceResult2);
        c.add(infoText);
    };

    /**
     * This function populates the play field with a set of hardcoded 36 buttons, to
     * be used by the players to click on.
     * 
     * @return An array of BoardPieces, where each boardPiece' tileNo is
     *         representative of the in-game tile number, which "snakes" back and
     *         forth. This gives the function it's need for the weird populate
     *         logic.
     */

    private BoardPiece[] populateBoard() {
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
    private int operate(int tileSize, boolean reverse) {
        if (reverse) {
            return -tileSize;
        }
        return tileSize;
    }

    /**
     * Roll two die.
     * 
     * @return int array containing the dice rolls.
     */
    public int[] rollDie() {
        int[] die = { (int) (Math.random() * 6 + 1), (int) (Math.random() * 6 + 1) };
        System.out.println("Die 1: " + Integer.toString(die[0]) + "\nDie 2: " + Integer.toString(die[1]));
        return die;
    }

    public void cycleSuspectedTurn() {
        if (suspectedTurn == 0) {
            suspectedTurn = 1;
        } else {
            suspectedTurn = 0;
        }
    }
}
