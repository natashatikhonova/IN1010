import java.util.Iterator;

public class Lenkeliste<T> implements Liste<T> {

    public Node start;
    public Node slutt;

    //Konstruktor
    public Lenkeliste() {
        start = null; 
        slutt = null;
    }

    //Oppretter indre klasse
    public class Node {
        Node neste = null;
        Node forrige = null;
        T data;

        Node(T x) {data = x;}
    }

    //Iterator
    public class LenkelisteIterator implements Iterator<T> {
        protected Liste<T> lenkeliste;
        protected int pos;

        public LenkelisteIterator(Lenkeliste<T> nyLenkeliste) {
            lenkeliste = nyLenkeliste;
            pos = 0;
        }

        public T next() {
            return lenkeliste.hent(pos++);
        }

        public boolean hasNext() {
            return (pos < lenkeliste.stoerrelse()); 
        }

    }

    //Legger til node i slutten av listen.
    @Override
    public void leggTil(T x) {
        //Node peker = start;
        Node nyNode = new Node(x);

        if (start == null) {
            start = nyNode;
            slutt = start;
        }
        else {
            slutt.neste = nyNode;
            nyNode.forrige = slutt;
            slutt = nyNode;
            slutt.neste = null;
        }
    }

    //Fjerner den forste noden i listen. 
    @Override
    public T fjern() throws UgyldigListeIndeks{
        T element = null;

        if (start == null) {
            //Kaster unntak om listen er tom.
            throw new UgyldigListeIndeks(-1);
        }
        else {
            element = start.data;
            start = start.neste;
        }

        return element;
    }

    //Setter inn verdi paa en valgt posisjon.
    @Override
    public void sett(int pos, T x) throws UgyldigListeIndeks {
        Node peker = start;
        Node nyVerdi = new Node(x);

        //Sjekker om posisjonen eksisterer.
        if (pos >= 0 && pos < stoerrelse()) {
            for (int i = 0; i < pos; i++) {
                peker = peker.neste;
            }
            peker.data = nyVerdi.data;
        }
        else {
            //Kaster unntak om posisjonen er ugyldig.
            throw new UgyldigListeIndeks(pos);
        }
    }

    //Legger til node i valgt posisjon. 
    @Override
    public void leggTil(int pos, T x) throws UgyldigListeIndeks {
        Node peker = start;
        Node nyNode = new Node(x);

        //Sjekker om posisjonen er gyldig.
        if (pos >= 0 && pos <= stoerrelse()) {
            //Listen er tom
            if (start == null) {
                start = nyNode;
                slutt = start;
            }
            //Noden skal legges til i starten av listen. 
            else if (pos == 0) {
                nyNode.neste = start;
                start = nyNode;
            }
            else {
                //Finner fram til den valgte posisjonen.
                for(int i = 0; i < pos-1; i++) {
                    peker = peker.neste;
                } 
                nyNode.neste = peker.neste;
                peker.neste = nyNode;
            }
        }
        else {
            //Kaster unntak om posisjonen er ugyldig. 
            throw new UgyldigListeIndeks(pos);
        }
    }

    //Fjerner node paa valgt posisjon.
    @Override
    public T fjern(int pos) throws UgyldigListeIndeks {
        Node peker = start;
        Node fjernetNode = null;

        //Sjekker at posisjonen er gyldig.
        if (pos >= 0 && pos < stoerrelse()) {
            if (start == null) {
                fjernetNode = start;
                start = null;
                slutt = start;
            }

            //Fjerne node i starten av lista.
            else if (pos == 0) {
                fjernetNode = start;

                if (stoerrelse() == 2) {
                    start = null;
                    start = slutt;
                }

                else {
                    start = start.neste;
                }
            }

            //Fjerne node i slutten av lista.
            else if (pos == stoerrelse()-1) {
                fjernetNode = slutt;
                slutt = slutt.forrige;
                slutt.neste = null;
            }

            else {
                //Finner fram til oppgitt posisjon.
                for (int i = 0; i < pos-1; i++) {
                    peker = peker.neste;
                }

                if (peker.neste.neste == slutt) {
                    fjernetNode = peker.neste;
                    peker.forrige.neste = peker.neste;
                    peker.neste = slutt;
                }

                else {
                    fjernetNode = peker.neste;
                    peker.neste = peker.neste.neste;
                }
            }
            return fjernetNode.data;
        }

        else {
            //Kaster unntak om posisjonen er ugyldig.
            throw new UgyldigListeIndeks(pos);
        }

    }

    //Returnerer storrelsen paa listen
    @Override
    public int stoerrelse() {
        int teller = 0;
        Node peker = start;

        while(peker != null) {
            teller++;
            peker = peker.neste;
        }
        
        return teller;
    }

    //Returner node paa valgt posisjon. 
    @Override
    public T hent(int pos) throws UgyldigListeIndeks {
        Node peker = start;
        //int teller = 0;
        
        if (pos >= 0 && pos < stoerrelse()) {
            for (int i = 0; i < pos; i++) {
                peker = peker.neste;
            }
        }
        else {
            throw new UgyldigListeIndeks(pos);
        }

        return peker.data;
    }

    //Returnerer et nytt lenkeliste-objekt
    @Override
    public Iterator<T> iterator() {
        return new LenkelisteIterator(this);
    }
    
    @Override
    public String toString() {
        Node peker = start;
        String string = "";

        while(peker != null) {
            string += peker.data + "\n";
            peker = peker.neste;
        }
        return string;
    }
}


 