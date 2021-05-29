/*
Superklasse: kan ikke opprette en instans
**/

public abstract class Legemiddel {
    protected String navn;
    protected int pris;
    protected double virkestoff;
    protected int id;
    private static int count = 1;

    //Konstruktor
    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        //Oker ID'en med 1 hver gang det opprettes et objekt av klassen.
        id = count++;
    }

    //Returnerer ID'en til legemiddelet.
    public int hentId() {
        return id;
    }
    public int hentStyrke(){
        return 0;
    }
    //Returnerer navnet til legemiddelet.
    public String hentNavn() {
        return navn;
    }

    //Returnerer prisen til legemiddelet.
    public int hentPris() {
        return pris;
    }

    //Returnerer mengden virkestoff til legemiddelet.
    public double hentVirkestoff() {
        return virkestoff;
    }   

    //Endrer og returnerer den nye prisen paa legemiddelet.
    public int settNyPris(int nyPris) {
        pris = nyPris;
        return pris;
    }
    
    @Override
    public String toString() {
        return "ID: " + id + "\n" + "Navn: " + navn + "\n" + "Pris: " + pris + "kr\n" + "Virkestoff: " + virkestoff + "mg\n";
    }

}