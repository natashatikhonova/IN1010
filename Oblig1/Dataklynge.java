//importerer ArrayList slik det blir enklere aa jobbe med lister i utviklingsprosessen
import java.util.ArrayList;
//importerer disse libraries slik vi kan lese fra filen
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//lager en klasse Dataklynge
public class Dataklynge {

    //deklarerer 2 instansvariabler: en array som bestaar av Rack-objekter og et tall som viser antall noder per rack
    private ArrayList<Rack> racks;
    private int noderPerRack;

    //konstruktoren tar imot filnavn
    public Dataklynge (String filnavn){
        //setter opp en array av Rack-objekter
        racks = new ArrayList<Rack>();
        //lager en fil 
        File fil = new File(filnavn);
        //leser fra den oppgitte filen
        Scanner in; 
        //prover aa faa data fra filen
        try{
            in = new Scanner(fil);
        } //om vi klarer ikke aa faa data, skriver ut en feil melding og setter data er lik tom tekst
        catch(FileNotFoundException exception){
            System.out.println("Fant ikke filen!");
            in = new Scanner(""); 
        }

        //lager en boolean variabel som skal hjelpe oss aa ha kontroll over den forste linje i filen
        boolean forste =true;

        //gaar gjennom hele filen ved hjelp av while-lokken
        while(in.hasNextLine()){
            //hvis vi har nettopp aapnet filen, setter vi forste tall til antall noder per rack og setter boolean hjelpevariabel er lik false
            if (forste){
                noderPerRack = in.nextInt();
                forste = false;
            } 
            //deklarerer de andre tall fra linje i teksten
            else{
                int antNoder = in.nextInt();
                int minne = in.nextInt();
                int antPros = in.nextInt();
                //setter inn noder
                for (int i=0; i<antNoder; i++){
                    Node node = new Node(minne, antPros);
                    settInnNode(node);
                }
            }
        }
        in.close();

    }

    //lager en metode som skal sette inn noder
    public void settInnNode(Node node){

        //lager ledig rack - et Rack objekt som er for naa lik null (har ikke noe verdi)
        Rack ledigRack = null;
        int i = 0; 
        //sjekker om det finnes et ledig rack
         while(ledigRack==null && i<racks.size()){
             if (racks.get(i).getAntNoder()<noderPerRack){
                 ledigRack=racks.get(i);
             }
             i++;
         }

         //hvis det ikke finnes et ledig rack, lager et nytt rack
         if (ledigRack == null){
             Rack nyRack = new Rack();
             ledigRack=nyRack;
             racks.add(nyRack);
         }
         //setter inn node-objekt i dette racket
         ledigRack.settInn(node);
         
    }

    //lager en metode som gaar gjennom alle rack-objektene i rack-arrayen og kalkulerer antall prosessorer
    public int antProsessorer(){
        int antPros = 0;
        for (Rack rack : racks){
            antPros+=rack.antProsessorer();
        }
        return antPros;
    }

    //en metode som tar imot et tall med paakrevd minne og returnerer antall noder
    public int noderMedNokMinne(int paakrevdMinne){
        int antNoder=0;

        //gaar gjennom alle Rack-objekter i racks-arrayen for aa sjekke hvor mange av dem har minst paakrevd minne
        for (Rack rack : racks){
            antNoder+=rack.noderMedNokMinne(paakrevdMinne);
        }

        return antNoder;
    }

    //en metoden som returnerer antall racks i dataklyngen
    public int antRacks(){
        return racks.size();
    }


    //en hjelpe metode for aa sjekke om systemet fungerer som det skal
    public void skrivUt(){
        System.out.println("Noder med minst 32GB: "+ noderMedNokMinne(32));
        System.out.println("Noder med minst 64GB: "+ noderMedNokMinne(64));
        System.out.println("Noder med minst 128GB: "+ noderMedNokMinne(128));
        System.out.println("Totalt antall prosessorer: "+ antProsessorer());
        System.out.println("Antall rack: "+ antRacks());
    }
}
