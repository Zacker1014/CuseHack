import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Game extends JFrame {

    private SecureRandom random = new SecureRandom();

    private final int BLOCK_WIDTH = 40;
    private final int BLOCK_HIGHT = 30;

    private final Timer timer;
    private final Color skyColor = new Color(224,240,249);

    Game() {
        this.setTitle("Game");
        this.setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);
        Draw draw = new Draw();
        this.add(draw);
        this.timer = new Timer(3000, new Time());
        timer.start();
    }

    public class Time implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }

    public class Draw extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            g.setColor(Color.BLUE);
            getContentPane().setBackground(skyColor);
            for (int i = 0; i < 5; i++) {
                int randomNumber = random.nextInt(2);
                int x, y;
                switch (randomNumber) {
                    case 0:
                        x = random.nextInt(750) + 30;
                        y = random.nextInt(500);
                        g.fillRoundRect(x, y, BLOCK_WIDTH, BLOCK_HIGHT, 10, 10);
                        break;
                    case 1:
                        x = random.nextInt(750);
                        y = random.nextInt(500) + 30;
                        g.fillRoundRect(x, y, BLOCK_WIDTH, BLOCK_HIGHT, 10, 10);
                        break;
                }
            }

        } // end paintComponent method

    } // ends Draw class

}
