package it.marsico.treasure.impostazioni;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicaThread extends Thread {
    private String percorso;
    private Musica player;
    // private Musica loop;

    public MusicaThread(String percorsoMusica)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.percorso = percorsoMusica;
        this.player = new Musica(getPath());
    }

    public String getPath() {
        return percorso;
    }

    @Override
    public void run() {
        player.setLoop();
        
        while (true);
    }
}
