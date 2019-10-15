package com.company.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable {

    private final Socket acceptedSocket;

    public ServerThread(Socket socket){
        this.acceptedSocket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = acceptedSocket.getInputStream();
            OutputStream outputStream = acceptedSocket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine())!=null){
                System.out.println(line);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
