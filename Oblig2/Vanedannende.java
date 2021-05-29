//subklasse Vanedannende av klassen Legemiddel
public class Vanedannende extends Legemiddel{
    //instansvariabel av denne subklassen
    int styrke;

    //konstruktor tar imot navn, pris, virkestoff og styrke til legemiddel
    public Vanedannende (String navn, int pris, double virkestoff,int styrke){
        //setter alle instansvariablene
        super(navn, pris, virkestoff);
        this.styrke=styrke;
    }

    //metoden som returnerer styrke 
    public int hentVanedannendeStyrke(){
        return styrke;
    }

    //metode som skriver objektet som en string 
    public String toString(){
        return ("Vanedannende Legemiddel:\nNavn paa Legemiddel: "+navn+"\nLegemiddel ID: "+id+"\nOriginal Pris: "+ pris +"\nVirkestoff: "+virkestoff+"\nStyrke: "+ styrke );
    }
}
