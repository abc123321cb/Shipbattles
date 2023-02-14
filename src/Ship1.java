public class Ship1 extends Ship {
    double t = Math.random()*360;
    Ship1(int x, int y, Launcher launcher, int team) {
        super(x, y, launcher, team);
        // make sure to add a name so we can tell apart the health and energy bars
        name = "Training dummy";

    }

    /*
    the move function is called once a frame the readme file will contain a list of variables you are allowed
    to access and other rules hopefully more indepth explaination on how things work.
     */

    @Override
    public void move() {
        super.move();
        if(getEnergy()>0.8*MAXENERGY){
            double theta = Math.random()*360;
            if(scan(theta)<200){
                fire(theta);
                thrust(theta+180);
            }
        }

    }
}
