import javax.swing.*;
import java.awt.*;

public class Launcher extends JPanel implements Runnable {
    public static int GAME_WIDTH = 1000;
    public static int GAME_HEIGHT = 500;

    public final static double step = 2;

    Ship[] ShipList;

    Image image;
    Thread gameThread;
    Graphics graphics;

    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    Launcher(){
        this.setFocusable(true);
        //this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();

    }

    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0 , GAME_WIDTH, GAME_HEIGHT);



    }

    public void move(){

    }

    public boolean pointcollide(int x, int y){
        for(Ship s: ShipList){

        }
        return (x<0|| x>GAME_WIDTH || y<0 || y > GAME_HEIGHT);
    }


    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountofticks = 60.0;
        double ns = 1000000000/amountofticks;
        double delta = 0;
        while(true){
            long  now = System.nanoTime();
            delta+= (now-lastTime)/ns;
            lastTime = now;
            if (delta>=1){
                repaint();
                move();
                delta--;
            }
        }
    }
}
