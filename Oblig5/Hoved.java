import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Hoved {

    //metadata fil
    static String metadataFil = "Data/metadata.csv";

    public static void main(String[] args) {
        HashBeholder trueBeholder = new HashBeholder();
        HashBeholder falseBeholder = new HashBeholder();
        ArrayList<String> alleFiler = new ArrayList<String>();
        int antallTraader = 0;

        //tar imot antall traader fra args
        try{
            antallTraader = Integer.parseInt(args[0]);
            if(antallTraader<=1){
                System.out.println("Oppgi gyldig antall traader!");
                System.exit(1);
            }
        }catch(Exception e){
            System.out.println("Oppgi antall traader!");
            System.exit(1);
        }
        
        //variabler som brukes videre
        int indeks = 0;
        int antallFiler = 0;
        int filerPerHverTraad = 1;
        String linje;
        Scanner sc = null;
        int trueTraader = 0;
        int falseTraader = 0;
        int rest = 0;
        int restFalse = 0;
        int trueAntallBehandlinger = 0;
        int falseAntallBehandlinger = 0;
        int trueBehandlingerPerTraad = 0;
        int falseBehandlingerPerTraad = 0;
        long time = System.currentTimeMillis();


        try{
            sc = new Scanner(new File(metadataFil));
            System.out.println("Velkommen til Systemet!");
            System.out.println("Vennligst vent! Vi lagrer dataen din...");
        } catch(FileNotFoundException e){
            System.out.println("Fant ikke filen:(");
            System.exit(1);
        }

        //legger linjene fra filen til en array
        sc.nextLine();
        while(sc.hasNextLine()){
            linje = sc.nextLine();
            alleFiler.add(linje);
            antallFiler++;
        }

        
        CountDownLatch count = new CountDownLatch(antallFiler);

        //oppretter traader for behandling av filene
        for(int i = 0; i<antallFiler; i++){
            int startIndeks = indeks;
            int sluttIndeks = indeks+filerPerHverTraad;
            indeks+=filerPerHverTraad;
            Behandle behandle = new Behandle(alleFiler, trueBeholder, falseBeholder, count, startIndeks, sluttIndeks);
            Thread traad = new Thread(behandle);
            traad.start();
        }

        try{
            count.await();
        } catch(InterruptedException e){
            System.out.println(e);
        }

        System.out.println("Antall HashMaper i True beholder: ");
        System.out.println(trueBeholder.stoerrelse());
        System.out.println("Antall HashMaper i False beholder: ");
        System.out.println(falseBeholder.stoerrelse());
    

        //regner ut antall traader for hver beholder
        if (antallTraader%2 == 0){
            trueTraader=falseTraader=antallTraader/2;
        } else{
            trueTraader = (antallTraader - 1)/2;
            falseTraader = antallTraader - trueTraader;
        }
        trueAntallBehandlinger = trueBeholder.stoerrelse()-1;
        falseAntallBehandlinger = falseBeholder.stoerrelse()-1;

        if(trueAntallBehandlinger%trueTraader == 0){
            trueBehandlingerPerTraad = trueAntallBehandlinger/trueTraader;
        } else{
            rest = trueAntallBehandlinger%trueTraader;
            trueBehandlingerPerTraad = (trueAntallBehandlinger - rest)/trueTraader;
        }

        if(falseAntallBehandlinger%falseTraader == 0){
            falseBehandlingerPerTraad = falseAntallBehandlinger/falseTraader;
        } else{
            restFalse = falseAntallBehandlinger%falseTraader;
            falseBehandlingerPerTraad = (falseAntallBehandlinger - rest)/falseTraader;
        }
        
        
        CountDownLatch trueCount = new CountDownLatch(trueAntallBehandlinger);
        CountDownLatch falseCount = new CountDownLatch(falseAntallBehandlinger);
        ArrayList<Thread> alleThreads = new ArrayList<Thread>();

        //oppretter traader og legger dem til arraylist
        for(int i = 0; i<trueTraader; i++){
            Combine trueCombine;
            if(i == 0){
                trueCombine = new Combine(trueBeholder, trueCount, trueBehandlingerPerTraad+rest);
            } else{
                trueCombine = new Combine(trueBeholder, trueCount, trueBehandlingerPerTraad);
            }
            
            Thread trueTraad = new Thread(trueCombine);
            alleThreads.add(trueTraad);
        }
        for(int i = 0; i<falseTraader; i++){
            Combine falseCombine;
            if(i == 0){
                falseCombine = new Combine(falseBeholder, falseCount, falseBehandlingerPerTraad+restFalse);
            } else{
                falseCombine = new Combine(falseBeholder, falseCount, falseBehandlingerPerTraad);
            }
            
            Thread falseTraad = new Thread(falseCombine);
            alleThreads.add(falseTraad);
        }
        //starter traader
        for(Thread t : alleThreads){
            t.start();
        }


        try{
            trueCount.await();
        } catch(InterruptedException e){
            System.out.println(e);
        }

        try{
            falseCount.await();
        } catch(InterruptedException e){
            System.out.println(e);
        }

        System.out.println("Antall True subsekvenser etter fletting: ");
        trueBeholder.test();
        System.out.println("Antall False subsekvenser etter fletting: ");
        falseBeholder.test();
      
        System.out.println("Subsekvens:\tAntall True:\tAntall false:\tForskjell:");     
        //tester
        for(Subsekvens trueSub : trueBeholder.getPosisjon(0).values()){
            for(Subsekvens falseSub : falseBeholder.getPosisjon(0).values()){
                if (trueSub.getSubsekvens().equals(falseSub.getSubsekvens())){
                    if(trueSub.getAntall()-falseSub.getAntall()>=5){
                        int forskjell = trueSub.getAntall()-falseSub.getAntall();
                        System.out.println(trueSub.getSubsekvens()+"\t\t"+trueSub.getAntall()+"\t\t"+falseSub.getAntall()+"\t\t"+forskjell);
                    } 
                } 
            }
        }

        System.out.println("Total tid: "+(System.currentTimeMillis() - time)/1000 +"s");
    }
    
}
