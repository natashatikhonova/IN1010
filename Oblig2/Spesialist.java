//subklasse Spesialist av superklassen Lege; implementerer grensesnitt Godkjenningsfritak
public class Spesialist extends Lege implements Godkjenningsfritak{
    //har sin egen instansvariabel
    String kontrollId;
    //oppretter konstruktor, og tar imot 2 instansvariablene
    Spesialist(String navn, String kontrollId){
        super(navn);
        this.kontrollId=kontrollId;
    }

    //metoden som returnerer kontroll id
    public String hentKontrollID(){
        return kontrollId;
    }

    //metoden som skriver Spesialist objektet som en string
    @Override
    public String toString(){
        return "Status: Spesialist\nNavn til Spesialisten: "+this.hentNavn()+"\nKontroll ID: "+this.hentKontrollID();
    }
}
