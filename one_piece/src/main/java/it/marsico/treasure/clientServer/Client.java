package it.marsico.treasure.clientServer;

import java.io.*;
import java.net.*;

public class Client {
    static Socket socketClient = null;//utilizzato dal client per comunicare

    static int porta = 5625;//porta server

    static BufferedReader in;
    static DataOutputStream out;

    static BufferedReader tastiera;
    static String rispostaInviata;

    
    public static void connetti() throws UnknownHostException, Exception{
        System.out.println("connessione server...");
        socketClient = new Socket(InetAddress.getLocalHost(), porta);//qui ci va ip server e porta server
        System.out.println("connesso");
        in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        //in = new DataInputStream(socketClient.getInputStream());
        out = new DataOutputStream(socketClient.getOutputStream());
    }

    public static void comunicaClient() throws Exception{
        do {
            tastiera = new BufferedReader(new InputStreamReader(System.in));//leggo quello che scrivo sulla tastiera
            System.out.println("Risposta enigma: ");
            rispostaInviata = tastiera.readLine();//qui salvo quello che scrivo
            out.writeBytes(rispostaInviata + "\n");
            //lato server
            String ricevuta = in.readLine();
            System.out.println("risposta server: " + ricevuta);
        } while (!rispostaInviata.equalsIgnoreCase("Specchio"));
    }

    /**
     * 
     * @param args
     * @throws Exception
     * 
     *     public static void main (String [] args) throws Exception{
        Client.connetti();
        Client.comunicaClient();
    }
     */
}

