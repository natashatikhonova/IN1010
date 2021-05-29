import java.util.ArrayList;

//subklassen aapning
public class Aapning extends HvitRute{
    public Aapning(int rad, int kol){
        super(rad, kol);
    }

    
    @Override
    public void gaa(Rute komFra, ArrayList<Tuppel> forrige){
        //lager tuppel med koordinatene til ruten
        Tuppel t = new Tuppel(kol, rad);
        //lager en kopi av listen
        ArrayList<Tuppel> nyVei = new ArrayList<>(forrige);
        //legger tuppel til den nye listen
        nyVei.add(t);
        //legger den nye listen til listen av alle utveier
        this.labirynt.utveier.add(nyVei);
    }
}
