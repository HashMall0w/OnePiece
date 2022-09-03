package it.marsico.treasure.impostazioni;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GestioneFile {
    public static void leggiFile(String percorsoFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(percorsoFile));
        String testo = reader.readLine();

        while (testo != null) {
            System.out.println(testo);
            testo = reader.readLine();
        }
        reader.close();
    }
}
