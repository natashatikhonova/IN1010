import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;



class GUI {
    
    public static void main(String[] args) {
        //oppretter GUI
        JFrame frame = new JFrame("Path Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        App app = new App();
        app.initGUI();
        frame.add(app);
        frame.pack();
        frame.setVisible(true);
        
    }
}

//oppretter app med to panels
class App extends JPanel{
    GUIvindu vindu;
    App(){
        vindu = new GUIvindu(this);
    } 

    void initGUI(){
        add(vindu);
        vindu.initGUI();
        setLayout(new GridLayout(1,2));
    }
}

//oppretter UI
class GUIvindu extends JPanel{
    App app;
    JButton velgknapp;
    JLabel filnavn;
    JButton resetknapp;
    JButton sluttknapp;
    JLabel antLosninger;
    JLabel beskjed;
    GUIbrett brett;
    Labyrint l;
    
    GUIvindu(App app){      
        this.app=app;
    }

    void initGUI(){
        velgknapp = new JButton("Select Labyrint");
        filnavn = new JLabel("No file selected");
        
        //velg filen
        class Filvelger implements ActionListener{
            GUIvindu vindu;
            Filvelger(GUIvindu vindu){
                this.vindu=vindu;
            }
            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser file = new JFileChooser();
                int resultat = file.showOpenDialog(null);
                if(resultat== JFileChooser.APPROVE_OPTION){
                    File valgtFil = file.getSelectedFile();
                    //hvis brettet ble opprettet tidligere, fjern den gamle brettet
                    if(brett!=null){
                        app.remove(brett);
                        app.revalidate();
                        app.repaint();
                        l = null;
                        beskjed.setText("");
                        
                    }
                    try {
                        l = new Labyrint(valgtFil);
                        filnavn.setText("File selected: "+ valgtFil.getName());
                        brett = new GUIbrett(vindu, l);
                        brett.initGUI();
                        app.add(brett);
                    } catch (Exception exc) {
                        //umulig aa lese inn filen
                        filnavn.setText("Kunne ikke lese fra  denne filen filen");
                        antLosninger.setText("Antall losninger: ");
                    }
                    
                    
                }
            }
        }
        velgknapp.addActionListener(new Filvelger(this));
        antLosninger = new JLabel("Antall losninger: ");
        resetknapp = new JButton("Clean");
        class Clear implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                //gaa gjennom alle rutene som ble farget og sette fargen til hvit
                for (int i=0; i<brett.antR; i++){
                    for (int j=0; j<brett.antK; j++){
                        if (brett.ruter[i][j].getBackground()== Color.CYAN){
                            brett.ruter[i][j].setBackground(Color.WHITE);
                        }
                    }
                }
                antLosninger.setText("Antall losninger: ");
                beskjed.setText("");
            }
        }
        resetknapp.addActionListener(new Clear());
        beskjed = new JLabel("");
        sluttknapp = new JButton("Exit");
        //avslutte programmet
        class Stoppbehandler implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        }
        sluttknapp.addActionListener(new Stoppbehandler());
        add(velgknapp);
        add(resetknapp);
        add(sluttknapp);
        add(filnavn);
        add(antLosninger);
        add(beskjed);
        
        setLayout(new GridLayout(6, 1));
    }
        
        
    
}

//lager GUI labyrint brettet ut fra innleste filen
class GUIbrett extends JPanel{
    GUIvindu vindu;
    GUIrute [][] ruter;
    Labyrint l;
    int antR;
    int antK;

    GUIbrett(GUIvindu vindu, Labyrint l){
        this.vindu = vindu;
        this.l = l;
        antR = l.hentRad();
        antK = l.hentKol();
        ruter = new GUIrute[antR][antK];
        //lager en GUI-rute for hver rute i labyrinten
        for (int i =0; i<antR; i++){
            for (int j=0; j<antK; j++){

                ruter[i][j]= new GUIrute(this, l.hentRute(i, j));
            }
        }
    }

    void initGUI(){
        for (int i =0; i<antR; i++){
            for (int j=0; j<antK; j++){
                ruter[i][j].initGUI();
                
                add(ruter[i][j]);
            }
        }
        setLayout(new GridLayout(antR, antK));
    }


}
//oppretter GUIrute ut fra informasjonen om ruten 
class GUIrute extends JButton{
    GUIbrett brett;
    Rute rute;

    GUIrute(GUIbrett brett, Rute rute){
        this.brett=brett;
        this.rute = rute;
    }
    void initGUI(){
        class Ruteknapp implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e){
                for (int i=0; i<brett.antR; i++){
                    for (int j=0; j<brett.antK; j++){
                        if (brett.ruter[i][j].getBackground()== Color.CYAN){
                            brett.ruter[i][j].setBackground(Color.WHITE);
                        }
                    }
                }
                brett.vindu.antLosninger.setText("Antall losninger: ");
                
                //listen med alle utveier fra denne ruten
               ArrayList<ArrayList<Tuppel>> utveier = brett.l.finnUtveiFra(rute.kol, rute.rad);

               //beskjed at det er ingen utveier
                if(utveier.size()==0){
                    brett.vindu.beskjed.setText("Det finnes ingen utvei fra denne rute");
                } else{
                    //gaa gjennom alle rutene i uteveien og farge dem
                    for (Tuppel r : utveier.get(0)){
                        brett.ruter[r.y][r.x].setBackground(Color.CYAN);
                    }
                    brett.vindu.beskjed.setText("");
                    brett.vindu.antLosninger.setText("Antall losninger: " + String.valueOf(brett.l.finnUtveiFra(rute.kol, rute.rad).size()));
                }
                
                
            }
        }
        setPreferredSize(new Dimension(30,30));
        if(rute instanceof HvitRute){
            this.setBackground(Color.WHITE);
            addActionListener(new Ruteknapp());
        } else{
            this.setBackground(Color.BLACK);
        }
        
        this.setOpaque(true);
        this.setBorderPainted(false);     
        
        
        

        
    }   

}

