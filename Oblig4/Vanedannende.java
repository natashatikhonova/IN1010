/*
Subklasse av klassen Legemiddel
**/

public class Vanedannende extends Legemiddel {
    protected int styrke;

    //Tar inn konstruktoren til superklassen, i tillegg til styrke.
    public Vanedannende(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    @Override
    //Returnerer den vanedannende styrken til legemiddelet.
    public int hentStyrke() {
        return styrke;
    }

    @Override
    public String toString() { 
        return super.toString() + "Styrke: " + Integer.toString(styrke);
    }

}