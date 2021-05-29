import java.util.ArrayList;

public abstract class Rute{
    public int rad;
    public int kol;
    public Rute north, south, west, east;
    Labyrint labirynt;

    public Rute(int rad, int kol){
        this.rad = rad;
        this.kol = kol;
    }
    abstract char tilTegn();

    //setter referansen til labyrint
    public void connectToLabyrint(Labyrint labyrint){
        this.labirynt = labyrint;
    }
    
    //setter referansen til naboer
    public void connentToNorth(Rute north){
        this.north = north;
    }

    public void connentToSouth(Rute south){
        this.south = south;
    }

    public void connentToEast(Rute east){
        this.east = east;
    }

    public void connentToWest(Rute west){
        this.west = west;
    }

    abstract void gaa(Rute komFra, ArrayList<Tuppel> forrige);

    //lager en tom liste og kaller paa gaa metoden fra denne ruten
    public void finnUtvei(){
        ArrayList<Tuppel> forrige = new ArrayList<>();
        this.gaa(this, forrige);
    }


    
}