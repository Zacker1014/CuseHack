import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {

    private Game() {
        this.setTitle("Game");
        this.setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

    public class Draw extends JPanel {

    }

    public static void main(String[] args) {
        new Game().setVisible(true);
    }

}
