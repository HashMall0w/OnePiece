package it.marsico.treasure.impostazioni;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import it.marsico.treasure.clientServer.Client;
import it.marsico.treasure.database.Database;
import it.marsico.treasure.grafica.MappaGrafica;
import it.marsico.treasure.grafica.Menu;
import it.marsico.treasure.pg.Player;

public class Comandi {
    private String input;
    private int i = 0;
    private boolean statusGame = true;
    private boolean esiste = false;
    private boolean usato = false;
    lambdaEx lambdaex = () -> System.out.print("Comandi: ");
    Stanza posizioneGiocatore;

    public void setInput() {
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
    }

    public static void pulisciSchermo() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void managerComandi() throws Exception {
        Mappa.creaMondo();

        for (i = 0; i < Mappa.mondo.size(); i++) {
            posizioneGiocatore = Mappa.mondo.get(i);

            do {
                lambdaex.stampa();
                setInput();
                switch (input.toUpperCase()) {
                    case "N":
                        if (posizioneGiocatore.getNome().equalsIgnoreCase("Spiaggia frastagliata")) {
                            pulisciSchermo();
                            posizioneGiocatore = Mappa.taverna;
                            GestioneFile.leggiFile("descrizioni mappe\\Taverna.txt");
                            System.out.println("posizione: " + posizioneGiocatore.getNome());

                            if (!Player.inventario.contains("Lente acquatica")) {
                                GestioneFile.leggiFile(("monologhi\\Monologo Taverna.txt"));
                                if (esiste == true) {
                                    Database.connessioneDB();
                                    Database.query();
                                    Database.chiudiConnessione();
                                } else {
                                    Database.creaDB();
                                    esiste = true;
                                }
                            }
                            break;
                        } else if (posizioneGiocatore.getNome().equalsIgnoreCase("Tempio sommerso")) {
                            System.out.println("Non puoi andare piu a nord di così");
                        } else if (posizioneGiocatore.getNome().equalsIgnoreCase("Caverna misteriosa")) {
                            System.out.println("Non puoi andare piu a nord di così");
                        } else if (posizioneGiocatore.getNome().equalsIgnoreCase("Taverna del lupo di mare")) {
                            System.out.println("Non puoi andare piu a nord di così");
                        }
                        break;

                    case "S":
                        if (posizioneGiocatore.getNome().equalsIgnoreCase("Spiaggia frastagliata")) {
                            System.out.println("Non puoi andare piu a sud di così");
                        } else if (posizioneGiocatore.getNome().equalsIgnoreCase("Tempio sommerso")) {
                            System.out.println("Non puoi andare piu a sud di così");
                        } else if (posizioneGiocatore.getNome().equalsIgnoreCase("Caverna misteriosa")) {
                            System.out.println("Non puoi andare piu a sud di così");
                        } else if (posizioneGiocatore.getNome().equalsIgnoreCase("Taverna del lupo di mare")) {
                            pulisciSchermo();
                            posizioneGiocatore = Mappa.spiaggia;
                            GestioneFile.leggiFile("descrizioni mappe\\Spiaggia.txt");

                            if (Player.inventario.contains("Cibo")) {
                                GestioneFile.leggiFile(("monologhi\\Monologo Spiaggia.txt"));
                            }
                        }
                        break;

                    case "E":
                        if (posizioneGiocatore.getNome().equalsIgnoreCase("Spiaggia frastagliata")) {
                            pulisciSchermo();
                            posizioneGiocatore = Mappa.caverna;
                            GestioneFile.leggiFile("descrizioni mappe\\Caverna.txt");

                            if (!Player.inventario.contains("Frutto del mare")) {
                                GestioneFile.leggiFile(("monologhi\\Monologo Caverna.txt"));
                            }
                        } else if (posizioneGiocatore.getNome().equalsIgnoreCase("Tempio sommerso")) {
                            pulisciSchermo();

                            if (Player.inventario.isEmpty()) {
                                System.out.println("Ma cosa fai! Prendi il tesoro!");
                                posizioneGiocatore = Mappa.tempio;
                                break;
                            } else {
                                posizioneGiocatore = Mappa.spiaggia;
                                GestioneFile.leggiFile("descrizioni mappe\\Spiaggia.txt");
                            }

                        } else if (posizioneGiocatore.getNome().equalsIgnoreCase("Caverna misteriosa")) {
                            System.out.println("Non puoi andare piu a est di così");
                        } else if (posizioneGiocatore.getNome().equalsIgnoreCase("Taverna del lupo di mare")) {
                            System.out.println("Non puoi andare piu a est di così");
                        }
                        break;

                    case "O":
                        if (posizioneGiocatore.getNome().equalsIgnoreCase("Spiaggia frastagliata")) {
                            pulisciSchermo();
                            posizioneGiocatore = Mappa.tempio;
                            if (!(Player.inventario.contains("Lente acquatica")
                                    && (Player.inventario.contains("Frutto del mare")))) {
                                System.out.println("Non possiedi tutti gli oggetti necessari per accedere");
                                posizioneGiocatore = Mappa.spiaggia;
                            } else if (Player.inventario.contains("Lente acquatica")
                                    && Player.inventario.contains("Frutto del mare")) {

                                GestioneFile.leggiFile("descrizioni mappe\\Tempio.txt");
                                GestioneFile.leggiFile("monologhi\\Monologo Tempio.txt");

                                // Parte il client-server
                                controlloInventario();
                                Enigma.enigma();
                                Client.connetti();
                                Client.comunicaClient();

                            } else if (Mappa.tempio.getItem().equals(null)) {
                                GestioneFile.leggiFile("descrizioni mappe\\Tempio.txt");
                            }

                        } else if (posizioneGiocatore.getNome().equalsIgnoreCase("Tempio sommerso")) {
                            System.out.println("Non puoi andare piu a ovest di così");
                        } else if (posizioneGiocatore.getNome().equalsIgnoreCase("Caverna misteriosa")) {
                            pulisciSchermo();
                            GestioneFile.leggiFile("descrizioni mappe\\Spiaggia.txt");
                            posizioneGiocatore = Mappa.spiaggia;

                            if (Player.inventario.contains("Cibo")) {
                                GestioneFile.leggiFile(("monologhi\\Monologo Spiaggia.txt"));
                            }
                        } else if (posizioneGiocatore.getNome().equalsIgnoreCase("Taverna del lupo di mare")) {
                            System.out.println("Non puoi andare piu a ovest di così");
                        }
                        break;

                    case "PRENDI":
                        switch (posizioneGiocatore.getNome()) {
                            case "Spiaggia frastagliata":
                                if (posizioneGiocatore.getItem().equals("")) {
                                    System.out.println("Non ci sono oggetti nella stanza");
                                } else if (!posizioneGiocatore.getItem().equals(null)) {
                                    if (!Player.inventario.contains("Cibo")) {
                                        Player.inventario.add("Danari");
                                        posizioneGiocatore.resetList();
                                        System.out.println("Oggetto preso!");
                                    } else
                                        System.out.println("Ah mi ha morsoo!");
                                }
                                break;

                            case "Taverna del lupo di mare":
                                if (posizioneGiocatore.getItem().equals("")) {
                                    System.out.println("Non ci sono oggetti nella stanza");
                                } else if (!posizioneGiocatore.getItem().equals("")) {
                                    if (Player.inventario.contains("Cibo")) {
                                        System.out.println("Prima devi pagare!");
                                    } else if (Player.inventario.contains("Danari")) {
                                        System.out.println("Prima devi pagare!");
                                    } else if (usato != true) {
                                        System.out.println("Prima devi pagare");
                                    } else if (usato == true) {
                                        Player.inventario.add("Lente acquatica");
                                        posizioneGiocatore.resetList();
                                        System.out.println("Oggetto preso!");
                                    }
                                }
                                break;

                            case "Caverna misteriosa":
                                if (posizioneGiocatore.getItem().equals("")) {
                                    System.out.println("Non ci sono oggetti nella stanza");
                                } else if (!posizioneGiocatore.getItem().equals(null)) {
                                    Player.inventario.add("Frutto del mare");
                                    posizioneGiocatore.resetList();
                                    System.out.println("Oggetto preso!");
                                }
                                break;

                            case "Tempio sommerso":
                                if (!posizioneGiocatore.getItem().equals(null)) {
                                    if (Player.inventario.contains("Frutto del mare")
                                            || Player.inventario.contains("Lente acquatica")) {
                                        System.out.println("Non puoi ancora prendere questo oggetto!");
                                    } else if (Player.inventario.isEmpty()) {

                                        if (!input.equalsIgnoreCase("Prendi")) {
                                            System.out.println("Ma cosa fai.. PRENDI IL TESORO!");
                                        } else {
                                            Player.inventario.add("One Piece");
                                            posizioneGiocatore.resetList();
                                            System.out.println("Oggetto preso!");
                                            System.out.println("Grazie per aver giocato il mio gioco RE DEI PIRATI!");
                                            Database.queryClear();
                                            System.exit(1);
                                        }
                                    }
                                }
                                break;
                        }
                        break;

                    case "USA":
                        String oggetto = null;
                        int i = 0;

                        if (Player.inventario.isEmpty()) {
                            System.out.println("L'inventario è vuoto");
                        } else {
                            System.out.println("INVENTARIO\n" + Player.inventario);
                            System.out.print("item?: ");
                            setInput();

                            for (i = 0; i < Player.inventario.size(); i++) {
                                if (Player.inventario.get(i).equalsIgnoreCase(input)) {
                                    oggetto = Player.inventario.get(i);
                                }
                            }

                            if (!input.equalsIgnoreCase(oggetto)) {
                                System.out.println("Oggetto inesistente");
                            } else {
                                if (posizioneGiocatore.getNome().equals("Tempio sommerso")) {
                                    switch (oggetto) {
                                        case "Frutto del mare":
                                            Player.inventario.remove(oggetto);
                                            System.out.println("Oggetto utilizzato!");
                                            break;

                                        case "Lente acquatica":
                                            Player.inventario.remove(oggetto);
                                            System.out.println("Oggetto utilizzato!");
                                            break;

                                        case "Cibo":
                                            System.out.println("Non puoi utilizzare questo item qui!");
                                            break;

                                        case "Danari":
                                            System.out.println("Non puoi utilizzare questo item qui!");
                                            break;
                                    }
                                } else if (posizioneGiocatore.getNome().equals("Spiaggia frastagliata")) {
                                    switch (oggetto) {
                                        case "Cibo":
                                            Player.inventario.remove(oggetto);
                                            System.out.println("Oggetto utilizzato");
                                            break;

                                        case "Danari":
                                            System.out.println("Non puoi utilizzare questo item qui!");
                                            break;

                                        case "Frutto del mare":
                                            System.out.println("Non puoi utilizzare questo item qui!");
                                            break;

                                        case "Lente acquatica":
                                            System.out.println("Non puoi utilizzare questo item qui!");
                                            break;
                                    }
                                } else if (posizioneGiocatore.getNome().equals("Taverna del lupo di mare")) {
                                    switch (oggetto) {
                                        case "Danari":
                                            Player.inventario.remove(oggetto);
                                            System.out.println("Oggetto utilizzato");
                                            usato = true;
                                            break;

                                        case "Cibo":
                                            System.out.println("Non puoi utilizzare questo item qui!");
                                            break;

                                        case "Frutto del mare":
                                            System.out.println("Non puoi utilizzare questo item qui!");
                                            break;

                                        case "Lente acquatica":
                                            System.out.println("Non puoi utilizzare questo item qui!");
                                            break;
                                    }
                                } else if (posizioneGiocatore.getNome().equals("Caverna misteriosa")) {
                                    switch (oggetto) {
                                        case "Cibo":
                                            System.out.println("Non puoi utilizzare questo item qui!");
                                            break;

                                        case "Danari":
                                            System.out.println("Non puoi utilizzare questo item qui!");
                                            break;

                                        case "Frutto del mare":
                                            System.out.println("Non puoi utilizzare questo item qui!");
                                            break;

                                        case "Lente acquatica":
                                            System.out.println("Non puoi utilizzare questo item qui!");
                                            break;
                                    }
                                }
                            }
                        }
                        break;

                    case "MOSTRA OGGETTI":
                        switch (posizioneGiocatore.getNome()) {
                            case "Spiaggia frastagliata":
                                System.out.println("Oggetti stanza = " + "[" + posizioneGiocatore.getItem() + "]");
                                break;

                            case "Tempio sommerso":
                                System.out.println("Oggetti stanza = " + "[" + posizioneGiocatore.getItem() + "]");
                                break;

                            case "Taverna del lupo di mare":
                                System.out.println("Oggetti stanza = " + "[" + posizioneGiocatore.getItem() + "]");
                                break;

                            case "Caverna misteriosa":
                                System.out.println("Oggetti stanza = " + "[" + posizioneGiocatore.getItem() + "]");
                                break;
                        }
                        break;

                    case "I":
                        pulisciSchermo();
                        if (!Player.inventario.isEmpty()) {
                            System.out.println("INVENTARIO:\n" + Player.inventario);
                        } else {
                            System.out.println("INVENTARIO:");
                        }
                        break;

                    case "M":
                        pulisciSchermo();
                        MappaGrafica.mostraMappa();
                        break;

                    case "HELP":
                        pulisciSchermo();
                        Menu.help();
                        ;
                        break;

                    case "ESCI":
                        if (input.equalsIgnoreCase("Esci")) {
                            pulisciSchermo();
                            statusGame = false;
                            Database.queryClear();
                            System.out.println("Ciao Ciao!");
                            System.exit(1);
                        }
                        break;

                    default:
                        System.out.println("Comando non valido");
                        break;
                }
            } while (statusGame);
        }
    }

    public void comandiMenu() throws Exception {
        do {
            System.out.print("Comando: ");
            setInput();

            switch (input.toUpperCase()) {
                case "NUOVA PARTITA":
                    pulisciSchermo();
                    GestioneFile.leggiFile("prologo\\Prologo.txt");
                    System.out.println("\n");
                    skip();
                    pulisciSchermo();
                    GestioneFile.leggiFile("descrizioni mappe\\Spiaggia.txt");
                    GestioneFile.leggiFile(("monologhi\\Monologo Spiaggia.txt"));
                    managerComandi();
                    break;

                case "HELP":
                    pulisciSchermo();
                    Menu.help();
                    break;

                case "ESCI":
                    System.out.println("Dov'è finito il tuo spirito di avventura?\n");
                    System.exit(1);
                    break;

                default:
                    System.out.println("Comando non valido");
                    break;
            }
        } while (!input.equalsIgnoreCase("NUOVA PARTITA"));
    }

    // Metodo che controlla l'inventario per far accedere il protagonista all'enigma
    public void controlloInventario() throws IOException {
        if (Player.inventario.isEmpty()) {
            System.out.println("L'inventario è vuoto");
        } else {
            do {
                System.out.println("INVENTARIO\n" + Player.inventario);
                System.out.print("item?: ");
                setInput();

                if (input.equalsIgnoreCase("Lente acquatica")) {
                    if (Player.inventario.contains("Lente acquatica")
                            && (Player.inventario.contains("Frutto del mare"))) {
                        Player.inventario.remove("Lente acquatica");
                        System.out.println("Ora si che si ragiona!\n "
                                + "Posso vedere sott'acqua ma manca ancora qualcosa!");
                    } else if (Player.inventario.contains("Lente acquatica")
                            && (!Player.inventario.contains("Frutto del mare"))) {
                        Player.inventario.remove("Lente acquatica");

                    }
                } else if (input.equalsIgnoreCase("Frutto del mare")) {
                    if (Player.inventario.contains("Lente acquatica")
                            && (Player.inventario.contains("Frutto del mare"))) {
                        Player.inventario.remove("Frutto del mare");
                        System.out.println("Ora si che si ragiona!\n "
                                + "Posso respirare sott'acqua ma manca ancora qualcosa!");
                    } else if (Player.inventario.contains("Frutto del mare")
                            && (!Player.inventario.contains("Lente acquatica"))) {
                        Player.inventario.remove("Frutto del mare");
                    }
                } else if (!input.equalsIgnoreCase("Frutto del mare") || !input.equalsIgnoreCase("Lente acquatica")) {
                    System.out.println("Oggetto inesistente");
                }
            } while (Player.inventario.contains("Lente acquatica") || (Player.inventario.contains("Frutto del mare")));
        }
    }

    public void skip() {
        do {
            System.out.print("Premi invio ");
            setInput();
            if (!input.equals("")) {
                System.out.println("Comando non valido");
            }
        } while (!input.equals(""));
    }
}
