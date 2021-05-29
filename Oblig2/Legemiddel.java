//lager en abstract klasse Legemiddel slik man ikke kan opprette en instans av denne klassen

abstract class Legemiddel{
    //deklarerer alle instansvariablene som brukes i klassen
    String navn;
    int pris;
    double virkestoff;
    //lager en static variabel teller som skal ta vare paa ID av hver enkelt legemiddel som blir opprettet
    static int teller = 0;
    int id;

    //konstuktor til klassen som tar imot navn, pris og virkestoff til legemiddel
    Legemiddel(String navn, int pris, double virkestoff){
        this.navn=navn;
        this.pris=pris;
        this.virkestoff=virkestoff;
        //hver gang et ny leggemiddel opprettes, skal teller øke med 1, og id skal settes til det nummeret
        teller++;
        id=teller;
    }

    //metode som returnerer id 
    public int hentId(){
        return id;
    }

    //metode som returnerer navn 
    public String hentNavn(){
        return navn;
    }

    //metode som returnerer pris
    public int hentPris(){
        return pris;
    }

    //metode som returnerer virkestoff
    public double hentVirkestoff(){
        return virkestoff;
    }

    //metode som tar inn ny pris og setter instansvariabel pris til en ny verdi
    public void settNyPris(int nyPris){
        pris=nyPris;
    }

}