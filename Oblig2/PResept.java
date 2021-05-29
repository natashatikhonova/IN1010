//subklassen PResept av subklassen HvitResept og superklassen Resept
public class PResept extends HvitResept{
    //tar imot legemiddel, utskrivendelege og pasientId fra superklassen
    PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId){
        //setter 3 som fast antall reit for alle p-reseptene
        super(legemiddel, utskrivendeLege, pasientId, 3);
    }

    //metoden sjekker hvor mye man må betale avhengig av original prisen av legemiddlet
    @Override
    public int prisAaBetale(){
        if (legemiddel.hentPris()<=108){
            return 0;
        } else {
            return legemiddel.hentPris()-108;
        }
    }

}
