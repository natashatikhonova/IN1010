/*
Subklasse av klassen Resept.
**/

public class BlaaResept extends Resept {
    protected int pris;
    protected String farge;

    //Tar inn konstruktoren til superklassen
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
        farge = farge();
    }

    //Returner den nye prisen til resepten.
    @Override
    public int prisAaBetale() {
        double orginalPris = legemiddel.hentPris();
        pris = (int) Math.round((orginalPris * 25) /100);

        if (pris > 0) {
            return pris;
        }
        else {
            pris = 0;
            return pris;
        }
    }

    @Override
    public String farge() {
        return "blaa";
    }

    @Override
    public boolean bruk() {
        if (reit <= 0) {
            return false;
        }
        else {
            reit--;
            return true;
        }
    }
    
    @Override
    public String toString() { 
        return super.toString() + "\nPris: " + Integer.toString(prisAaBetale()) + "kr\n" + "Farge: " + farge;
    }
}