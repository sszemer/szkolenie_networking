package com.company.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientThread implements Runnable {
    @Override
    public void run() {
        try {
            Socket socket = new Socket("192.168.0.108", 5555);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBytes("elo z clienta\n");
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
