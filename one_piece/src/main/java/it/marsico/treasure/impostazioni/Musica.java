package it.marsico.treasure.impostazioni;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Musica {
	private Clip clip;
	private AudioInputStream audioInputStream;

	public Musica(String percorsoFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

		audioInputStream = AudioSystem.getAudioInputStream(new File(percorsoFile).getAbsoluteFile());
		clip = AudioSystem.getClip();
		clip.open(audioInputStream);
	}

	public void setLoop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void play() {
		clip.start();
	}
}
