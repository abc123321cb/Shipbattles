import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ship {
    private int health = 10;
    private int x;
    private int y;
    private static final int MAXENERGY = 100;
    private int energy = MAXENERGY;
    private static final int dimen = 20;
    private static final BufferedImage img;

    static {
        try {
            img = ImageIO.read(new File("res/Ship.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Ship(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getEnergy() {
        return energy;
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, dimen, dimen, null);

    }

    public void move(){

    }

    public int scan(double theta){
        return 0;
    }

}

