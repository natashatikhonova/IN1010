public class Hovedprogram {
    public static void main(String[] args) throws UlovligUtskrift {
        Legesystem legesystem = new Legesystem();
        legesystem.lesFraFil("storFil.txt");
        legesystem.hovedmeny();
        
    }
    
}
