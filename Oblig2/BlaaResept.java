//subklasse Blaaresept av klassen Resept
public class BlaaResept extends Resept {
    //tar imot alle instansvariablene til superklassen
    BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        super(legemiddel, utskrivendeLege, pasientId, reit);
    }

    //metoden returnerer fargen på resepten
    public String farge(){
        return "Blaa";
    }

    //metoden regner ut og returnerer pris å betale
    @Override
    public int prisAaBetale(){
        int pris = legemiddel.hentPris();
        int nyPris = (int)Math.round(pris*0.25);
        return nyPris;
    }


}
