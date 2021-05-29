/*
Superklasse: kan ikke opprette en instans
**/

public abstract class Resept {
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;
    protected int id;
    private static int count = 1;

    //Konstruktor
    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        //Oker ID'en med 1 hver gang det opprettes et objekt av klassen.
        id = count++;
    }

    //Returnerer ID'en til resepten.
    public int hentId() {
        return id;
    }

    //Returnerer legemiddelet til resepten.
    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    //Returnerer legen som skriver ut resepten.
    public Lege hentLege() {
        return utskrivendeLege;
    }

    //Returnerer ID'en til pasienten som skal ha resepten.
    public Pasient hentPasient() {
        return pasient;
    }

    //Returnerer reiten til resepten.
    public int hentReit() {
        return reit;
    }

    //Prover aa bruke respten, returnerer true eller false
    public boolean bruk() {
        if (reit == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    //Returnerer fargen til resepten.
    abstract public String farge();

    //Returnerer prisen til resepten.
    abstract public int prisAaBetale();

    @Override
    public String toString() {
        return "ReseptID:"+id+"\n" + 
        "Pasient:\n" + pasient +  "\n" + "Lege:\n" + utskrivendeLege +"\nInfo om legemiddel:\n" + legemiddel + "\n"+
        "Reit: " + reit;
    }
}
