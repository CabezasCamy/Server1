package itis.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStr {
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String StrinngaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi()
    {
        try
        {
            System.out.println("1: il server e' in esecuzione" + '\n');
            //attivo il server sulla porta, apertura della porta
            server = new ServerSocket(2018);
            //rimane in attesa di un cliente, istruzione bloccante
            client = server.accept();
            server.close(); //chiusura della porta, ma non del socket
            //stream di scrittura e di lettura
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);
        }
        //
        return client;
    }

    public void comunica()
    {
        try
        {
            System.out.println("3: benvenuto client, scrivi una frase e la trasformerò in maiuscolo. Attendo..." + '\n');
            //legge
            stringaRicevuta = inDalClient.readLine();
            System.out.println("6: ho ricevuto la stringa => " + stringaRicevuta + '\n');
            //modifico la stringa ricevuta dal client
            StrinngaModificata = stringaRicevuta.toUpperCase();
            //fatto, ora la invio
            System.out.println("7: invio la stringa..." + '\n');
            //invio la stringa modificata
            outVersoClient.writeBytes(StrinngaModificata + '\n');

            //termino elaborazione sul server: chiudo la connessione, ovvero il socket
            System.out.println("9: fine elaborazione ... arrivederci");
            client.close(); //chiusura socket
        }
        catch(Exception e)
        {
        }
    }
}
