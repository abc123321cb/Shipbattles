import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Ship {
    public static final int MAXHEALTH = 5;
    private int health = MAXHEALTH;
    public int x;
    public int y;
    public double xvel;
    public double yvel;
    public int team;
    String name;

    public static final double THRUSTPOWER = 1;
    public static final int MAXENERGY = 100;
    private double energy = MAXENERGY;
    public final double energyRegen = 0.5;
    public static final int dimen = 20;
    private static final BufferedImage img;
    private Launcher launcher;
    public static final Map<String, Integer> costs = Map.of(
            "scan", 2,
            "fire", 30,
            "move", 5
            );

    static {
        try {
            img = ImageIO.read(new File("res/Ship.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Ship(int x, int y, Launcher launcher, int team){
        this.x = x;
        this.y = y;
        this.team = team;
        this.launcher = launcher;
        name = "Default";
    }

    public double getEnergy() {
        return energy;
    }
    public int getHealth(){return health;}
    public void damage(int x){
        health-=x;
        System.out.println("This ship on team " + team + " has " + health + " hull left");
    }
    public void draw(Graphics g) {
        move();
        g.drawImage(img, x, y, dimen, dimen, null);

    }

    public void move(){
        // updates energy
        energy+=(energy > MAXENERGY)?0:energyRegen;

        x+=xvel;
        y+=yvel;

        // bounce off walls
        if((x <= 0 && xvel < 0) || (x + dimen > Launcher.GAME_WIDTH && xvel > 0)) {
            xvel *= -0.1;
            if (x<=0){
                x=0;
            }else {
                x = Launcher.GAME_WIDTH-dimen;
            }
        }
        if((y <= 0 && yvel < 0) || (y +dimen > Launcher.GAME_HEIGHT && yvel > 0)) {
            yvel *= -0.1;
            if (y<=0){
                y=0;
            }else {
                y = Launcher.GAME_HEIGHT-dimen;
            }
        }
    }

    public int scan(double theta){
        if(energy < costs.get("scan")){
            return -1;
        }
        energy-=costs.get("scan");

        theta = Math.toRadians(theta);
        double distance = 0;
        double x= this.x + dimen/2. + Math.cos(theta)*Math.sqrt(2) * (dimen+2);
        double y= this.y + dimen/2. + Math.sin(theta)*Math.sqrt(2) * (dimen+2);
        while(!launcher.pointcollide(x, y)){
            x+=Math.cos(theta)*Launcher.step;
            y-=Math.sin(theta)*Launcher.step;
            distance+=Launcher.step;
        }
        return (int)distance;

    }

    public void fire(double theta){
        if(energy < costs.get("fire")){
            return;
        }
        energy-=costs.get("fire");
        Bullets b = new Bullets(x+dimen/2, y+dimen/2, theta, team);
    }

    public void thrust(double theta){
        xvel += Math.cos(Math.toRadians(theta)) * THRUSTPOWER;
        yvel -= Math.sin(Math.toRadians(theta)) * THRUSTPOWER;
    }

}

