package Project1.src;

import javax.swing.JButton;
import java.awt.event.*;
import java.awt.Container;

/**
 * Class representing a piece on the board, with its clickable button, tile
 * number and coordinates. get() functions are self-explantory.
 */
public class BoardPiece extends GUIElement {
    private JButton button;

    public BoardPiece() {
        super();
        this.button = new JButton();
        setInvisible();
    }

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

    public JButton getButton() {
        return button;
    }

    /**
     * Configures the button
     */
    public void config(Container c) {
        button.setBounds(getX(), getY(), 102, 102);
        c.add(button);
    }
}
