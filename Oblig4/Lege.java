/*
Oppretter et Lege objekt
**/
public class Lege implements Comparable<Lege> {
    protected String navn;
    protected String kontrollID="0";
    //Liste over reseptene legen har skrevet ut
    protected Liste<Resept> utskrevendeResepter;

    //Konstruktor
    public Lege(String navn, String kontrollID) {
        this.navn = navn;
        this.kontrollID = kontrollID;
        utskrevendeResepter = new Lenkeliste<Resept>();
    }

    //Returnerer navnet paa legen
    public String hentNavn() {
        return navn;
    }

    public String hentKontrollID() {
        return kontrollID;
    }

    //Sorterer legene etter navn
    @Override
    public int compareTo(Lege annenLege) {
        return this.navn.compareTo(annenLege.hentNavn());
    }

    //Opprette instans av klassen HvitResept
    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        else {
            HvitResept nyResept = new HvitResept(legemiddel, this, pasient, reit);
            utskrevendeResepter.leggTil(nyResept);
            pasient.leggTilResept(nyResept);
            return nyResept;
        }
    }

    //Opprette instans av klassen MilitaerResept
    public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        else {
            MilitaerResept nyResept = new MilitaerResept(legemiddel, this, pasient, reit);
            utskrevendeResepter.leggTil(nyResept);
            pasient.leggTilResept(nyResept);
            return nyResept;
        }
    }

    //Opprette instans av klassen PResept
    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        else {
            PResept nyResept = new PResept(legemiddel, this, pasient);
            utskrevendeResepter.leggTil(nyResept);
            pasient.leggTilResept(nyResept);
            return nyResept;
        }
    }

    //Opprette instans av klassen BlaaResept
    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        }
        else {
            BlaaResept nyResept = new BlaaResept(legemiddel, this, pasient, reit);
            utskrevendeResepter.leggTil(nyResept);
            pasient.leggTilResept(nyResept);
            return nyResept;
        }
    }

    //teller antall utskrevende resepter paa nark legemiddler
    public int antallNarkotiskResept(){
        int teller=0;
        if (this.utskrevendeResepter.stoerrelse()>0){
            for (Resept resept : this.utskrevendeResepter){
                if (resept.hentLegemiddel() instanceof Narkotisk){
                    teller++;
                }
            }
        }
        return teller;
    }

    @Override
    public String toString() {
        return "Navn: " + navn;
    }
}