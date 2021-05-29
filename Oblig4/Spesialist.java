/*
Subklasse av Lege som implementerer grensesnittet Godkjenningsfritak.
**/

public class Spesialist extends Lege implements Godkjenningsfritak {
    

    //Tar inn konstruktoren til Lege, i tillegg til en kontroll ID.
    public Spesialist(String navn, String kontrollID) {
        super(navn, kontrollID);    
    }

    

    @Override
    //Opprette instans av klassen HvitResept
    public HvitResept skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        HvitResept nyResept = new HvitResept(legemiddel, this, pasient, reit);
        utskrevendeResepter.leggTil(nyResept);
        pasient.leggTilResept(nyResept);
        return nyResept;
    }

    @Override
    //Opprette instans av klassen MilitaerResept
    public MilitaerResept skrivMilitaerResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        MilitaerResept nyResept = new MilitaerResept(legemiddel, this, pasient, reit);
        utskrevendeResepter.leggTil(nyResept);
        pasient.leggTilResept(nyResept);
        return nyResept;
    }

    @Override
    //Opprette instans av klassen PResept
    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        PResept nyResept = new PResept(legemiddel, this, pasient);
        utskrevendeResepter.leggTil(nyResept);
        pasient.leggTilResept(nyResept);
        return nyResept;
    }

    @Override
    //Opprette instans av klassen BlaaResept
    public BlaaResept skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        BlaaResept nyResept = new BlaaResept(legemiddel, this, pasient, reit);
        utskrevendeResepter.leggTil(nyResept);
        pasient.leggTilResept(nyResept);
        return nyResept;
    }

    @Override
    public String toString() {
        return "Navn: " + navn + "\n" + "Kontroll ID: " + kontrollID;
    }
}