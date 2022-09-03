package it.marsico.treasure;

import it.marsico.treasure.grafica.Menu;
import it.marsico.treasure.impostazioni.Comandi;
import it.marsico.treasure.impostazioni.MusicaThread;
import it.marsico.treasure.pg.Player;

public final class App {
    private App() {
    }

    public static void main(String[] args) throws Exception {
        Comandi comandi = new Comandi();
        Menu menu = new Menu();
        Player pg = new Player();
        MusicaThread musica = new MusicaThread("src/musica.wav");

        musica.start();

        pg.setInventario();
        menu.mostraStampeMenu();
        comandi.comandiMenu();
    }
}
