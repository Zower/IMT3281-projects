package Project1.src;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

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
    private JButton exitButton;
    private JButton reset;
    private JTextField diceResult1, diceResult2;
    private JTextArea infoText;
    private JTextArea introText;
    private GameController controller;

    public snakesLaddersGUI() {
        super("Snakes and Ladders");
    }

    /**
     * This function creates the JFrame, adds all the pieces and draws the frame. It
     * is the backbone of the GUI.
     */

    public void initGUI() {
        setLayout(null);
        setSize(940, 650);
        setResizable(false);
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
        if (controller.getToBeCycled()) {

            int[] diceResults = rollDie();

            diceResult1.setText(Integer.toString(diceResults[0]));
            diceResult2.setText(Integer.toString(diceResults[1]));

            boolean doubleRoll = (diceResults[0] == diceResults[1]);

            if (controller.advancePos(diceResults[0] + diceResults[1], doubleRoll)) { // Game won
                int visTurn = controller.getTurn() + 1;
                infoText.setText("Player " + visTurn + " won!");
                rollDice.setEnabled(false);
            } else { // Regular turn
                int visTurn = controller.getTurn() + 1;
                if (doubleRoll) {
                    infoText.setText(" Double dice! \n Roll again.");
                } else {
                    infoText.setText(" It's player " + visTurn + "' turn");
                }
            }

        } else {
            infoText.setText("Click your icon!");
        }
    }

    private void configGUIButtons(Container c) {
        rollDice = new JButton("Roll the dice!");
        diceResult1 = new JTextField("0");
        diceResult2 = new JTextField("0");
        infoText = new JTextArea(" It's player 1' turn");
        introText = new JTextArea(
                "The players take turns rolling the dice. The \n corresponding player piece will move however much \n you roll. If you roll two equal die, you get another turn. \n If you land on a snake or ladder, you will need to click \n your icon to progress before the turn is handed  over. \n If you roll beyond the winning tile, you will remain \n where you were. You have to roll perfect to land on the \n winning tile.");

        exitButton = new JButton("Exit");
        reset = new JButton("New Game/Reset");

        Font diceFont = new Font(infoText.getFont().getName(), Font.PLAIN, 22);

        rollDice.setBounds(693, 270, 120, 25);
        rollDice.addActionListener(this);
        exitButton.setBounds(693, 575, 120, 25);
        exitButton.addActionListener(exiting -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        reset.setBounds(678, 375, 150, 25);
        reset.addActionListener(reset -> reset());

        diceResult1.setBounds(693, 315, 25, 25);
        diceResult1.setFont(diceFont);
        diceResult2.setBounds(793, 315, 25, 25);
        diceResult2.setFont(diceFont);

        infoText.setBounds(640, 165, 237, 90);
        infoText.setFont(new Font(infoText.getFont().getName(), Font.PLAIN, 30));
        introText.setBounds(620, 15, 300, 130);

        c.add(rollDice);
        c.add(diceResult1);
        c.add(diceResult2);
        c.add(infoText);
        c.add(introText);
        c.add(exitButton);
        c.add(reset);
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
        return die;
    }

    public void reset() {
        rollDice.setEnabled(true);
        infoText.setText(" It's player 1' turn");
        controller.reset();
    }

}
