public class Stabel<T> extends Lenkeliste<T> {

    //Konstruktor: arver fra superklassen.
    public Stabel() {
        super();
    }

    //Legger til node paa slutten av listen.
    public void leggPaa(T x) {
        super.leggTil(x);
    }

    //Fjerner node fra slutten av listen.
    public T taAv() throws UgyldigListeIndeks {
        T element = null;

        if (stoerrelse() == 0) {
            //Kaster unntak om listen er tom.
            throw new UgyldigListeIndeks(-1);
        }
        else {
            element = hent(stoerrelse()-1);
            super.fjern(stoerrelse()-1);
            return element;
        }
    }

    @Override
    public String toString() { 
        return super.toString();
    }
}