package itis.meucci;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
    public void avvio()
    {
        try
        {
            ServerSocket ServerSocket = new ServerSocket();
            for(;;)
            {
                System.out.println("1: server in attesa...");
                Socket socket = ServerSocket.accept();
                System.out.println("3: server socket " + socket);
                ServerThread serverThread = new ServerThread(socket);
                serverThread.comunica();
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);
        }
    }
}
