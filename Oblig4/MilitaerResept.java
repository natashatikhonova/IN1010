/*
Subklasse av klassen HvitResept.
**/

public class MilitaerResept extends HvitResept {
    protected int pris;
    protected String farge;

    //Tar inn konstruktoren til superklassen
    public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
        farge = farge();
    }
    
    //Returner den nye prisen til resepten
    @Override
    public int prisAaBetale() {
        pris = legemiddel.hentPris();
        pris = 0;
        return pris;
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
        return super.toString() + "\nPris: " + Integer.toString(prisAaBetale()) + "\nFarge: " + farge;
    }
}