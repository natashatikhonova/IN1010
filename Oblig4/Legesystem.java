
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Legesystem {
    //Oppretter lister over leger, pasienter, legemidler og resepter.
    protected SortertLenkeliste<Lege> alleLeger;
    protected Liste<Pasient> allePasienter;
    protected Liste<Legemiddel> alleLegemidler;
    protected Liste<Resept> alleResepter;

    public Legesystem(){
        allePasienter = new Lenkeliste<Pasient>();
        alleLegemidler = new Lenkeliste<Legemiddel>();
        alleLeger = new SortertLenkeliste<Lege>();
        alleResepter = new Lenkeliste<Resept>();
    }

    //leser info om legemiddel, sjekker om alt er gyldig, og oppretter objekt
    public void behandlerLegemidler(String linje){
        String navn = linje.split(",")[0];
        int pris = 0;
        double virkestoff = 0;
        int styrke=0;
        boolean ugyldig = false;
        try {
            pris = Integer.parseInt(linje.split(",")[2]);
        } catch (NumberFormatException e){
            ugyldig = true;
        }

            try {
                virkestoff = Double.parseDouble(linje.split(",")[3]);
            } catch(NumberFormatException e){
                ugyldig = true;
            }

                try {
                    if (linje.split(",")[1].equals("narkotisk")){
                        styrke = Integer.parseInt(linje.split(",")[4]);
                        Legemiddel narkotisk = new Narkotisk(navn, pris, virkestoff, styrke);
                        alleLegemidler.leggTil(narkotisk);
                        
        
                    } else if (linje.split(",")[1].equals("vanedannende")){
                        styrke = Integer.parseInt(linje.split(",")[4]);
                        Legemiddel vanedannende = new Vanedannende(navn, pris, virkestoff, styrke);
                        alleLegemidler.leggTil(vanedannende);
                        
        
                    } else if (linje.split(",")[1].equals("vanlig")){
                        Legemiddel vanlig = new Vanlig(navn, pris, virkestoff);
                        alleLegemidler.leggTil(vanlig);
                        
                    } else {
                        ugyldig = true;
                    }
                } catch(NumberFormatException e){
                    ugyldig=true;
                }  
                    
    }
    
    //leser info om resept, sjekker om alt er gyldig, og oppretter objekt
    public void behandlerResepter(String linje){
        boolean ugyldig = false;
        int lmlID = 0;
        Legemiddel lml = null;
        String legeNavn = linje.split(",")[1];
        int pasID = 0;
        String type = linje.split(",")[3].toLowerCase();
        try{
            lmlID = Integer.parseInt(linje.split(",")[0]);
        } catch(NumberFormatException e){
            ugyldig  = true;
        }
        try{
            lml = alleLegemidler.hent(lmlID-1);
        } catch(UgyldigListeIndeks e){
            ugyldig = true;
        } 

        try {
            pasID = Integer.parseInt(linje.split(",")[2]);
        } catch(NumberFormatException e){
            ugyldig = true;
        }
        
        if(!ugyldig){
            try{
                if (type.equals("hvit")){
                    int reit = Integer.parseInt(linje.split(",")[4]);
                    try{
                            for (Lege lege : alleLeger){
                                if(lege.navn.equals(legeNavn)){
                                    try {
                                        alleResepter.leggTil(lege.skrivHvitResept(lml, allePasienter.hent(pasID-1), reit));
                                    } catch(UgyldigListeIndeks e){
                                        ugyldig = true;
                                    }
                                }    
                            }
                         
                    } catch(UlovligUtskrift e){
                        ugyldig = true;
                    }
                    
                } else if (type.equals("blaa")){
                    int reit = Integer.parseInt(linje.split(",")[4]);
                    try{
                        for (Lege lege : alleLeger){
                            if(lege.navn.equals(legeNavn)){
                                try{
                                    alleResepter.leggTil(lege.skrivBlaaResept(lml, allePasienter.hent(pasID-1), reit));
                                } catch(UgyldigListeIndeks e){
                                    ugyldig = true;
                                }
                            }
                        }
                        
                    } catch(UlovligUtskrift e){
                        ugyldig = true;
                    }
                } else if(type.equals("militaer")){
                    int reit = Integer.parseInt(linje.split(",")[4]);
                    try{
                        for (Lege lege : alleLeger){
                            if(lege.navn.equals(legeNavn)){
                                try{
                                    alleResepter.leggTil(lege.skrivMilitaerResept(lml, allePasienter.hent(pasID-1), reit));
                                } catch(UgyldigListeIndeks e){
                                    ugyldig = true;
                                }
                            }
                        }
                        
                    } catch(UlovligUtskrift e){
                        ugyldig = true;
                    }
                } else if (type.equals("p")){
                    try{
                        for (Lege lege : alleLeger){
                            if(lege.navn.equals(legeNavn)){
                                try{
                                    alleResepter.leggTil(lege.skrivPResept(lml, allePasienter.hent(pasID-1)));
                                } catch(UgyldigListeIndeks e){
                                    ugyldig = true;
                                }
                            }
                        }
                        
                    } catch(UlovligUtskrift e){
                        ugyldig = true;
                    }
                } else {
                    ugyldig = true;
                }
            } catch (NumberFormatException e){
                ugyldig = true;
            }
        }
                    
    }
    //leser data fra fil
    public void lesFraFil(String filnavn) {
        

        File fil = new File(filnavn);
        Scanner valgtFil = null;
        boolean behandlerPasienter = false;
        boolean behandlerLegemidler = false;
        boolean behandlerLeger = false;
        boolean behandlerResepter = false;

        try {
            
            valgtFil = new Scanner(fil);
        }
        catch(FileNotFoundException exception){
            System.out.println("Fant ikke filen");
            System.exit(1);
        }
        //vurderer hvilke objekter skal opprettes ut fra linje som starter med #
        while (valgtFil.hasNextLine()) {
            String linje = valgtFil.nextLine();
            if (linje.startsWith("#")){
                if (linje.contains("Pasienter")){
                    behandlerPasienter = true;
                    
                } else if (linje.contains("Legemidler")){
                    behandlerLegemidler = true;
                    
                } else if (linje.contains("Leger")){
                    behandlerLeger = true;
                    
                } else if (linje.contains("Resepter")){
                    behandlerResepter = true; 
                }
            } else{
                //gaar gjennom linjer og oppretter objekter
                if (behandlerResepter){
                    behandlerResepter(linje);

                } else if (behandlerLeger){
                    String navn = linje.split(",")[0];
                    String id = linje.split(",")[1];

                    if (id.equals("0")){
                        Lege lege = new Lege(navn,"0");
                        alleLeger.leggTil(lege);
                        
                    } else {
                        Lege spesialist = new Spesialist(navn, id);
                        alleLeger.leggTil(spesialist);
                        
                    }
    
                } else if(behandlerLegemidler){
                    behandlerLegemidler(linje);
                } else if(behandlerPasienter){
                    String navn = linje.split(",")[0];
                    String fodselsnr = linje.split(",")[1];
                    Pasient pasient = new Pasient(navn, fodselsnr);
                    allePasienter.leggTil(pasient);
                    
                }
            }    
        }

        valgtFil.close();
    }
    
    //gaar gjennom 4 store lister over helesystemet, og skriver ut objektene fra listene
    public void skrivOversikt(){
        System.out.println("Oversikt over Legesystemet:");
        if(allePasienter.stoerrelse()>0){
            System.out.println("Pasienter:\n\n");
            for (Pasient pasient : allePasienter){
                System.out.println((pasient.toString())+"\n");
            }
        } else {
            System.out.println("Det finnes ingen pasienter i systemet.");
        }

        if (alleLegemidler.stoerrelse()>0){
            System.out.println("Legemidler:\n\n");
            for (Legemiddel legemiddel : alleLegemidler){
                System.out.println((legemiddel.toString())+"\n");
            }
        } else {
            System.out.println("Det finnes ingen legemidler i systemet.");
        }

        if (alleLeger.stoerrelse()>0){
            System.out.println("Leger\n\n");
            for (Lege lege : alleLeger){
                System.out.println((lege.toString())+"\n");
            }
        } else{
            System.out.println("Det finnes ingen leger i systemet.");
        }

        if(alleResepter.stoerrelse()>0){
            System.out.println("Resepter\n\n");
            for (Resept resept : alleResepter){
                System.out.println((resept.toString())+"\n");
            }
        } else{
            System.out.println("Det finnes ingen resepter i systemet.");
        }

        avslutt();
    }

    //bruker interkasjon: bruker kan velge hva den skal gjore i systemet. herfra kalles det andre metoder
    public void hovedmeny(){
        System.out.println("Velkommen til Legesystemet!\nVelg en av folgende alternativer:");
        System.out.println("0: Skriv ut oversikt over Legesystemet");
        System.out.println("1: Opprett og legg til nye elementer");
        System.out.println("2: Bruk resept");
        System.out.println("3: Skriv ut statistikk");
        System.out.println("4: Skriv data til en fil");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        boolean gyldig1=false;
        boolean gyldig=false;
        int valg = 0;
        while(!gyldig1){
            try{
                valg = Integer.parseInt(input);
                gyldig1 = true;
            } catch(NumberFormatException e){
                System.out.println("Ugyldig input!Oppgi et tall 0-4:");
                input= scanner.next();
            }
        }
        
        while (!gyldig){
            if (valg==0){
                this.skrivOversikt();
                gyldig = true;
            } else if (valg == 1){
                this.opprett();
                gyldig = true;
            } else if (valg == 2){
                this.brukResept();
                gyldig = true;
            } else if (valg == 3){
                this.statistikk();
                gyldig = true;
            } else if (valg == 4){
                this.skrivTilFil("fil.txt");
                gyldig = true;
            } else{
                System.out.println("Ugyldig input!Oppgi et tall 0-4:");
                valg = scanner.nextInt();
            }
        }
    }

    //bruker velger en pasient, deretter en resept ut fra pasientens resepter. resepten blir brukt (eller ikke om 0 reit igjen), og antall reit vises
    public void brukResept(){
        System.out.println("Hvilken pasient vil du se resepter for? ");
        int pasientNr=0;
        for (Pasient pasient : allePasienter){
            System.out.println(pasientNr+":" + pasient.navn+"(fnr: "+pasient.fodselsnr+")");
            pasientNr++;
        }
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        int posisjon = 0;
        boolean gyldigPas = false;
        while(!gyldigPas){
            try {
                posisjon = Integer.parseInt(input);
                try {
                    System.out.println("Valgt pasient: " + allePasienter.hent(posisjon).toString());
                    gyldigPas=true;
                } catch(UgyldigListeIndeks e){
                    System.out.println("Ugyldig input! Oppgi et fra 0 til "+ (pasientNr-1)+": ");
                    input = scanner.next();
                }

            } catch(NumberFormatException e){
                System.out.println("Ugyldig input! Oppgi et tall: ");
                input = scanner.next();
            }
        }
        
        if(allePasienter.hent(posisjon).resepter.stoerrelse()<1){
            System.out.println("Pasienten har ingen gyldige resepter.");
        } else{
            System.out.println("Hvilken resept vil du bruke? ");
            int reseptNr = 0;
            for (Resept r : allePasienter.hent(posisjon).hentResepter()){
                System.out.println(reseptNr+": "+r.hentLegemiddel().hentNavn()+"("+r.hentReit()+" reit)");
                reseptNr++;
            }
            input = scanner.next();
            int valgtResept = 0;
            boolean gyldigRes = false;
            while(!gyldigRes){
                try {
                    valgtResept = Integer.parseInt(input);
                    try {
                        allePasienter.hent(posisjon).brukResept(valgtResept);
                        gyldigRes = true;
                    } catch (UgyldigListeIndeks e){
                        System.out.println("Ugyldig input! Oppgi et fra 0 til "+ (reseptNr-1)+": ");
                        input = scanner.next();
                    }

                } catch(NumberFormatException e){
                    System.out.println("Ugyldig input! Oppgi et tall: ");
                    input = scanner.next();
                }
            }
        }
        

        

        avslutt();
  
    }

    //gaar gjennom alle listene i systemet (hver objekt i hver liste), og legger den til en fil
    public void skrivTilFil(String utfil){
        File fil = new File(utfil);
        try{
            PrintWriter pw = new PrintWriter(fil);
            pw.append("# Pasienter (navn, fnr)\n");
            for (Pasient pasient : allePasienter){
                pw.append(pasient.navn+","+pasient.fodselsnr+"\n");
            }
            pw.append("# Legemidler (navn,type,pris,virkestoff,[styrke])\n");
            for (Legemiddel legemiddel : alleLegemidler){
                String navn = legemiddel.hentNavn();
                int pris = legemiddel.hentPris();
                double virkestoff = legemiddel.hentVirkestoff();
                int styrke = legemiddel.hentStyrke();
        
                if (legemiddel instanceof Narkotisk){
                    pw.append(navn+",narkotisk,"+pris+","+virkestoff+","+styrke+"\n");
                    
                } else if (legemiddel instanceof Vanedannende){
                    pw.append(navn+",vanedannende,"+pris+","+virkestoff+","+styrke+"\n");
                    
                } else if (legemiddel instanceof Vanlig){
                    pw.append(navn+",vanlig,"+pris+","+virkestoff+"\n");
                }
                

            }
            pw.append("# Leger (navn,kontrollid / 0 hvis vanlig lege)\n");
            for (Lege lege : alleLeger){
                pw.append(lege.navn+","+lege.kontrollID+"\n");
            }
            pw.append("# Resepter (legemiddelNummer,legeNavn,pasientID,type,[reit])\n");
            for (Resept resept : alleResepter){
                int lmlNr = resept.hentLegemiddel().hentId();
                String legeNavn = resept.hentLege().hentNavn();
                int pasId= resept.hentPasient().id;
                int reit = resept.hentReit();
                if (resept instanceof PResept){
                    pw.append(lmlNr+","+legeNavn+","+pasId+",p\n");
                } else if (resept instanceof MilitaerResept){
                    pw.append(lmlNr+","+legeNavn+","+pasId+",millitaer,"+reit+"\n");
                } else if (resept instanceof BlaaResept){
                    pw.append(lmlNr+","+legeNavn+","+pasId+",blaa,"+reit+"\n");
                } else if (resept instanceof HvitResept){
                    pw.append(lmlNr+","+legeNavn+","+pasId+",hvit,"+reit+"\n");
                }

            }
            pw.close();
            System.out.println("Dataen er innlest, og filen er lagret.");
        } catch(FileNotFoundException e){
            System.out.println("Det skjedde en feil med skriving til fil.");
        }
        avslutt();
    }

    //gaar gjennom lister for aa oppgi statistikk over systemet
    public void statistikk(){
        //antall vanedannende resepter
        int tellerVanedannende = 0;
        for (Resept resept : alleResepter){
            if (resept.hentLegemiddel() instanceof Vanedannende){
                tellerVanedannende++;
            }
        }

        //antall narkotiske resepter
        int tellerNarkotisk = 0;
        for (Resept resept : alleResepter){
            if (resept.hentLegemiddel() instanceof Narkotisk){
                tellerNarkotisk++;
            }
        }

        SortertLenkeliste<Lege> misbrukLeger = new SortertLenkeliste<Lege>();
        //leger har utskrevet mer enn 1 nark resept
        for (Lege lege : alleLeger){
            if (lege.antallNarkotiskResept() > 0){
                misbrukLeger.leggTil(lege);
            }    
        }

        Liste<Pasient> misbrukPasienter = new Lenkeliste<Pasient>();
        //pasienter som har mer enn 1 nark resept
        for (Pasient pasient : allePasienter){
            if(pasient.antallNarkotiskResept()>0){
                misbrukPasienter.leggTil(pasient);
            }   
        }
        

        System.out.println("Totalt antall utskrevne resepter på vanedannende legemidler: "+tellerVanedannende);
        System.out.println("Totalt antall utskrevne resepter på narkotiske legemidler: "+tellerNarkotisk);
        System.out.println("Leger som har skrevet ut minst en resept på narkotiske legemidler");
        for (Lege lege : misbrukLeger){
            System.out.println(lege.navn+": "+lege.antallNarkotiskResept());
        }
        System.out.println("Pasienter som har minst en gyldig resept på narkotiske legemidler");
        for (Pasient pasient : misbrukPasienter){
            System.out.println(pasient.navn+": "+pasient.antallNarkotiskResept());
        }
        avslutt();
    }

    //metoden som gir brukeren mulighet aa gaa tilbake til hovedmeny eller avslutte programmet. kjores hver gang bruker er ferdig med interaksjon
    public void avslutt(){
        System.out.println("S: Avslutte programmet\nH: Hovedmeny");
        Scanner sc = new Scanner(System.in);
        boolean gyldig = false;
        String input = sc.next().toLowerCase();
        
        while(!gyldig){
            if (input.equals("s")){
                System.exit(1);
                gyldig = true;
            } else if (input.equals("h")){
                this.hovedmeny();
                gyldig = true;
            } else {
                System.out.println("Ugyldig input!Oppgi et bokstav: S eller H");
                input= sc.next();
            }
        }
    }

    //oppretter pasient ut fra brukerens input
    public void opprettPasient(){
                Scanner sc = new Scanner(System.in);
                System.out.println("Oppgi navnet til pasienten: ");
                String navn = sc.next();
                System.out.println("Oppgi fodselsnummer til pasienten: ");
                String fnr = sc.next();
                Pasient pasient = new Pasient(navn, fnr);
                allePasienter.leggTil(pasient);
                System.out.println("Pasienten ble lagt til systemet!");
    }

    //oppretter legemiddel ut fra brukerens input
    public void opprettLegemiddel(){
                Scanner sc = new Scanner(System.in);
                System.out.println("Hvilken type legemiddel skal du opprette?\n0: Vanlig\n1: Vanedannende\n2: Narkotisk");
                String valgLml = sc.next();
                boolean riktig = false;
                boolean riktig1 = false;
                int type = 0;
                
                while(!riktig){
                    try{
                        type = Integer.parseInt(valgLml);
                        riktig = true;
                    } catch(NumberFormatException e){
                        System.out.println("Ugyldig input!Oppgi et tall 0-2:");
                        valgLml= sc.next();
                    }
                }

                while(!riktig1){
                    if (type == 0 || type == 1 || type == 2){
                        riktig1 = true;
                    } else {
                        System.out.println("Ugyldig input!Oppgi et tall 0-2:");
                        valgLml= sc.next();
                    }
                }

                System.out.println("Oppgi navnet til legemidlet: ");
                String navn = sc.next();
                System.out.println("Oppgi pris til legemidlet: ");
                String pris = sc.next();
                boolean riktigPris=false;
                int pris1=0;
                double vstoff1=0;
                int styrke1=0;
                
                while(!riktigPris){
                    try{
                        pris1 = Integer.parseInt(pris);
                        riktigPris = true;
                    } catch(NumberFormatException e){
                        System.out.println("Ugyldig input!Oppgi et tall: ");
                        pris = sc.next();
                    }
                }
                System.out.println("Oppgi virkestoff (mg) til legemidlet: ");
                String vstoff = sc.next();
                boolean riktigVstoff=false;
                while(!riktigVstoff){
                    try{
                        vstoff1 = Double.parseDouble(vstoff);
                        riktigVstoff = true;
                    } catch(NumberFormatException e){
                        System.out.println("Ugyldig input!Oppgi et tall: ");
                        vstoff = sc.next();
                    }
                }

                if (type == 0){
                    Legemiddel vanlig = new Vanlig(navn, pris1, vstoff1);
                    alleLegemidler.leggTil(vanlig);
                } else{
                    System.out.println("Oppgi styrke til legemidlet: ");
                    String styrke = sc.next();
                    boolean riktigStyrke=false;
                    while(!riktigStyrke){
                        try{
                            styrke1 = Integer.parseInt(styrke);
                            riktigStyrke = true;
                        } catch(NumberFormatException e){
                            System.out.println("Ugyldig input!Oppgi et tall: ");
                            styrke = sc.next();
                        }
                    }

                    if (type == 1){
                        Legemiddel vanedannende = new Vanedannende(navn, pris1, vstoff1, styrke1);
                        alleLegemidler.leggTil(vanedannende);
                    } else if (type == 2){
                        Legemiddel narkotisk = new Narkotisk(navn, pris1, vstoff1, styrke1);
                        alleLegemidler.leggTil(narkotisk);
                    }
                }
                System.out.println("Legemiddlet ble lagt til systemet!");
    }

    //oppretter lege ut fra brukerens input
    public void opprettLege(){
                Scanner sc = new Scanner(System.in);
                System.out.println("Oppgi navnet til legen: ");
                String navn = sc.next();
                System.out.println("Er legen spesialist? (J:ja/N:nei): ");
                boolean gyldigSpes = false;
                String svar = sc.next().toLowerCase();
                
                while(!gyldigSpes){
                    if (svar.equals("j")){
                        System.out.println("Oppgi kontrol ID til legen: ");
                        String kontrollId = sc.next();
                        Lege spes = new Spesialist(navn, kontrollId);
                        alleLeger.leggTil(spes);
                        gyldigSpes = true;
                    } else if (svar.equals("n")){
                        Lege lege = new Lege(navn, "0");
                        alleLeger.leggTil(lege);
                        gyldigSpes = true;
                    } else {
                        System.out.println("Ugyldig input!Oppgi et bokstav: J eller N");
                        svar= sc.next();
                    }
                }
                System.out.println("Legen ble lagt til systemet!");
    }
    
    //lar brukeren aa opprette resept
    public void opprettResept() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Oppgi ID til pasienten: ");
        String pasId = sc.next();
        Pasient pasient = gyldigPasient(pasId);
        System.out.println("Oppgi navnet til legen: ");
        String legeNavn = sc.nextLine();
        Lege lege = gyldigLege(legeNavn);
        int riktigReit = 0;
        System.out.println("Hvilken type resept vil du opprette?: \nP: P-resept\nM: Millitaer resept\nB: Blaa resept\nH: Hvit resept");
                
        boolean sant = false;

        //type resept
        String type = sc.next().toLowerCase();
        
        while(!sant){
            if (type.equals("p")){
                sant = true;
            } else if (type.equals("m")||type.equals("b")||type.equals("h")){
                System.out.println("Hvor mange reit vil du opprette?: ");
                String reit = sc.next();
                boolean gyldigReit = false;
                while(!gyldigReit){
                    try {
                        //antall reit
                        riktigReit = Integer.parseInt(reit);
                        if (riktigReit>0){
                            gyldigReit=true;
                        } else{
                            System.out.println("Antall reit maa vaere storre enn 0. Oppgi gyldig antall reit: ");
                            reit = sc.next();
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ugyldig input! Oppgi et tall: ");
                        reit = sc.next();
                    }
                }
                        
                sant = true;
            } else {
                System.out.println("Ugyldig input!Oppgi et bokstav: P, M, B eller H");
                type= sc.next();
            }
        }

        System.out.println("Oppgi ID til legemidlet: ");
        String lmlId = sc.next();
        Legemiddel legemiddel = gyldigLegemiddel(lmlId);

        try{
            if(type.equals("p")){
                alleResepter.leggTil(lege.skrivPResept(legemiddel, pasient));
            } else if (type.equals("m")){
                alleResepter.leggTil(lege.skrivMilitaerResept(legemiddel, pasient, riktigReit));
            } else if (type.equals("b")){
                alleResepter.leggTil(lege.skrivBlaaResept(legemiddel, pasient, riktigReit));
            } else if (type.equals("h")){
                alleResepter.leggTil(lege.skrivHvitResept(legemiddel, pasient, riktigReit));
            }
        } catch (UlovligUtskrift e){
            System.out.println(e);
        }
                
        System.out.println("Resepten ble lagt til systemet!");
    }
    
    //sjekker om pasient er gyldig, og returnerer den
    public Pasient gyldigPasient(String pasientID){
        Pasient pasient=null;
        Scanner sc = new Scanner(System.in);
        int id = 0;
        boolean gyldigId=false;
        boolean funnetPas = false;
        while(!gyldigId){
            try {
                id = Integer.parseInt(pasientID);
                for (Pasient pas : allePasienter){
                    if (pas.id == id){
                        funnetPas = true;
                        pasient = pas;
                    } 
                }
                    if (funnetPas){
                        gyldigId=true;
                    } else{
                        System.out.println("Fant ikke pasienten med denne ID. Oppgi gyldig ID: ");
                        pasientID=sc.next();
                    }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig input! Oppgi et tall: ");
                pasientID = sc.next();
            }
        }
        return pasient;
    }

    //sjekker om lege er gyldig, og returnerer den
    public Lege gyldigLege(String legeNavn){
        Lege lege = null;
        boolean funnetLege = false;
        
        while(!funnetLege){
            for (Lege doctor : alleLeger){
                if (legeNavn.equalsIgnoreCase(doctor.hentNavn())){
                    lege = doctor;
                    funnetLege = true;
                }
            }
            if (!funnetLege){
                System.out.println("Fant ikke legen med dette navnet. Oppgi gyldig lege: ");
                Scanner sc = new Scanner(System.in);
                legeNavn=sc.nextLine();
            }
        }
        return lege;
        
    }
    
    //sjekker om legemiddel er gyldig, og returnerer den 
    public Legemiddel gyldigLegemiddel(String lmlId){
        Legemiddel legemiddel = null;
        int legId = 0;
        boolean funnetLml = false;
        //finner leggemiddel med ID fra parameter + sjekker om gyldig
        while(!funnetLml){
            try {
                legId = Integer.parseInt(lmlId);
                for (Legemiddel lml : alleLegemidler){
                    if (lml.hentId() == legId){
                        legemiddel = lml;
                        funnetLml = true;
                    } 
                }
                if(!funnetLml){
                    System.out.println("Fant ikke legemiddel med denne ID. Oppgi gyldig ID: ");
                    Scanner sc = new Scanner(System.in);
                    lmlId=sc.next();
                }
            } catch (NumberFormatException e) {
                System.out.println("Ugyldig input! Oppgi et tall: ");
                Scanner sc = new Scanner(System.in);
                lmlId = sc.next();
            }
        }
        return legemiddel;
    }

    //kommando opprett elementer i systemet
    public void opprett() {
        System.out.println("Hva vil du opprette? \n0: Pasient\n1: Legemiddel\n2: Lege\n3: Resept");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        boolean gyldig1=false;
        boolean gyldig=false;
        int valg = 0;
        //sjekker om gyldig input
        while(!gyldig1){
            try{
                valg = Integer.parseInt(input);
                gyldig1 = true;
            } catch(NumberFormatException e){
                System.out.println("Ugyldig input!Oppgi et tall 0-4:");
                input= sc.next();
            }
        }
        //kaller paa andre funksjoner ut fra brukerens valg
        while (!gyldig){
            if (valg==0){
                opprettPasient();
                gyldig = true;
            } else if (valg == 1){
                opprettLegemiddel();
                gyldig = true;
            } else if (valg == 2){
                opprettLege();
                gyldig = true;
            } else if (valg == 3){
                opprettResept();
                gyldig = true;
            } else{
                System.out.println("Ugyldig input!Oppgi et tall 0-4:");
                valg = sc.nextInt();
            }
        }

        avslutt();
   
    }
}
