//subklasse Narkotisk av klassen Legemiddel
public class Narkotisk extends Legemiddel{
    //instansvariabel av denne subklassen
    int styrke;

    //konstruktor tar imot navn, pris, virkestoff og styrke til legemiddel
    public Narkotisk (String navn, int pris, double virkestoff,int styrke){
        //setter alle instansvariablene
        super(navn, pris, virkestoff);
        this.styrke=styrke;
    }

    //metoden som returnerer styrke
    public int hentNarkotiskStyrke(){
        return styrke;
    }

    //metoden som skriver objektet som en string
    public String toString(){
        return ("Narkotisk Legemiddel:\nNavn paa Legemiddel: "+navn+"\nLegemiddel ID: "+id+"\nOriginal Pris: "+pris +"\nVirkestoff: "+virkestoff+"\nStyrke: "+ styrke );
    }
}
