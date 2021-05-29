import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Labyrint {
    private Rute[][] ruter;
    private int antRader;
    private int antKol;
    private File fil;
    public ArrayList<ArrayList<Tuppel>> utveier;

    public Labyrint(File fil) throws FileNotFoundException{
        this.fil = fil;
        antRader = 0;
        antKol = 0;
        ruter=lesFraFil();
        
    }

    //oppretter labyrint
    private Rute[][] lesFraFil()throws FileNotFoundException{
        Scanner sc = new Scanner (fil);
        antRader = sc.nextInt();
        antKol = sc.nextInt();
        Rute[][] brett = new Rute[antRader][antKol];
        int radTeller = -1;

        //gaar gjennom alle linjene i filen
        while(sc.hasNextLine()){
            String linje = sc.nextLine();
            for(int i = 0; i<linje.length(); i++){
                //hvite
                if(Character.toString(linje.charAt(i)).equals(".")){
                    //sjekk om aapning
                    if(i==0 || radTeller==0 || i==antKol-1 || radTeller == antRader-1){
                        Aapning aapning = new Aapning(radTeller, i);
                        brett[radTeller][i]=aapning;

                    } else{
                        HvitRute hvit = new HvitRute(radTeller, i);
                        brett[radTeller][i]=hvit;

                    }
                } else{
                    //sort
                    SortRute sort = new SortRute(radTeller, i);
                    brett[radTeller][i]=sort;
                }
            }
            radTeller++;
        }

        //gaar gjennom alle rutene og setter opp referanser til naboer og denne labyrinten
        for(int r = 0; r<antRader; r++){
            for(int k = 0; k<antKol; k++){
                if (r!=0){
                    brett[r][k].connentToNorth(brett[r-1][k]);
                }
                if(r!=antRader-1){
                    brett[r][k].connentToSouth(brett[r+1][k]);
                }
                if(k!=0){
                    brett[r][k].connentToWest(brett[r][k-1]);
                }
                if(k!=antKol-1){
                    brett[r][k].connentToEast(brett[r][k+1]);
                }
                brett[r][k].connectToLabyrint(this);
            
            }
        }
        sc.close();
        return brett;

    }

    //kaller paa finnUtvei fra denne labyrinten og returnerer listen med utveier
    public ArrayList<ArrayList<Tuppel>> finnUtveiFra(int kol, int rad){
        utveier= new ArrayList<ArrayList<Tuppel>>();
        ruter[rad][kol].finnUtvei();
        return utveier;
    }

    @Override
    public String toString(){
        String skrivUt = "";
        for(Rute[] rad : ruter){
            for(Rute rute : rad){
                skrivUt+=rute.tilTegn();
            }
            skrivUt+="\n";
        }
        return skrivUt;
    }
}
