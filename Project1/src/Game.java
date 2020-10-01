/**
 * This code is part of a previous assignment, and is only here due to the requirement for the Snakes and Ladders application to be launched from the Game Center.
 * As such I don't consider it part of "Project 1", nor do I hold it to the same standards as the files related to snakesAndLadders.
 */

package Project1.src;

import javax.swing.*;
import java.awt.*;

public class Game {

    private int count;

    public Game() {
        this.count = 0;
    }

    /**
     * @return returns count
     */
    public int getCounter() {
        this.count++;
        return this.count;
    }

    /**
     * @param count
     */
    public void setCounter(int count) {
        this.count = count;

    }

    public static class RollDiceGUI {

        private JButton player1;
        private JButton player2;
        private int counter = 6;
        private int score1 = 0;
        private int score2 = 0;

        public RollDiceGUI() {
            this.player1 = new JButton("Player 1");
            this.player2 = new JButton("Player 2");

        }

        public int rollDice() {
            return (int) (Math.random() * 6 + 1);
        }

        public void RollDiceInitGUI() {
            JFrame maxFrame = new JFrame();

            maxFrame.setVisible(true);
            this.player1.setEnabled(false);
            this.player2.setEnabled(false);
            maxFrame.setTitle("Roll a Dice");
            maxFrame.setResizable(false);
            maxFrame.setSize(500, 400);
            maxFrame.setVisible(true);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);

            // JPanel
            JPanel panel = new JPanel();
            // Layout
            panel.setLayout(new GridBagLayout());
            // set xx a y position for each components

            JLabel infoLabel = new JLabel("Each player rolls three times.");
            JLabel infoLabel2 = new JLabel("Maximum score wins!");
            gbc.gridx = 1;
            gbc.gridy = 0;
            panel.add(infoLabel, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            panel.add(infoLabel2, gbc);

            JLabel player1scorelabel = new JLabel("Player 1 score");
            JLabel player2scorelabel = new JLabel("Player 2 score");
            JLabel winner = new JLabel("This shouldnt be here");
            winner.setVisible(false);

            JTextField player1score = new JTextField(Integer.toString(score1));
            JTextField player2score = new JTextField(Integer.toString(score2));

            JLabel diceValue = new JLabel("Dice value");

            player1.setEnabled(true);

            player1.addActionListener(player1click -> {
                int value = rollDice();
                diceValue.setText(Integer.toString(value));
                score1 += value;
                player1score.setText(Integer.toString(score1));
                player2.setEnabled(true);
                player1.setEnabled(false);
                counter--;
                player2.setEnabled(true);
                player1.setEnabled(false);

            });

            player2.addActionListener(player2click -> {
                int value = rollDice();
                diceValue.setText(Integer.toString(value));
                score2 += value;
                player2score.setText(Integer.toString(score2));
                counter--;
                if (counter == 0) {
                    player1.setEnabled(false);
                    player2.setEnabled(false);
                    if (score1 == score2) {
                        winner.setText("It's a tie!");
                    } else if (score1 > score2) {
                        winner.setText("Player 1 wins!");
                    } else {
                        winner.setText("Player 2 wins!");
                    }
                    winner.setVisible(true);
                } else {
                    player1.setEnabled(true);
                    player2.setEnabled(false);
                }

            });

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(player1, gbc);// add StartButton

            gbc.gridx = 2;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(player2, gbc);

            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.fill = GridBagConstraints.NONE;
            panel.add(diceValue, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            panel.add(player1scorelabel, gbc);

            gbc.gridx = 2;
            gbc.gridy = 4;
            panel.add(player2scorelabel, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            panel.add(player1score, gbc);

            gbc.gridx = 2;
            gbc.gridy = 5;
            panel.add(player2score, gbc);

            gbc.gridx = 1;
            gbc.gridy = 6;
            panel.add(winner);

            maxFrame.add(panel);

            maxFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            maxFrame.setVisible(true);

        }
    }
}
