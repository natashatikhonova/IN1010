/*
Subklasse av klassen Resept.
**/

public class HvitResept extends Resept {
    
    //Tar inn konstruktoren til superklassen
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public int prisAaBetale() {
        return legemiddel.hentPris();
    }

    @Override
    public String farge() {
        return "hvit";
    }

    @Override
    public String toString() { 
        return super.toString();
    }
}