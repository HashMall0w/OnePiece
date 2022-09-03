package it.marsico.treasure.grafica;

public class Menu {
    public void mostraStampeMenu() {
        System.out.println(" _______                   ______ __                   \n"
        +"|       |.-----.-----.    |   __ |__|.-----.----.-----.\n"
        +"|   -   ||     |  -__|    |    __/  ||  -__|  __|  -__|\n"
        +"|_______||__|__|_____|    |___|  |__||_____|____|_____|\n"
        +"\n"
        + "Nuova partita:\n"
        + "Help\n"
        + "Esci\n");
        }

 public static void help() {
        
        System.out.println("Benvenuto! Puoi consultarmi quando vorrai anche durante il gioco, quindi non esitare! ");

        System.out.println("Comandi del menu':\n"
                +   "Nuova: Per iniziare una nuova partita\n"
                +   "Help: Per consultare i vari comandi\n"
                +   "Esci: Esce dal gioco\n");

        System.out.println("Comandi in gioco:\n"
                +   "N: Vai a nord\n"
                +   "S: Vai a sud\n"
                +   "E: Vai a est\n"
                +   "O: Vai a ovest\n"
                +   "I: mostra l'inventario\n"
                +   "M: mostra la mappa\n"
                +   "Mostra oggetti: mostra gli oggetti disponibili in base al luogo in cui ti trovi\n"
                +   "Prendi: prende l'oggetto disponibile in quella stanza\n"
                +   "Usa: Chiedera' il nome dell'item, una volta inserito il nome userà quello specifico item\n"
                +   "Esci: Esce dal gioco"); 
	}
}
