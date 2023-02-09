import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    private Canvas canvas;

    public static void main(String[] args) {
        Main main = new Main();
    }

    Main() {
        Launcher panel = new Launcher();
        this.add(panel);
        this.setTitle("Ship");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}