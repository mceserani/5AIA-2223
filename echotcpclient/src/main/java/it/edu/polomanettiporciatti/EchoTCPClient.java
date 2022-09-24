package it.edu.polomanettiporciatti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoTCPClient {
	
	private EchoTCPClient(){
	}

	/**
	 * 
	 * @param args Command line arguments
	 * @author Matteo Ceserani
	 * @version 1.0
	 */
    public static void main(String[] args){
       
		BufferedReader tastiera = null;
		String stringaUtente = null;
		tastiera = new BufferedReader(new InputStreamReader(System.in));

		Socket s = null;
		try {
			if (args[0] == null) 
				s = new Socket("localhost", 7);
			else
				s = new Socket(args[0], 7);
		} catch (IOException e) {
			System.err.println("Errore di connessione");
			System.exit(1);
		}

		System.out.println("Inserisci la stringa da inviare al server: ");

		try {
			stringaUtente = tastiera.readLine();
		} catch (IOException e) {
			System.err.println("Errore di lettura da tastiera");
			System.exit(2);
		}

		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			System.err.println("Errore di lettura dal socket");
			System.exit(3);
		}

		PrintWriter out = null;
		try {
			out = new PrintWriter(s.getOutputStream(), true);
		} catch (IOException e) {
			System.err.println("Errore di scrittura sul socket");
			System.exit(4);
		}

		out.println(stringaUtente);

		String stringaRicevuta = null;
		try {
			stringaRicevuta = in.readLine();
		} catch (IOException e) {
			System.err.println("Errore di lettura dal socket");
			System.exit(5);
		}

		System.out.println("Ricevuto: " + stringaRicevuta);

    }

}