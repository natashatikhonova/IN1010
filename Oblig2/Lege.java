//oppretter klassen Lege
public class Lege {
    //tar in en instansvariabel
    String navn;
    //oppretter konstruktor
    Lege(String navn){
        this.navn=navn;
    }

    //metode som returnerer navn
    public String hentNavn(){
        return navn;
    }

    //metode som skriver Lege objektet som en string
    public String toString(){
        return "Stutus: Lege \nNavn til legen: "+this.hentNavn();
    }
}
