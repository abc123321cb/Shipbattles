import java.util.ArrayList;

public class SampleScanner extends Ship{
    private int t;

    SampleScanner(int x, int y, Launcher launcher, int team){
        super(x, y, launcher, team);

        name = "Scanner";
        t = 100;

    }

    public void move() {
        super.move();

        if (t>0){
            thrust(-45);
            t--;
        }

        if (Math.random()>0.8){
            thrust(-45);
        }
        if (getEnergy() >= 0.9*MAXENERGY){
            ArrayList<Integer> results = new ArrayList<>();
            int s = 90;
            for(int i = s; i < s+90; i += 8){
                results.add(scan(i));
            }
            
            int max = 0;
            int index = 0;
            results.add(0, results.get(results.size()-1));
            results.add(results.get(1));

            for(int i = 1; i < results.size()-1;i++){
                int x = Math.abs(results.get(i)-results.get(i-1)) + Math.abs(results.get(i)-results.get(i+1));
                if (x > max){
                    max = x;
                    index = i - 1;
                }
            }

            fire(index*15);

        }

    }

}
