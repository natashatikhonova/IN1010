/*
Subklasse av klassen Legemiddel
**/
public class Narkotisk extends Legemiddel {
    protected int styrke;

    //Tar inn konstruktoren til superklassen, i tillegg til styrke
    public Narkotisk(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    @Override
    //Returner den narkotiske styrken til legemiddelet.
    public int hentStyrke() {
        return styrke;
    }

    @Override
    public String toString() { 
        return super.toString() + "Styrke: " + Integer.toString(styrke);
    }

}