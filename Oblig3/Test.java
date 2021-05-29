

public class Test {
    public static void main(String[] args) {
        Liste<String> liste = new SortertLenkeliste<String>();
        liste.leggTil("1");
        liste.leggTil("2");
        liste.leggTil("4");
        liste.leggTil("3");
        System.out.println(liste.toString());
        liste.fjern();
        System.out.println(liste.toString());




        


    }
}
