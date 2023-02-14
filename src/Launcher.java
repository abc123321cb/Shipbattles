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

        Ship player1 = new Ship1((int) (Math.random()*GAME_WIDTH), (int) (Math.random()*GAME_HEIGHT),this,0);
        Ship player2 = new SampleScanner((int) (Math.random()*GAME_WIDTH), (int) (Math.random()*GAME_HEIGHT),this,1);

        while(Math.sqrt(Math.pow(player1.x - player2.x,2)+Math.pow(player1.y-player2.y, 2)) < 200){
            player2.x = (int)(Math.random()*GAME_WIDTH);
            player2.y = (int)(Math.random()*GAME_HEIGHT);
        }

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
        try {
            for (Bullets b : Bullets.bulletList) {
                b.draw(g);
            }
        }catch (Exception ignored){}

        // Draws the health/energy bars on the screen

        for(int i = 0; i<ShipList.length;i++){
            g.setColor(Color.black);
            g.drawString(ShipList[i].name,i*(GAME_WIDTH-100),20);
            g.setColor(Color.red);
            g.fillRect(i*(GAME_WIDTH-100), 40, 100*ShipList[i].getHealth()/Ship.MAXHEALTH, 20);
            g.setColor(Color.orange);
            g.fillRect(i*(GAME_WIDTH-100), 60, (int) (100*ShipList[i].getEnergy()/Ship.MAXENERGY), 20);
        }


    }

    public void move(){

    }

    public boolean pointcollide(double x, double y){
        for(Ship s: ShipList){
            if(x > s.x && x < s.x + Ship.dimen && y > s.y && y < s.y + Ship.dimen) return true;
        }
        for(Bullets b: Bullets.bulletList){
            if(x > b.x && x < b.x + b.dimen && y > b.y && y < b.y + b.dimen) return true;
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
