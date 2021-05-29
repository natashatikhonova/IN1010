//vi lager en klasse Node som inneholder 2 instansvariablene
public class Node{

    //deklarerer instansvariablene
    private int minnestorelse;
    private int prosessorantall;

    //tar imot verdiene for de instansvariablene i parametrene til konstruktoren
    public Node(int minnestorelse, int prosessorantall){
        this.minnestorelse=minnestorelse;
        this.prosessorantall=prosessorantall;
    }

    //lager en metode som returnerer antall prosessorer i node
    public int antProsessor (){
        return prosessorantall; 
    }

    //lager en metode som tar imot et tall med paakrevd Minne og sjekker om minnestorelse av noden er storre eller lik den paakrevd minne
    public boolean nokMinne(int paakrevdMinne){
        return minnestorelse>=paakrevdMinne;
    }
    
}
