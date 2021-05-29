import java.util.ArrayList;

//subklassen hvitrute
public class HvitRute extends Rute{
    public HvitRute(int rad, int kol){
        super(rad, kol);
    }

    //ruten til tegn
    public char tilTegn(){
        return '.';
    }

    @Override
    public void gaa(Rute komFra, ArrayList<Tuppel> forrige){
        //oppretter tuppel med koordinatene til ruten
        Tuppel t = new Tuppel(kol, rad);
        //lager kopi av listen
        ArrayList<Tuppel> nyVei = new ArrayList<>(forrige);
        //legger tuppel til den nye listen
        nyVei.add(t);

        //kaller igjen paa gaa metoden fra denne ruten med den nye listen
        if(this.east!=komFra && this.east!=null){
            this.east.gaa(this, nyVei);
        }
        if(this.west!=komFra && this.west!=null){
            this.west.gaa(this,nyVei);
        }
        if(this.north!=komFra && this.north!=null){
            this.north.gaa(this, nyVei);
        }
        if(this.south!=komFra && this.south!=null){
            this.south.gaa(this, nyVei);
        }
    }
}
