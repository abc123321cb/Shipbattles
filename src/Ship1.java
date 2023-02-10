public class Ship1 extends Ship {

    Ship1(int x, int y, Launcher launcher, int team) {
        super(x, y, launcher, team);


    }

    @Override
    public void move() {
        super.move();
        if(getEnergy()>0.8*MAXENERGY){
            double theta = Math.random()*360;
            if(scan(theta)<200){
                fire(theta);
            }
        }


    }
}
