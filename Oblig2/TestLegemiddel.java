public class TestLegemiddel {
    public static void main(String[] args) {
        //oppretter teste objekter av hver subklasse
        Narkotisk narkotisk = new Narkotisk("Malfin", 265, 7.5, 60);
        Vanedannende vanedannende = new Vanedannende("Paralgin forte", 100, 3.5, 30);
        Vanlig vanlig = new Vanlig("Ibux", 80, 1.5);

        //tester Narkotisk Legemiddel
        System.out.println("Tester Narkotisk Legemiddel:");
        //hentNavn
        if (narkotisk.hentNavn()== "Malfin"){
            System.out.println("hentNavn: riktig");
        } else{
            System.out.println("hentNavn: feil");
        }
        //hentId
        if (narkotisk.hentId()== 1){
            System.out.println("hentId: riktig");
        } else{
            System.out.println("hentId: feil");
        }

        //hentVirkestoff
        if (narkotisk.hentVirkestoff()== 7.5){
            System.out.println("hentVirkestoff: riktig");
        } else{
            System.out.println("hentVirkestoff: feil");
        }

        //hentNarkotiskStyrke
        if (narkotisk.hentNarkotiskStyrke()== 60){
            System.out.println("hentNarkotiskStyrke: riktig");
        } else{
            System.out.println("hentNarkotiskStyrke: feil");
        }

        //hentPris
        if (narkotisk.hentPris()== 265){
            System.out.println("hentPris: riktig");
        } else{
            System.out.println("hentPris: feil");
        }

        //settNyPris
        narkotisk.settNyPris(280);
        if (narkotisk.hentPris()== 280){
            System.out.println("settNyPris: riktig");
        } else{
            System.out.println("settNyPris: feil");
        }

        //tester Vanedannende Legemiddel
        System.out.println("Tester Vanedannende Legemiddel:");

        //hentNavn
        if (vanedannende.hentNavn()== "Paralgin forte"){
            System.out.println("hentNavn: riktig");
        } else{
            System.out.println("hentNavn: feil");
        }
        //hentId
        if (vanedannende.hentId()== 2){
            System.out.println("hentId: riktig");
        } else{
            System.out.println("hentId: feil");
        }

        //hentVirkestoff
        if (vanedannende.hentVirkestoff()== 3.5){
            System.out.println("hentVirkestoff: riktig");
        } else{
            System.out.println("hentVirkestoff: feil");
        }

        //hentVanedannendeStyrke
        if (vanedannende.hentVanedannendeStyrke()== 30){
            System.out.println("hentVanedannendeStyrke: riktig");
        } else{
            System.out.println("hentVanedannendeStyrke: feil");
        }

        //hentPris
        if (vanedannende.hentPris()== 100){
            System.out.println("hentPris: riktig");
        } else{
            System.out.println("hentPris: feil");
        }

        //settNyPris
        vanedannende.settNyPris(120);
        if (vanedannende.hentPris()== 120){
            System.out.println("settNyPris: riktig");
        } else{
            System.out.println("settNyPris: feil");
        }

        //tester Vanlig Legemiddel
        System.out.println("Tester Vanlig Legemiddel:");

        //hentNavn
        if (vanlig.hentNavn()== "Ibux"){
            System.out.println("hentNavn: riktig");
        } else{
            System.out.println("hentNavn: feil");
        }
        //hentId
        if (vanlig.hentId()== 3){
            System.out.println("hentId: riktig");
        } else{
            System.out.println("hentId: feil");
        }

        //hentVirkestoff
        if (vanlig.hentVirkestoff()== 1.5){
            System.out.println("hentVirkestoff: riktig");
        } else{
            System.out.println("hentVirkestoff: feil");
        }

        //hentPris
        if (vanlig.hentPris()== 80){
            System.out.println("hentPris: riktig");
        } else{
            System.out.println("hentPris: feil");
        }

        //settNyPris
        vanlig.settNyPris(90);
        if (vanlig.hentPris()== 90){
            System.out.println("settNyPris: riktig");
        } else{
            System.out.println("settNyPris: feil");
        }

    }
}
