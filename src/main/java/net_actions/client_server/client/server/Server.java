package net_actions.client_server.client.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Jack on 30.10.2016.
 */
public class Server {
    public void serverRun() {

        try {

            ServerSocket serverSocket = new ServerSocket(8080);

            for (; ; ) {
                InetAddress myLockalIP = InetAddress.getLocalHost();

                Socket forClient = serverSocket.accept();

                String clientInfo = String.format("ip: %s:%d\n", forClient.getInetAddress(), forClient.getPort());

                PrintWriter pw = new PrintWriter(forClient.getOutputStream());
                pw.print("Hello from server: " + myLockalIP + "\n");
                pw.flush();
                System.out.printf("Connected from %s:%s\n", forClient.getInetAddress(), forClient.getPort());
                pw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
