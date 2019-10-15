package com.company.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientThread implements Runnable {
    @Override
    public void run() {
        try {
            Socket localhost = new Socket("localhost", 1337);
            OutputStream outputStream = localhost.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBytes("elo z clienta");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
