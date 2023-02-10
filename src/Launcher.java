import javax.swing.*;
import java.awt.*;

public class Launcher extends JPanel implements Runnable {
    public static int GAME_WIDTH = 1000;
    public static int GAME_HEIGHT = 500;

    public final static double step = 2;

    static Ship[] ShipList;

    Image image;
    Thread gameThread;
    Graphics graphics;

    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    Launcher(){
        this.setFocusable(true);
        //this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);

        Ship player1 = new Ship1(10,10,this,0);
        Ship player2 = new Ship1(100,100,this,1);
        ShipList = new Ship[]{player1, player2};

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
        for (Ship ship: ShipList) {
            ship.draw(g);
        }
        for(Bullets b: Bullets.bulletList){
            b.draw(g);
        }


    }

    public void move(){

    }

    public boolean pointcollide(double x, double y){
        for(Ship s: ShipList){
            if(x > s.x && x < s.x + Ship.dimen && y > s.y && y < s.y + Ship.dimen) return true;
        }
        return (x<0|| x>GAME_WIDTH || y<0 || y > GAME_HEIGHT);
    }

    public static boolean collide(double x1, double y1, double width1, double height1, double x2, double y2, double width2, double height2){
        return (x1 < x2+width2 && x2 < x1+width1 && y1 < y2 + height2 && y2 < y1 +height1);
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
