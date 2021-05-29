/*
Subklasse av klassen HvitResept.
**/

public class PResept extends HvitResept {
    protected String farge;

    //Tar inn konstruktoren til superklassen, men tar ikke inn reit
    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(legemiddel, utskrivendeLege, pasient, 3);
        farge = farge();
    }
    
    //Returner den nye prisen til resepten.
    @Override
    public int prisAaBetale() {
        int pris = legemiddel.hentPris();
        int rabatt = 108;

        if (pris-rabatt > 0) {
            pris = pris - rabatt;
        }
        else {
            pris = 0;
        }

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