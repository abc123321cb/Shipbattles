import java.awt.*;
import java.util.ArrayList;

public class Bullets {
    int x;
    int y;
    int team;
    int dimen = 5;
    final static int VELOCITY = 10;
    static ArrayList<Bullets> bulletList =  new ArrayList<>();
    int xvel;
    int yvel;

    Bullets(int x, int y, double theta, int team){
        this.x = x;
        this.y = y;
        this.team = team;
        xvel = (int) (Math.cos(Math.toRadians(theta)) * VELOCITY);
        yvel = (int) (Math.sin(Math.toRadians(theta)) * VELOCITY);
        bulletList.add(this);
    }

    public void draw(Graphics g){
        x+=xvel;
        y+=yvel;
        boolean collided = x > Launcher.GAME_WIDTH || x < 0 || y > Launcher.GAME_HEIGHT || y < 0;
        for(Ship s: Launcher.ShipList){
            boolean c = s.team != team && Launcher.collide(x, y, dimen, dimen, s.x, s.y, Ship.dimen, Ship.dimen);
            collided = collided || c;
            if(c){
                s.damage(1);
            }
        }
        g.setColor(Color.blue);
        g.drawRect(x,y,dimen,dimen);
    }





}
