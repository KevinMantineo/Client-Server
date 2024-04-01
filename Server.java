/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mainserver;

/**
 *
 * @author kevin
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int porta;

    public Server(int porta) {
        this.porta = porta;
    }

    public void attendi() {
        try {
            serverSocket = new ServerSocket(porta);
            System.out.println("Server in ascolto sulla porta " + porta);

            while (true) { // Server rimane in ascolto per nuove connessioni
                clientSocket = serverSocket.accept();
                System.out.println("Connessione accettata da: " + clientSocket.getInetAddress());

                leggi();
                scrivi("Ciao, sono il server!");
                chiudi();
            }
        } catch (IOException ex) {
            System.err.println("Errore nella fase di ascolto");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void scrivi(String messaggio) {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(messaggio);
        } catch (IOException e) {
            System.out.println("Errore nell'invio del messaggio.");
            e.printStackTrace();
        }
    }

    public void leggi() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String messaggio = in.readLine();
            System.out.println("Messaggio ricevuto dal client: " + messaggio);
        } catch (IOException e) {
            System.out.println("Errore nella lettura del messaggio.");
            e.printStackTrace();
        }
    }

    public void chiudi() {
        try {
            if (clientSocket != null) {
                clientSocket.close();
                System.out.println("Connessione chiusa con il client.");
            }
        } catch (IOException e) {
            System.out.println("Errore nella chiusura del socket.");
            e.printStackTrace();
        }
    }
}