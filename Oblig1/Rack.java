//importerer ArrayList slik det blir enklere aa jobbe med lister i utviklingsprosessen
import java.util.ArrayList;

//lager en klasse Rack
public class Rack {

    //deklarerer instansvariavel noder som er en array som bestaar av Node-objekter
    private ArrayList<Node> noder;
    //konstruktorer som setter instansvariabel
    public Rack(){
        noder = new ArrayList<Node>();
    }

    //lager en metode som tar imot et Node-objekt i paramteren og legger det objektet til noder-array (instansvariabel)
    public void settInn(Node node){
        noder.add(node);
    }

    //lager en metode som returnerer antall noder i arrayen
    public int getAntNoder(){
        return noder.size();
    }

    //lager en metoden som gaar gjennom arrayen og kalkulerer totalt antall prosessorer
    public int antProsessorer(){
        int antallPros = 0;

        //bruker forEach - lokke for aa gaa gjennom hele arrayen
        for(Node node : noder){
            antallPros+=node.antProsessor();
        }
        return antallPros; 
    }

    //lager en metode som tar imot et tall med paakrevd minne og returnerer antall noder som har minst paakrevd minne
    public int noderMedNokMinne(int paakrevdMinne){
        int antNoder=0;

        //bruker forEach - lokke for aa gaa gjennom hele arrayen og sjekke hvor mange noder har saa mye minne som er gitt i parameteren
        for(Node node : noder){
            if (node.nokMinne(paakrevdMinne)){
                antNoder++;
            }
        }
        return antNoder;
    }
}
