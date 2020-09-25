package Project1.src;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.*;

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
    }

    /**
     * Makes the button invisible, but it is still clickable
     */
    private void setInvisible() {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
    }

    public void addActionListener(ActionListener l) {
        button.addActionListener(l);
    }

    public void removeActionListener(ActionListener l) {
        button.removeActionListener(l);
    }

    /**
     * Configures the button
     */
    public void config(JFrame frame) {
        button.setBounds(getX(), getY(), 102, 102);
        frame.add(button);
    }
}
