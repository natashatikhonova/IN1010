public class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {

    //Konstruktor: arver fra superklassen.
    public SortertLenkeliste() {
        super();
    }

    @Override
    public void leggTil(T x) {

        //Legger til node paa starten, hvis listen er tom.
        if (stoerrelse() == 0) {
            super.leggTil(0, x);
            return;
        }

        else {
            for (int i = 0; i < stoerrelse(); i++) {
                //Sjekker om oppgitt verdi er storre enn verdien vi befinnner oss paa.
                if (hent(i).compareTo(x) > 0) {
                    //Legger til et tomt objekt.
                    super.leggTil(null);
                    //Plasserer node paa oppgitt posisjon.
                    for (int ix = stoerrelse()-2; ix >= i; ix--) {
                        super.sett(ix+1, super.hent(ix));
                    }
                    super.sett(i, x);
                    return;
                }
            }
            super.leggTil(x);
        }
    }

    //Fjerner storste verdi - vil alltid vaere siste objektet. 
    @Override
    public T fjern() {
        if (stoerrelse() == 0) {
            throw new UgyldigListeIndeks(-1);
        }
        else {
            T element = hent(stoerrelse()-1);
            super.fjern(stoerrelse()-1);
            return element;
        } 
    }

    @Override
    public void leggTil(int pos, T x) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
    }

    @Override
    public void sett(int pos, T x) throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
    }

    @Override
    public String toString() { 
        return super.toString();
    }
}