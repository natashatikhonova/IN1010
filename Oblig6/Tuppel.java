public class Tuppel {

    //koordinatene
    public int x;
    public int y;

    //konstruktor
    public Tuppel(int x, int y){
        this.x=x;
        this.y=y;
    }

    //koordinatene til string
    @Override
    public String toString(){
        return "("+x+", "+y+")";
    }
}
