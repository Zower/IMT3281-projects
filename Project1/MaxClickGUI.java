package Project1;

import javax.swing.*;
import java.awt.*;

public class MaxClickGUI extends Game {

    private JButton clickMe;
    private int sec;
    private int delay;
    private JTextField timer;
    private Timer downCounter;

    public MaxClickGUI() {
        this.clickMe = new JButton("Click Me");
        this.sec = 60;
        this.delay = 1000;
        this.timer = new JTextField(5);

        this.downCounter = new Timer(delay, e -> {

            if (sec > 0 && sec <= 60) {
                sec--;
                clickMe.setEnabled(true);
                timer.setText("" + sec);
            } else {
                clickMe.setEnabled(false);

            }

        });

    }

    public void maxClickGui() {
        Game game = new Game();
        JFrame maxFrame = new JFrame();

        maxFrame.setVisible(true);
        this.clickMe.setEnabled(false);
        maxFrame.setTitle("Maximum Clicks");
        maxFrame.setResizable(false);
        maxFrame.setSize(500, 400);
        maxFrame.setVisible(true);
        maxFrame.getDefaultCloseOperation();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // JPanel
        JPanel panel = new JPanel();
        // Layout
        panel.setLayout(new GridBagLayout());
        // set xx a y position for each components

        JTextField counter = new JTextField(10);

        JLabel infoLabel = new JLabel("See how many clicks you can do in a minute!");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(infoLabel, gbc);
        ReadWriteToFile readWriteToFile = new ReadWriteToFile();

        JLabel recordLabel = new JLabel("Record: " + readWriteToFile.readRecord() + " clicks");

        JButton startBut = new JButton("Start");
        startBut.addActionListener(start -> {

            clickMe.setEnabled(false);
            downCounter.stop();
            sec = 60;
            game.setCounter(0);
            clickMe.setEnabled(true);
            timer.setText("" + sec);
            downCounter.start();

            clickMe.setEnabled(true);
        });

        clickMe.addActionListener(click -> {
            counter.setText("" + game.getCounter());

            if (game.getCounter() > readWriteToFile.readRecord())
                readWriteToFile.writeOutput(game.getCounter());
        });

        JLabel timeInfo = new JLabel("Time Left: ");

        JLabel seconds = new JLabel("seconds");

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(recordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;// Strech startButton
        panel.add(startBut, gbc);// add StartButton
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;

        panel.add(clickMe, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(counter, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(timeInfo, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(timer, gbc);
        gbc.gridx = 2;
        gbc.gridy = 4;

        panel.add(seconds, gbc);

        maxFrame.add(panel);

        maxFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // maxFrame.pack();
        maxFrame.setVisible(true);

    }
}
