/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_serve_app;

/**
 *
 * @author local
 */
import java.io.*;
import java.net.*;

public class SocketServer {

    ServerSocket clientConn;

    /**
     * @param args the command line arguments
     */
    public SocketServer(int port) {
        System.out.println("Server connecting to port_" + port);

        try {
            clientConn = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println("Eception " + e);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        int port = 3000;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception e) {
                port = 3000;
            }
        }
        SocketServer server = new SocketServer(port);
        System.out.println("Server is running on port " + port);
        server.listen();
    }

    private void listen() {
        try {
            System.out.println("Server is waiting for clients");
            while (true) {
                Socket clientReq = clientConn.accept();
                System.out.println("Connection from " + clientReq.getInetAddress().getHostName());
                serverClient(clientReq);
            }
        } catch (IOException e) {
            System.out.println("Exception" + e);
        }
    }

    private void serverClient(Socket s) {
        DataInputStream inStream = null;
        DataOutputStream outStream = null;
        try {
            inStream = new DataInputStream(s.getInputStream());
            outStream = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            System.out.println("Exception" + e);
        }
    }

}
