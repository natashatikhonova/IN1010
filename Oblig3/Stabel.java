//lager en klasse Stabel som arver fra Lenkeliste
public class Stabel<T> extends Lenkeliste<T>{
    
    //siden metoden leggTil(x) i superklassen legger x til slutten av listen, 
    //kaller vi på den samme metoden for å få elementet på slutten av listen
    public void leggPaa(T x){
        this.leggTil(x);
    }

    //lager en metode som skal fjerne siste elementet i listen
    public T taAv(){
        //finner siste elementet i listen og lagrer data fra det
        T siste = this.hent(this.stoerrelse()-1);
        //fjerner elementet fra listen
        this.fjern(this.stoerrelse()-1);
        //returner data som ble fjernet
        return siste;
    }
}
