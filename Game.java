import java.security.SecureRandom;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Game extends JFrame {

    private SecureRandom random = new SecureRandom();

    private final int BLOCK_WIDTH = 40;
    private final int BLOCK_HEIGHT = 30;

    private int x1Position, y1Position;
    private int x2Position, y2Position;
    private int x3Position, y3Position;
    private int x1Speed, y1Speed;
    private int x2Speed, y2Speed;
    private int x3Speed, y3Speed;
    private int SPEED = 1;

    private final Color skyColor = new Color(224, 240, 249);

    Game() {
        this.getContentPane().setBackground(skyColor);
        this.setTitle("Game");
        this.setResizable(false);
        this.setSize(900, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        switch (random.nextInt(3)) {
            case 0:
                x1Speed = y2Speed = x3Speed = 1;
                x2Speed = y1Speed = y3Speed = -1;
                break;
            case 1:
                x1Speed = y1Speed = x2Speed = 1;
                y2Speed = y3Speed = x3Speed = -1;
                break;
            case 2:
                x2Speed = y3Speed = x2Speed = 1;
                x1Speed = y1Speed = y2Speed = -1;
                break;
        }

        x1Position = random.nextInt(750) + 150;
        x2Position = random.nextInt(750) + 150;
        x3Position = random.nextInt(750) + 150;

        y1Position = random.nextInt(450) + 150;
        y2Position = random.nextInt(450) + 150;
        y3Position = random.nextInt(450) + 150;


        Draw draw = new Draw();
        draw.setBackground(skyColor);
        this.add(draw);

        Timer timer = new Timer(10, new Time());
        timer.start();
    }

    public class Time implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int width = getWidth() - 150;
            int height = getHeight() - 150;
            x1Position += x1Speed;
            y1Position += y1Speed;

            x2Position += x2Speed;
            y2Position += y2Speed;

            x3Position += x3Speed;
            y3Position += y3Speed;

            if (x1Position < 100) {
                x1Position = 100;
                x1Speed = SPEED;
            } else if (x1Position > width - BLOCK_WIDTH) {
                x1Position = width - BLOCK_WIDTH;
                x1Speed = -SPEED;
            }
            if (y1Position < 100) {
                y1Position = 100;
                y1Speed = SPEED;
            } else if (y1Position > height - BLOCK_HEIGHT) {
                y1Position = height - BLOCK_HEIGHT;
                y1Speed = -SPEED;
            }

            if (x2Position < 100) {
                x2Position = 100;
                x2Speed = SPEED;
            } else if (x2Position > width - BLOCK_WIDTH) {
                x2Position = width - BLOCK_WIDTH;
                x2Speed = -SPEED;
            }
            if (y2Position < 100) {
                y2Position = 100;
                y2Speed = SPEED;
            } else if (y2Position > height - BLOCK_HEIGHT) {
                y2Position = height - BLOCK_HEIGHT;
                y2Speed = -SPEED;
            }

            if (x3Position < 100) {
                x3Position = 100;
                x3Speed = SPEED;
            } else if (x3Position > width - BLOCK_WIDTH) {
                x3Position = width - BLOCK_WIDTH;
                x3Speed = -SPEED;
            }
            if (y3Position < 100) {
                y3Position = 100;
                y3Speed = SPEED;
            } else if (y3Position > height - BLOCK_HEIGHT) {
                y3Position = height - BLOCK_HEIGHT;
                y3Speed = -SPEED;
            }
            repaint();
        }
    }

    public class Draw extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            g.setColor(Color.BLUE);
            g.fillRoundRect(x1Position, y1Position, BLOCK_WIDTH, BLOCK_HEIGHT, 15, 15);

            g.setColor(Color.YELLOW);
            g.fillRoundRect(x2Position, y2Position, BLOCK_WIDTH, BLOCK_HEIGHT, 15, 15);

            g.setColor(Color.LIGHT_GRAY);
            g.fillRoundRect(x3Position, y3Position, BLOCK_WIDTH, BLOCK_HEIGHT, 15, 15);
        }
    }

}
