//oppretter subsekvens
public class Subsekvens{
    private String subsekvens; 
    private int antall = 1;

    Subsekvens (String subsekvens){
        this.subsekvens = subsekvens;
    }

    //returnerer subsekvensen
    public String getSubsekvens(){
        return subsekvens;
    }

    //returnerer antall
    public int getAntall(){
        return antall;
    }

    //setter subsekvens til ny string
    public void setSubsekvens(String nySekvens){
        subsekvens = nySekvens;
    }

    //setter antall til ny antall
    public void setAntall(int nyAntall){
        antall = nyAntall;
    }

    //oeker antall med en
    public void addOne(){
        antall++;
    }

    //oeker antall med oppgitt int
    public void add(int oekning){
        antall+=oekning;
    }
}