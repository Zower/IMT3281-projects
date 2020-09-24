package Project1.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class MainGUI extends JPanel {

    private final JFrame jFrame;
    private JButton rollDice;
    private JButton maxClicks;
    private JButton snakesLaddersButton;

    private final JButton exit;
    private final Box mainBox;

    public MainGUI() {
        this.mainBox = Box.createVerticalBox();
        this.jFrame = new JFrame("Game center");

        // Used this way so that both buttons i,e 'Roll a Dice' and 'Maximum clicks'
        // could have the same size

        this.rollDice = new JButton("Roll a Dice") {
            {
                setSize(150, 75);
                setMaximumSize(getSize());
            }
        };
        // RollDiceGUI rDGUI = new RollDiceGUI();
        // rollDice.addActionListener(roll -> {
        // rDGUI.rollDice();
        // });
        // Used this way so that both buttons could have the same size

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

        this.exit = new JButton("Exit");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

    }

    /**
     * initiates the gui
     **/
    public void MainPage() {

        jFrame.pack();

        createButtonSpace(40, 0);

        jFrame.setSize(400, 400);
        jFrame.setVisible(true);
        jFrame.getContentPane().setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));

        mainBox.add(rollDice);
        Game.RollDiceGUI rollGUI = new Game.RollDiceGUI();
        rollDice.addActionListener(dice -> rollGUI.RollDiceInitGUI());

        createButtonSpace(20, 100);

        mainBox.add(maxClicks);
        MaxClickGUI maxGUI = new MaxClickGUI();
        maxClicks.addActionListener(max -> {
            maxGUI.maxClickGui();

        });

        createButtonSpace(20, 100);

        mainBox.add(snakesLaddersButton);
        snakesLaddersGUI snakesLadders = new snakesLaddersGUI();
        snakesLaddersButton.addActionListener(SL -> {
            snakesLadders.initGUI();
        });

        createButtonSpace(50, 20);

        jFrame.getContentPane().add(mainBox);
        mainBox.setAlignmentX(CENTER_ALIGNMENT);

        mainBox.setBorder(BorderFactory.createTitledBorder(" Games"));

        // closes the jFrame when ever the the exit button clickMe
        exit.addActionListener(exiting -> jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING)));
        JPanel extPanel = new JPanel();
        extPanel.add(exit, BorderLayout.PAGE_START);
        jFrame.add(extPanel);

    }

    public void createButtonSpace(int vertical, int horizontal) {
        mainBox.add(Box.createVerticalStrut(vertical));
        mainBox.add(Box.createHorizontalStrut(horizontal));
    }

}
