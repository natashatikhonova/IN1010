//subklassen MilitarResept av subklassen HvitResept og superklassen Resept
public class MilitarResept extends HvitResept{
    
    //tar imot alle instansvariablene av superklassen
    MilitarResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    //setter pris man må betale lik 0
    public int prisAaBetale(){
        return 0;
    }

}
