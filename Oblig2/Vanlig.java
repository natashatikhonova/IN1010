//subklassen Vanlig av klassen Legemiddel
public class Vanlig extends Legemiddel{

    //oppretter konstruktor som tar imot alle instansvariablene til superklassen
    public Vanlig(String navn, int pris, double virkestoff){
        super(navn, pris, virkestoff);
    }

    //metode som skriver objektet som en string
    public String toString(){
        return ("Vanlig Legemiddel:\nNavn paa legemiddel: "+navn+"\nLegemiddel ID: "+id+"\nOriginal Pris: "+pris + "\nVirkestoff: "+virkestoff);
    }
}
