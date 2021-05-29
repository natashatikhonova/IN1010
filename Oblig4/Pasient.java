public class Pasient extends Stabel<Resept>{
    protected String navn;
    protected String fodselsnr;
    protected int id;
    private static int count = 1;
    //Liste over reseptene til pasienten
    protected Stabel<Resept> resepter;

    //Konstruktor
    public Pasient(String navn, String fodselsnr) {
        this.navn = navn;
        this.fodselsnr = fodselsnr;
        //Oker ID'en med 1 hver gang det opprettes et objekt av klassen.
        id = count++;
        //Oversikt over resepter
        resepter = new Stabel<Resept>();
    }

    public int hentId(){
        return id;
    }

    public void leggTilResept(Resept nyResept) {
        resepter.leggPaa(nyResept);
    }

    public void brukResept() {
        resepter.taAv();
    }


    public void brukResept(int pos){
        if (resepter.hent(pos).bruk()){
            System.out.println("Brukte resept paa "+resepter.hent(pos).hentLegemiddel().hentNavn()+". Antall gjennvaerende reit: "+resepter.hent(pos).hentReit());
        } else{
            System.out.println("Kunne ikke bruke resept paa"+resepter.hent(pos).hentLegemiddel().hentNavn()+"(ingen gjenvaerende reit).");
        }
        
    }

    //returnerer resepter
    public Stabel<Resept> hentResepter() {
        return resepter;
    }

    //antall resepter paa nark legemidler
    public int antallNarkotiskResept(){
        int teller=0;
        if (this.resepter.stoerrelse()>0){
            for (Resept resept : this.resepter){
                if (resept.hentLegemiddel() instanceof Narkotisk){
                    teller++;
                }
            }
        }
        return teller;
    }


    @Override
    public String toString() {
        return "Pasientnr " + id + ": " + navn + " (" + fodselsnr + ")";
    }

}