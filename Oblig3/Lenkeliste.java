//lager en klasse Lenkeliste som implementerer grensesnitt Liste
public class Lenkeliste<T> implements Liste<T>{

    //lager instansvariablene
    private Node<T> forste = new Node<>(null);
    int antNoder = 0;

    //lager metoden som legger til elementet på slutten av listen
    @Override
    public void leggTil(T data){
        //hvis listen er tom
        if(forste.data==null){
            forste.data=data;
        } else{
            Node<T> temp = forste;
            //gaa fram til siste elementet
            while (temp.neste!=null){
                temp=temp.neste;
            }
            //sette inn elementet
            temp.neste = new Node<>(data);
        }
        //oke antall noder
        antNoder++;
    }

    //lager metoden som fjerner og returnerer noden paa starten av listen
    @Override
    public T fjern(){
        //listen er tom
        if(forste.data==null){
            throw new UgyldigListeIndeks(0);
        } else{
            //lagrer data fra noden
            T data = forste.data;
            //fjerner node
            forste = forste.neste;
            //minker antall noder
            antNoder--;
            return data;
        }
    }

    //lager metoden som setter inn noden paa oppgitt posisjon
    @Override
    public void sett(int pos, T data){
        Node<T> nyNode = new Node<>(data);
        Node<T> temp = forste;

        //sjekk om oppgitt posisjon er gyldig
        if(pos>=0 && pos<antNoder){
            for (int i=0; i<pos; i++){
                temp = temp.neste;
            }
            temp.data = nyNode.data;
        } else{
            //hvis ikke, kast unntak
            throw new UgyldigListeIndeks(pos);
        }

    }

    //lager metoden som legger til noden paa oppgitt posisjon
    @Override 
    public void leggTil(int pos, T data) {
        Node<T> temp = forste;
        //sjekker om posisjon er gyldig
        if(pos>=0 && pos<=antNoder){
            //hvis listen er tom,
            if(antNoder==0){
                forste.data = data;
            } else if(pos == 0){
                forste = new Node<>(data);
                forste.neste = temp;
            } else{ 
                //gaa frem til elementet for den hvor den skal legges til
                for (int i=1; i<pos; i++){
                    temp=temp.neste;
                }

                Node<T> husk = temp.neste;
                temp.neste = new Node<>(data);
                temp.neste.neste=husk;
            }

            antNoder++;

        } else{
            //hvis ikke, kast unntak
            throw new UgyldigListeIndeks(pos);
        }
        
    }

    //lager en metode som fjerner en node paa oppgitt posisjon
    @Override
    public T fjern(int pos){
        Node <T> temp = forste;
        //sjekker om posisjon er gyldig
        if (pos>=0 && pos<antNoder){
            
            //gaar frem til det elementet som skal fjernes
            for (int i=1; i<pos; i++){
                temp = temp.neste;
            }

            //hvis listen er tom
            if (forste.data==null){
                throw new UgyldigListeIndeks(0);
            }

            //hvis ett element i listen
            else if (antNoder==1){
                T data = forste.data;
                forste.data=null;
                antNoder--;
                return data;
            }
            //hvis den er forste i liste
            else if(pos==0){
                forste= temp.neste;
                antNoder--;
                return temp.data;
            }

            T fjernetData  = temp.neste.data;
            Node<T> n = temp.neste;
            temp.neste = n.neste;
            antNoder--;

            return fjernetData;
        } else{
            //hvis ikke, kast unntak
            throw new UgyldigListeIndeks(pos);
        }

    }

    //metoden returnerer antall noder i listen
    @Override
    public int stoerrelse(){
        return antNoder;
    }

    //metoden finner noden paa oppgitt posisjon, og returnerer data fra den noden
    @Override
    public T hent(int pos){
        Node<T> temp = forste;
        //sjekker om posisjon er gyldig
        if(pos>=0 && pos<antNoder){
             for(int i=0; i<pos; i++){
                temp=temp.neste;
            }
            return temp.data;  
        } else{
            //hvis ikke, kast unntak
            throw new UgyldigListeIndeks(pos);
        }

    }

    //metode som returnerer listen som en streng
    @Override
    public String toString(){
        Node<T> temp = forste;
        String tempString = "";
        while (temp!=null){
            tempString+=temp.data+"-->";
            temp=temp.neste;
        }

        return tempString;
    }

}