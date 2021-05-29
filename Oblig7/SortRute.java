import java.util.ArrayList;

//subklassen sortrute
public class SortRute extends Rute{
    public SortRute(int rad, int kol){
        super(rad, kol);
    }

    //returnerer tegn
    public char tilTegn(){
        return '#';
    }

    
    @Override
    public void gaa(Rute komFra, ArrayList<Tuppel> forrige){
        return;
    }
}
