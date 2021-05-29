//lager en abstract klasse Resept slik man ikke kan opprette en instans av denne klassen
abstract class Resept {
    //oppretter instansvariabele av klassen
    Legemiddel legemiddel;
    Lege utskrivendeLege;
    int pasientId;
    int reit;
    //lager en static variabel teller som skal ta vare paa ID av hver enkel resept som blir opprettet
    static int teller = 0;
    int id;
    //boolean som skal ta vare på gyldighet til resepten
    boolean gyldig;

    //konstruktor til klassen som tar imot legemiddel, lege, pasientId og reit
    Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
        //setter instansvariablene
        this.legemiddel=legemiddel;
        this.utskrivendeLege=utskrivendeLege;
        this.pasientId=pasientId;
        this.reit=reit;
        //øker teller med 1 og setter ID til resepten er lik dette tallet
        teller++;
        id=teller;
        //sjekker og setter gyldighet til resepten
        settGyldighet();
    }

    //hjelpemetoden som setter resept som ugyldig, om den har mindre eller 0 reit igjen
    public void settGyldighet(){
        if (reit<=0){
            gyldig = false;
        } else{
            gyldig = true;
        }
    }

    //metoden som henter ID
    public int hentId(){
        return id;
    }

    //metoden som henter legemiddel
    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    //metoden som henter utskrivende lege
    public Lege hentLege(){
        return utskrivendeLege;
    }

    //metoden som henter ID til pasienten
    public int hentPasientId(){
        return pasientId;
    }

    //metoden som henter reit
    public int hentReit(){
        return reit;
    }

    //metoden sjekker om resept er gyldig; hvis den er gyldig, så bruker vi resepten, antall reit minker med 1, og gyldighet oppdateres, og true returneres siden det var mulig å bruke resepten
    //om reserpten er ugyldig, returneres false, siden det er umulig å bruke resepten
    public boolean bruk(){
        if (gyldig){
            reit--;
            settGyldighet();
            return true;
        } else{
            return false;
        }
     }

     //lager abstract metode som skal returnere farge til resepten
     abstract public String farge();

     //lager abstract metode som skal returnere pris man skal betale for legemiddel
     abstract public int prisAaBetale();

     //metode returnerer Resept objekt som en string
     public String toString(){
        return this.farge()+" Resept: \nPasient ID:"+pasientId+"\n"+legemiddel.toString()+"\nAntall reit: "+reit+"\nUtskrivende Lege:\n"+utskrivendeLege.toString()+"\nPris aa betale: "+prisAaBetale();
    }

}
