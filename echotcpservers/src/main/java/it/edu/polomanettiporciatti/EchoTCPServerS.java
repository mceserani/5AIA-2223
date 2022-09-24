package it.edu.polomanettiporciatti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCPServerS {
	
	private EchoTCPServerS(){
	}

	/**
	 * 
	 * @param args Command line arguments
	 * @author Matteo Ceserani
	 * @version 1.0
	 */
    public static void main(String[] args){
       
		ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(7);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 7.");
            System.exit(1);
        }

        while(true){

            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                continue;
            }

            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                System.err.println("Cannot create socket streams.");
                continue;       
            }

            PrintWriter out = null;
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.err.println("Cannot create socket streams.");
                continue;      
            }

            String inputLine = null;
            try {
                while((inputLine = in.readLine())!=null){
                    out.println(inputLine);
                }
            } catch (IOException e) {
                System.err.println("Cannot read from socket.");
                continue;
            }

            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Cannot close socket.");
                continue;
            }
        
        } // end while


    }

}