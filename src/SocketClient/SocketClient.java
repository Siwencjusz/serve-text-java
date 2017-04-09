/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketClient;

/**
 *
 * @author local
 */
import java.io.*;
import java.net.*;

public class SocketClient {

    Socket serverConn;

    public SocketClient(String host, int port) {
        try {
            serverConn = new Socket(host, port);
        } catch (Exception e) {
            System.out.println("Exception" + e);
            System.exit(1);
        }
        System.out.format("Connected to %s :%d", host, port);
    }

    public void sendMessage() {
        DataInputStream inStream = null;
        DataOutputStream outStream = null;

        String message = "hello_world";
        String response;

        try {
            inStream = new DataInputStream(serverConn.getInputStream());
            outStream = new DataOutputStream(serverConn.getOutputStream());
            outStream.writeUTF(message);
            response = inStream.readUTF();
            System.out.println("Server returned " + response);
        } catch (IOException e) {
            System.out.println("I /O Exception : " + e);

        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage : java Socket Client host port");
            System.exit(1);
        }
        String host = args[0];
        int port;
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            port = 3000;
        }
        SocketClient client = new SocketClient(host, port);
        client.sendMessage();
    }
}
