public class TestResepter {
    public static void main(String[] args) {
        //oppretter test Legemiddler objekter (samme som i TestLegemiddel.java)
        Narkotisk narkotisk = new Narkotisk("Malfin", 265, 7.5, 60);
        Vanedannende vanedannende = new Vanedannende("Paralgin forte", 100, 3.5, 30);
        Vanlig vanlig = new Vanlig("Ibux", 80, 1.5);

        //oppretter test Leger objekter
        Lege lege1 = new Lege("Anna");
        Lege lege2 = new Lege("Olav");

        //oppretter testeobjekter av hver subklasse
        BlaaResept blaaResept = new BlaaResept(vanedannende, lege1, 2, 4);
        HvitResept hvitResept = new HvitResept(vanlig, lege1, 5, 3);
        PResept pResept = new PResept(vanlig, lege2, 8);
        MilitarResept militarResept = new MilitarResept(narkotisk, lege2, 11, 1);

        //tester BlaaResept
        System.out.println("Tester BlaaResept:");

        //hentId
        if (blaaResept.hentId()==1){
            System.out.println("hentId: riktig");
        } else{
            System.out.println("hentId: feil");
        }

        //hentLegemiddel
        if (blaaResept.hentLegemiddel().hentNavn()=="Paralgin forte"){
            System.out.println("hentLegemiddel: riktig");
        } else{
            System.out.println("hentLegemiddel: feil");
        }

        //hentLege
        if (blaaResept.hentLege().hentNavn()=="Anna"){
            System.out.println("hentLege: riktig");
        } else{
            System.out.println("hentLege: feil");
        }

        //hentPasientId
        if (blaaResept.hentPasientId()==2){
            System.out.println("hentPasientId: riktig");
        } else{
            System.out.println("hentPasientId: feil");
        }

        //hentReit
        if (blaaResept.hentReit()==4){
            System.out.println("hentReit: riktig");
        } else{
            System.out.println("hentReit: feil");
        }

        //bruk
        if(blaaResept.bruk()== true && blaaResept.hentReit()==3){
            System.out.println("bruk: riktig");
        } else{
            System.out.println("bruk: false");
        }

        //farge
        if (blaaResept.farge()=="Blaa"){
            System.out.println("farge: riktig");
        } else{
            System.out.println("farge: feil");
        }

        //prisAaBetale
        if(blaaResept.prisAaBetale()==25){
            System.out.println("prisAaBetale: riktig");
        } else{
            System.out.println("prisAaBetale: feil");
        }

        //tester HvitResept
        System.out.println("Tester HvitResept:");

        //hentId
        if (hvitResept.hentId()==2){
            System.out.println("hentId: riktig");
        } else{
            System.out.println("hentId: feil");
        }

        //hentLegemiddel
        if (hvitResept.hentLegemiddel().hentNavn()=="Ibux"){
            System.out.println("hentLegemiddel: riktig");
        } else{
            System.out.println("hentLegemiddel: feil");
        }

        //hentLege
        if (hvitResept.hentLege().hentNavn()=="Anna"){
            System.out.println("hentLege: riktig");
        } else{
            System.out.println("hentLege: feil");
        }

        //hentPasientId
        if (hvitResept.hentPasientId()==5){
            System.out.println("hentPasientId: riktig");
        } else{
            System.out.println("hentPasientId: feil");
        }

        //hentReit
        if (hvitResept.hentReit()==3){
            System.out.println("hentReit: riktig");
        } else{
            System.out.println("hentReit: feil");
        }

        //bruk
        if(hvitResept.bruk()== true && hvitResept.hentReit()==2){
            System.out.println("bruk: riktig");
        } else{
            System.out.println("bruk: false");
        }

        //farge
        if (hvitResept.farge()=="Hvit"){
            System.out.println("farge: riktig");
        } else{
            System.out.println("farge: feil");
        }

        //prisAaBetale
        if(hvitResept.prisAaBetale()==80){
            System.out.println("prisAaBetale: riktig");
        } else{
            System.out.println("prisAaBetale: feil");
        }
        //tester PResept
        System.out.println("Tester PResept:");

        //hentId
        if (pResept.hentId()==3){
            System.out.println("hentId: riktig");
        } else{
            System.out.println("hentId: feil");
        }

        //hentLegemiddel
        if (pResept.hentLegemiddel().hentNavn()=="Ibux"){
            System.out.println("hentLegemiddel: riktig");
        } else{
            System.out.println("hentLegemiddel: feil");
        }

        //hentLege
        if (pResept.hentLege().hentNavn()=="Olav"){
            System.out.println("hentLege: riktig");
        } else{
            System.out.println("hentLege: feil");
        }

        //hentPasientId
        if (pResept.hentPasientId()==8){
            System.out.println("hentPasientId: riktig");
        } else{
            System.out.println("hentPasientId: feil");
        }

        //hentReit
        if (pResept.hentReit()==3){
            System.out.println("hentReit: riktig");
        } else{
            System.out.println("hentReit: feil");
        }

        //bruk
        if(pResept.bruk()== true && pResept.hentReit()==2){
            System.out.println("bruk: riktig");
        }  else{
            System.out.println("bruk: false");
        }

        //farge
        if (pResept.farge()=="Hvit"){
            System.out.println("farge: riktig");
        } else{
            System.out.println("farge: feil");
        }

        //prisAaBetale
        if(pResept.prisAaBetale()== 0){
            System.out.println("prisAaBetale: riktig");
        } else{
            System.out.println("prisAaBetale: feil");
        }

        //tester MilitarResept
        System.out.println("Tester MilitarResept:");

        //hentId
        if (militarResept.hentId()==4){
            System.out.println("hentId: riktig");
        } else{
            System.out.println("hentId: feil");
        }

        //hentLegemiddel
        if (militarResept.hentLegemiddel().hentNavn()=="Malfin"){
            System.out.println("hentLegemiddel: riktig");
        } else{
            System.out.println("hentLegemiddel: feil");
        }

        //hentLege
        if (militarResept.hentLege().hentNavn()=="Olav"){
            System.out.println("hentLege: riktig");
        } else{
            System.out.println("hentLege: feil");
        }

        //hentPasientId
        if (militarResept.hentPasientId()==11){
            System.out.println("hentPasientId: riktig");
        } else{
            System.out.println("hentPasientId: feil");
        }

        //hentReit
        if (militarResept.hentReit()==1){
            System.out.println("hentReit: riktig");
        } else{
            System.out.println("hentReit: feil");
        }

        //bruk
        if(militarResept.bruk()== true && militarResept.hentReit()==0){
            System.out.println("bruk: riktig");
        } else{
            System.out.println("bruk: false");
        }

        //farge
        if (militarResept.farge()=="Hvit"){
            System.out.println("farge: riktig");
        } else{
            System.out.println("farge: feil");
        }

        //prisAaBetale
        if(militarResept.prisAaBetale()==0){
            System.out.println("prisAaBetale: riktig");
        } else{
            System.out.println("prisAaBetale: feil");
        }

        //test toString
        System.out.println("Tester toString():");
        System.out.println(blaaResept.toString());
        System.out.println(hvitResept.toString());
        System.out.println(pResept.toString());
        System.out.println(militarResept.toString());

    }
}
