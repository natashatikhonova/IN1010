public class Person{
  private Bil3 eidBil;

  public Person(Bil3 bil){
    eidBil = bil;
  }
  public void skrivUt(){
    String nummer = eidBil.hentNummer();
    System.out.println("Nummeret paa bilen din er "+nummer);
  }
}
