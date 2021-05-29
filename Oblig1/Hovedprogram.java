
//lager et hovedprogram
public class Hovedprogram {
    public static void main(String[] args) {

        //lager 4 dataklynger
        Dataklynge abel = new Dataklynge("dataklynge.txt");
        Dataklynge abel2 = new Dataklynge("dataklynge2.txt");
        Dataklynge abel3 = new Dataklynge("dataklynge3.txt");
        Dataklynge abel4 = new Dataklynge("dataklynge4.txt");

        //sjekker om systemet fungerer som det skal
        abel.skrivUt();
        abel2.skrivUt();
        abel3.skrivUt();
        abel4.skrivUt();
    }
}
