package Project1.src;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;

import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class representing the main game center where one can start all the games.
 */
@SuppressWarnings("serial")
public class MainGUI extends JPanel implements ActionListener {

    private final JFrame jFrame;
    private JButton rollDice;
    private JButton maxClicks;
    private JButton snakesLaddersButton;

    private final JButton exit;
    private final Box mainBox;

    public MainGUI() {
        this.mainBox = Box.createVerticalBox();
        this.jFrame = new JFrame("Game center");

        // Used this way so that all buttons e.g. 'Roll a Dice' and 'Maximum clicks'
        // could have the same size

        this.rollDice = new JButton("Roll a Dice") {
            {
                setSize(150, 75);
                setMaximumSize(getSize());
            }
        };

        this.maxClicks = new JButton("Maximum Clicks") {
            {
                setSize(150, 75);
                setMaximumSize(getSize());
            }

        };

        this.snakesLaddersButton = new JButton("Snakes and ladders") {
            {
                setSize(150, 75);
                setMaximumSize(getSize());
            }
        };

        this.exit = new JButton("Exit") {
            {
                setSize(150, 75);
                setMaximumSize(getSize());
            }

        };

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

    }

    /**
     * Initiates the main page.
     **/
    public void MainPage() {

        jFrame.pack();
        jFrame.setSize(400, 400);
        jFrame.getContentPane().setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));

        configButtons();

        mainBox.setBorder(BorderFactory.createTitledBorder(" Games"));
        mainBox.setAlignmentX(CENTER_ALIGNMENT);

        jFrame.add(mainBox);
        jFrame.setVisible(true);
    }

    /**
     * Configures the buttons correctly.
     */
    private void configButtons() {
        Game.RollDiceGUI rollGUI = new Game.RollDiceGUI();
        rollDice.addActionListener(dice -> rollGUI.RollDiceInitGUI());
        mainBox.add(rollDice);

        createButtonSpace(20, 100);

        MaxClickGUI maxGUI = new MaxClickGUI();
        maxClicks.addActionListener(max -> maxGUI.maxClickGui());
        mainBox.add(maxClicks);

        createButtonSpace(20, 100);

        snakesLaddersGUI snakesLadders = new snakesLaddersGUI();
        snakesLaddersButton.addActionListener(SL -> snakesLadders.initGUI());
        mainBox.add(snakesLaddersButton);

        createButtonSpace(20, 100);

        exit.addActionListener(this);
        mainBox.add(exit);
    }

    /**
     * Creates some visual space.
     * 
     * @param vertical   The vertical space to create.
     * @param horizontal The horizontal space to create.
     */
    private void createButtonSpace(int vertical, int horizontal) {
        mainBox.add(Box.createVerticalStrut(vertical));
        mainBox.add(Box.createHorizontalStrut(horizontal));
    }

    /**
     * Exit button has been clicked, close the program.
     */
    public void actionPerformed(ActionEvent e) {
        jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
    }

}
