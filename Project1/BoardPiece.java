package Project1;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Class representing a piece on the board, with its clickable button, tile
 * number and coordinates. get() functions are self-explantory.
 */
public class BoardPiece {
    private JButton button;
    private int tileNo;
    private int x;
    private int y;

    public BoardPiece(int tileNo, int x, int y) {
        this.tileNo = tileNo;
        this.x = x;
        this.y = y;
        this.button = new JButton();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Makes the button invisible, but it is still clickable
     */
    public void setInvisible() {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    /**
     * Adds the action listener on the button TODO: Implement logic on button press.
     */

    public void addActionListener() {
        button.addActionListener(start -> {
            System.out.println(Integer.toString(tileNo));
        });
    }

    /**
     * Configures the button
     */
    public void configButton(JFrame frame) {
        setInvisible();
        button.setBounds(x, y, 102, 102);
        addActionListener();
        frame.add(button);
    }
}
