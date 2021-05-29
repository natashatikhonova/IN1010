import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//oppretter hashbeholder 
public class HashBeholder {
    ArrayList<HashMap<String, Subsekvens>> beholder = new ArrayList<HashMap<String, Subsekvens>>();
    Lock laas = new ReentrantLock();
    Condition ikkeTom = laas.newCondition();
    

    //tar imot posisjon og returnerer hashmap paa den oppgitte posisjonen
    public HashMap<String, Subsekvens> getPosisjon(int indeks){
        laas.lock();
        try{
            return beholder.get(indeks);
        } finally{
            laas.unlock();
        }
    }

    //tar imot hashmap og legger den til beholder
    public void leggTil(HashMap<String, Subsekvens> ny){
        laas.lock();
        try{
            ikkeTom.signalAll();
            beholder.add(ny);
        } finally{
            laas.unlock();
        }
    }

    //fjerner en hashmap fra beholder og returnerer den
    public HashMap<String, Subsekvens> taUt() throws InterruptedException{
        laas.lock();
        try{
            if(this.tom()){
                ikkeTom.await();
            }
            return beholder.remove(0);
        }  finally{
            laas.unlock();
        }
    }

    //returnerer stoerrelsen paa beholderen
    public int stoerrelse(){
        return beholder.size();
    }

    //metoden som slaar sammen to hashmapper
    synchronized HashMap<String, Subsekvens> flett(HashMap<String, Subsekvens> forste, HashMap<String, Subsekvens> andre){
        laas.lock();
        try{
            HashMap<String, Subsekvens> combined = new HashMap<String, Subsekvens>();
            Subsekvens hentet;
            for(Subsekvens sub : forste.values()){
                hentet = andre.remove(sub.getSubsekvens());

                if(hentet == null){
                    combined.put(sub.getSubsekvens(), sub);
                } else{
                    int ant = hentet.getAntall();
                    sub.add(ant);
                    combined.put(sub.getSubsekvens(), sub);
                }  
            }

            for(Subsekvens sub2 : andre.values()){
                combined.put(sub2.getSubsekvens(), sub2);
            }


            return combined;
        } finally{
            laas.unlock();
        }
    }

    //test metode
    public void test(){
        System.out.println("Lengden paa hashmappene:");
        for(HashMap<String, Subsekvens> hashmap : beholder){
            System.out.println("Stoerrelsen: "+hashmap.size());
        }
    }

    public boolean tom(){
        return beholder.isEmpty();
    }
}
