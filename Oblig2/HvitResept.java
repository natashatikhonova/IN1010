//subklasse HvitResept av klassen Resept
public class HvitResept extends Resept{
    //tar imot alle instansvariablene til superklassen
    HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    //metoden som returnerer fargen paa resept
    public String farge(){
        return "Hvit";
    }

    //metoden som returnerer fargen paa resept
    public int prisAaBetale(){
        return legemiddel.hentPris();
    }
}
