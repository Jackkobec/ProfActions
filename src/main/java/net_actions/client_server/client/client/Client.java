package net_actions.client_server.client.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Jack on 30.10.2016.
 */
public class Client {

    public void clientRun() {

        try {
            Socket client = new Socket("localhost", 8080);

            //InputStream is = client.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(client.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String temp = null;

            while ((temp = bf.readLine()) != null) {
                sb.append(temp);
            }

            System.out.println(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
