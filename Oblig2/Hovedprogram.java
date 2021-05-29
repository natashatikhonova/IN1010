public class Hovedprogram {
    public static void main(String[] args) {
        //oppretter test Legemiddler objekter 
        Narkotisk narkotisk = new Narkotisk("Malfin", 265, 7.5, 60);
        Vanedannende vanedannende = new Vanedannende("Paralgin forte", 100, 3.5, 30);
        Vanlig vanlig = new Vanlig("Ibux", 80, 1.5);

        //oppretter test Leger objekter
        Lege lege = new Lege("Anna");
        Spesialist spesialist = new Spesialist("Olav", "A4F5U7");

        //oppretter testeobjekter av hver subklasse
        BlaaResept blaaResept = new BlaaResept(vanedannende, lege, 2, 4);
        HvitResept hvitResept = new HvitResept(vanlig, lege, 5, 3);
        PResept pResept = new PResept(vanlig, spesialist, 8);
        MilitarResept militarResept = new MilitarResept(narkotisk, spesialist, 11, 1);

        //skriver ut informasjon om hver resept
        System.out.println(blaaResept.toString()+"\n");
        System.out.println(hvitResept.toString()+"\n");
        System.out.println(militarResept.toString()+"\n");
        System.out.println(pResept.toString());

    }
}
