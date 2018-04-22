import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Intro extends JFrame {

    private final JButton play, quit;
    private final JPanel buttonPanel;

    private final Color backgroundColor = new Color(183, 251, 162);

    private Intro() {
        this.setTitle("Introduction");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(backgroundColor);

        // Button
        play = new JButton("play");
        quit = new JButton("quit");
        play.addActionListener(new ButtonListener());
        quit.addActionListener(new ButtonListener());
        buttonPanel.add(play);
        buttonPanel.add(quit);

        this.add(buttonPanel, BorderLayout.SOUTH);
        this.getContentPane().setBackground(backgroundColor);
    }

    protected class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == play) {
                new Game().setVisible(true);
                setVisible(false);
            }
            if (e.getSource() == quit) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to " +
                        "quit the game?", "Confirmation", dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    setVisible(false);
                    System.exit(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Intro().setVisible(true);
    }
}
