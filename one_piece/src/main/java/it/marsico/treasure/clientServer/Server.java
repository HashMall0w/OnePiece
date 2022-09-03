package it.marsico.treasure.clientServer;
import java.io.*;
import java.net.*;

public class Server {
    static ServerSocket server = null;
    static Socket client = null;//utilizzato dal client per comunicare
    
    static int porta = 5625;//porta server
    
    static BufferedReader in;
    static DataOutputStream out;
    static String rispostaRicevuta;
    static String rispostaServer;

    public static void attendi() throws Exception{
        System.out.println("server inizializzato");
        server = new ServerSocket(porta);
        client = server.accept();
        server.close();

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new DataOutputStream(client.getOutputStream());
    }

    public static void comunicaServer() throws Exception{
        
        do {
            rispostaRicevuta = in.readLine();
            if(rispostaRicevuta.equalsIgnoreCase("Specchio")){
                rispostaServer = "La porta si apre...I tuoi occhi brillano...Il One Piece..";
                out.writeBytes(rispostaServer + "\n");
            }else{
                rispostaServer = "Ritenta sarai piu fortunato";
                //System.out.println("Risposta: " + rispostaServer);
                out.writeBytes(rispostaServer + "\n");
            }
        } while (!rispostaRicevuta.equalsIgnoreCase("Specchio"));
        client.close();
    }

    public static void main (String [] args) throws Exception{
        Server.attendi();
        Server.comunicaServer();
    }
}
