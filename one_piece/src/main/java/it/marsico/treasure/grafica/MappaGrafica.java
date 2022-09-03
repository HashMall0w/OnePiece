package it.marsico.treasure.grafica;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MappaGrafica {
    static JFrame finestra;
    static ImageIcon immagine;
    static JLabel pannello;

    public static void creaFinestraMappa() {

        immagine = new ImageIcon("src/mappa.jpg");
        pannello = new JLabel(immagine);
        pannello.setSize(1920, 1080);
    
        finestra = new JFrame();
        finestra.setTitle("Mappa giocatore");
        finestra.add(pannello);
        finestra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        finestra.setSize(1920, 1080);
        finestra.setLocationRelativeTo(null);
        finestra.setResizable(false);
        finestra.setVisible(true);
        finestra.setAlwaysOnTop(true);
    }

    public static void mostraMappa() {
        creaFinestraMappa();
    }
}
