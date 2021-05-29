/*
Subklasse av klassen Legemiddel.
**/

public class Vanlig extends Legemiddel {

    //Tar inn konstruktoren til superklassen
    public Vanlig(String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}