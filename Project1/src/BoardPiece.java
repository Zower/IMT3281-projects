package Project1.src;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Class representing a piece on the board, with its clickable button, tile
 * number and coordinates. get() functions are self-explantory.
 */
public class BoardPiece extends GUIElement {
    private JButton button;

    public BoardPiece(int tileNo, int x, int y) {
        super(tileNo, x, y);
        this.button = new JButton();
        setInvisible();
        addActionListener();
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
            System.out.println(Integer.toString(getTile()));
        });
    }

    /**
     * Configures the button
     */
    public void config(JFrame frame) {
        button.setBounds(getX(), getY(), 102, 102);
        frame.add(button);
    }
}
