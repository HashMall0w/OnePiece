package it.marsico.treasure.impostazioni;

import java.io.IOException;

public class Enigma {
    public static void enigma() throws IOException {
        System.out.println("Bene! Ora posso procedere");
        GestioneFile.leggiFile("enigma\\Enigma.txt");
    }
}
