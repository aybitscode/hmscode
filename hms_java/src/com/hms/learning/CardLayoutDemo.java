package com.hms.learning;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CardLayoutDemo {

    public static void main(String[] args) {

        Runnable r = new Runnable () {
            public void run() {
                final JRadioButton game = new JRadioButton("Game", true);
                JRadioButton highScores = new JRadioButton("High Scores");

                ButtonGroup bg = new ButtonGroup();
                bg.add( game );
                bg.add( highScores );

                JPanel buttons = new JPanel(new 
                    FlowLayout(FlowLayout.CENTER, 5, 5));
                buttons.add( game );
                buttons.add( highScores );

                JPanel gui = new JPanel(new BorderLayout(5,5));
                gui.add(buttons, BorderLayout.SOUTH);

                final CardLayout cl = new CardLayout();
                final JPanel cards = new JPanel(cl);
                gui.add(cards);
                cards.add(new JLabel("Level 1"), "game");
                cards.add(new JLabel("High Scores"), "scores");

                ActionListener al = new ActionListener(){
                    public void actionPerformed(ActionEvent ae) {
                        if (game.isSelected()) {
                            cl.show(cards, "game");
                        } else {
                            cl.show(cards, "scores");
                        }
                    }
                };
                game.addActionListener(al);
                highScores.addActionListener(al);

                JOptionPane.showMessageDialog(null, gui);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}