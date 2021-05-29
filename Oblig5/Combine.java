import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

//traad klasse som slaar sammen hashmapper
public class Combine implements Runnable { 
    private HashBeholder beholder;
    CountDownLatch count;
    private int teller;

    public Combine(HashBeholder beholder, CountDownLatch count, int teller){
        this.beholder = beholder;
        this.count = count;
        this.teller = teller;

    }

    //slaa sammen hashmapper
    @Override
    public void run(){
        for (int i = 0; i<teller; i++){
            HashMap<String, Subsekvens> forste;
            HashMap <String, Subsekvens> andre;
            HashMap <String, Subsekvens> combined;
            try {
                forste = beholder.taUt();
                andre = beholder.taUt();
                combined = beholder.flett(forste, andre);
                beholder.leggTil(combined);
                count.countDown();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
            
        }
    }
}
