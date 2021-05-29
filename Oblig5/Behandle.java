import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

//traad klassen som leger subskevensene fra filene til beholderen
public class Behandle implements Runnable {

    private ArrayList<String> alleFiler;
    private HashBeholder trueBeholder;
    private HashBeholder falseBeholder;
    CountDownLatch count;
    private int start;
    private int slutt;
    static int lengde = 3;
    
    public Behandle(ArrayList<String> alleFiler,HashBeholder trueBeholder, HashBeholder falseBeholder, CountDownLatch count, int start, int slutt ){
        this.alleFiler = alleFiler;
        this.trueBeholder = trueBeholder;
        this.falseBeholder = falseBeholder;
        this.count = count;
        this.start = start;
        this.slutt = slutt;
    }

    //leser fra fil og legger hashmap til den riktige beholderen
    @Override
    public void run(){
        for(int i =start; i<slutt; i++){
            String [] biter = alleFiler.get(i).split(",");
            if(biter[1].toUpperCase().equals("TRUE")){
                lesFil(trueBeholder, biter[0]);
            } else if(biter[1].toUpperCase().equals("FALSE")){
                lesFil(falseBeholder, biter[0]);
            } else{
                System.out.println("Feil inn i filen");
            }
        }

        count.countDown(); 
    }

    //metoden som leser data fra filen og leger sekvensene til hashmappen, og deretter til beholderen
    static void lesFil(HashBeholder beholder, String fil){
        String linje, subStreng;
        String file = "Data/"+fil+".csv";

        try{
            Scanner sc = new Scanner(new File(file));
            HashMap<String, Subsekvens> hashmap = new HashMap<String, Subsekvens>();

            while(sc.hasNextLine()){
                linje = sc.nextLine().trim();

                for(int i = 0; i+lengde <=linje.length(); i++){
                    subStreng = linje.substring(i, i+lengde);
                    hashmap.putIfAbsent(subStreng, new Subsekvens(subStreng));
                }
            }
            sc.close();
            beholder.leggTil(hashmap);
        }catch(IOException e){
            System.out.println("Noe gikk galt:(");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
