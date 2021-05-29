//lager en klasse SortertLenkeliste som arver fra Lenkeliste
public class SortertLenkeliste<T extends Comparable <T>> extends Lenkeliste<T> {

    //overskriver metoden leggTil
    @Override
    public void leggTil(T x){
        //hvis listen er tom
        if (stoerrelse()==0){
            //legger til elementet
            super.leggTil(x);
            return;
        } else{
            //gaar gjennom alle elementene i listen
            for(int i =0; i<stoerrelse(); i++){
                //sammenligner dataen fra elementet til oppgitt data
                if(hent(i).compareTo(x)>0){
                    //legger til node med ingen data
                    super.leggTil(null);
                    //setter inn node på oppgitt posisjon
                    for(int ix = stoerrelse()-2; ix>=i; ix--){
                        sett(ix+1, hent(ix));
                    }
                    sett(i, x);
                    return;
                }
            }

            super.leggTil(x);
        }
    }

    //overskriver metoden fjern
    @Override
    public T fjern(){
        //hvis listen er tom
        if (stoerrelse()==0){
            //kast en unntak
            throw new UgyldigListeIndeks(0);
        } else{
            //siden storste elementet edr siste elementet, finner vi siste elementet og lagrer data
            T data = hent(stoerrelse()-1);
            //fjerner siste elementet
            super.fjern(stoerrelse()-1);
            return data;
        }
    }

    //overskriver metoden
    @Override
    public void sett(int pos, T x){
        //hvis posisjonen er gyldig, kaller pa super metoden
        if (pos>=0 && pos<=stoerrelse()-1){
            super.sett(pos, x);
        } else{
            //hvis ikke, kast unntak
            throw new UnsupportedOperationException();
        }
    }

    //overskriver metoden
    @Override
    public void leggTil(int pos, T x){
        //hvis posisjon er gyldig, kaller paa super metoden
        if (pos>=0 && pos<=stoerrelse()-1){
            super.leggTil(pos, x);
        } else{
            //hvis ikke, kast unntak
            throw new UnsupportedOperationException();
        } 
    }
    
    
}
