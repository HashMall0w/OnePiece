package it.marsico.treasure.impostazioni;

import java.util.ArrayList;
import java.util.List;

public class Mappa {
    public static List<Stanza> mondo = new ArrayList<>();

    public static Stanza spiaggia = new Stanza("Spiaggia frastagliata", "Danari");
    public static Stanza taverna = new Stanza("Taverna del lupo di mare", "Lente acquatica");
    public static Stanza caverna = new Stanza("Caverna misteriosa", "Frutto del mare");
    public static Stanza tempio = new Stanza("Tempio sommerso", "One Piece");

    public static void creaMondo() {
        mondo.add(spiaggia);
        mondo.add(taverna);
        mondo.add(caverna);
        mondo.add(tempio);
    }
}
